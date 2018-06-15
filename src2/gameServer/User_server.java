package gameServer;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import gameClient.ClientFrame;




public class User_server extends Thread{
	ObjectInputStream is;
	ObjectOutputStream os;
	ArrayList<User_server> userList;
	Socket socket ;
	
	//랜덤함수 발생부분
	Point p ;
	ClientFrame cf;
	boolean random = true;


	public User_server(Socket socket,ArrayList<User_server> userList) {
		this.socket = socket;
		this.userList = userList;
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				
				Message message = (Message)is.readObject();
				
				switch(message.getType()){
					case Message.LEFT : 
						move(message);
						break;
					
					case Message.RIGHT : 
						move(message);
						break;
						
					case Message.EXIT:
						exit(message);
						return;
					
					case Message.STARTUSERSET :
						 userset();
						 break;
				
					case Message.END :
						random = false;
						move(message);
						break;
						
					case Message.GAMEING :
						//random = true;
						//randomNum(message);
						break;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void userset() throws IOException {
		
		userList.get(0).os.writeObject(new Message(Message.STARTUSERSET,Message.USER1));
		userList.get(1).os.writeObject(new Message(Message.STARTUSERSET,Message.USER2));
		
	}

	public void move(Message message) throws IOException {
		
		for(User_server u : userList) {
			
			try {
				u.os.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void exit(Message message) throws IOException {
			
			
			userList.remove(this);
			os.writeObject(message);
			os.close();
			is.close();
			socket.close();
	}
	
	private void randomNum(Message message) throws IOException{
		
		new Thread(()-> {
			while(random) {
				p = new Point();
				//p.x = (int)(Math.random()*cf.getSize().width);
				p.x = (int)(Math.random()*500);
				message.setP(p);
				
				for(User_server u : userList) {
					try {
						u.os.writeObject(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try{
					Thread.sleep(3);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		
		 
	}
	
	
	
}
