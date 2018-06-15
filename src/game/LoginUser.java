package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class LoginUser {
	
	private String nickName;
	private JTextArea userListArea;
	private JTextArea chatArea;
	private Thread recieveThread;
	private Thread game1checkThread;
	ArrayList<LoginUser> playerList ;  
	ObjectOutputStream oos;
	ObjectInputStream ois;
	User user;
	Socket socket;
	Vector<String> nameList;
	JPanel gameMainPanel;
	JPanel gamePanel;
	GameMainFrame game;
	public static int game1 ;
	
	
	public JTextArea getuserListArea() {
		return userListArea;
	}
	
	

	public JTextArea getChatArea() {
		return chatArea;
	}



	LoginUser(User user,Socket socket,GameMainFrame game){
		this.user = user;
		this.socket = socket;
		this.userListArea = game.userListArea;
		this.chatArea = game.chatArea;
		this.nickName = user.getName();
		this.gamePanel = game.centerPanel;
		
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		recieveThread = new Thread(()->{
					
			while(true){
				boolean isEnd = recieve();
				if(isEnd == true) {
					break;
				}
			}
			
		}); 
				
		recieveThread.start();
		
		game1checkThread = new Thread(()-> {
			while(true) {
				if(upup()) {
					
					try {
						oos.writeObject(new Message(nickName,"",Message.CLOSE));
						oos.flush();
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		game1checkThread.start();
				
	}
	
	public User getUser() {
		return user;
	}



	public Socket getSocket() {
		return socket;
	}



	private void gameclose() {
		
		((JButton)gamePanel.getComponent(1)).setEnabled(false);
		((JButton)gamePanel.getComponent(1)).setText("잠김");
		
	}
	
	private boolean recieve() {
		try {
			Message message = (Message) ois.readObject();
			
			nameList = message.getNameList();
			
			switch(message.getType()) {
				case Message.START : 
					
					userListArea.setText("");
					
					for(String s : nameList) {
						userListArea.append(s +"\n");
					}
					break;
				case Message.SEND :	
					chatArea.append(message.getNickName()+":"+message.getMsg()+"\n");
					break;
				case Message.EXIT :	oos.close();	ois.close();	socket.close();	return true;
				
				case Message.CLOSE :
					gameclose();
					break;
				case Message.PLAY :
					play();
					break;
				case Message.GAMEING :
					gameing();
					break;
					
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public void gameing() {
		
		
		
	}



	public void play() {

		try {
			oos.writeObject(new Message(nickName,"",Message.PLAY));
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void exit() {

		try {
			oos.writeObject(new Message(nickName,"",Message.EXIT));
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void send(String msg) {
		Message message = new Message(nickName, msg, Message.SEND);
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login() {
		
		try {
			
			oos.writeObject(new Message(nickName,"",Message.START));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean upup() {
		if(game1 == 2) {
			
			return true;
		}
		return false;
	}
	
	
	

	public String getNickName() {
		return nickName;
	}
	
	
	
}
