package gameClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import gameServer.Message;

public class User_client{
	
	ObjectOutputStream os;
	ObjectInputStream is;
	Socket socket;
	Man man;
	Thread messageThread;
	ClientFrame cf;
	int user;
	User_client(Socket socket,ClientFrame cf){
		
		this.man = cf.man;
		this.socket = socket;
		this.cf = cf;
		try {
			
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		messageThread = new Thread(()->{
			
			while(true){
				boolean isEnd = recieve();
				
				if(isEnd == true) {
					break;
				}
				
			}
		}); 
	
		messageThread.start();
		
	}

	private boolean recieve() {
		try {
			Message message = (Message) is.readObject();
			
			switch(message.getType()) {
			case Message.LEFT : 
				if(message.getUser() == 1) {
					left(message);	
				}else if(message.getUser() == 2) {
					twoleft(message);
				}
				 
				break;
			case Message.RIGHT : 
				if(message.getUser() == 1) {
					right(message);	
				}else if(message.getUser()==2) {
					tworight(message);
				}
				 
				break;
			case Message.EXIT :	os.close();	is.close();	socket.close();	return true;
				
			case Message.STARTUSERSET : 
				
				cf.startBtn.setEnabled(false);
				cf.hi=true;
				cf.start();
				user = message.getUser();
				break;
			
			case Message.END :
				end();
				break;
				
			case Message.GAMEING :
				// cf.p.x= message.getP().x;
				break;
			
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	private void left(Message message) {
		
			man.p.x -= 10;
		
	}
	private void twoleft(Message message) {
	
			cf.manp2.x -= 10;
		
	}
	
	private void right(Message message) {
		
			man.p.x += 10;
		
	}
	private void tworight(Message message) {
		
			cf.manp2.x += 10;
		
	}
	
	public void userset() {
		
		try {
			os.writeObject(new Message(Message.STARTUSERSET));
			os.writeObject(new Message(Message.GAMEING));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void exit() {
		
		try {
			os.writeObject(new Message(Message.EXIT));
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void end() {
		
		
		cf.hi = false;
		cf.ti.stop();
		cf.canvaspanel.getGraphics().clearRect(0, 0, cf.canvaspanel.getWidth(), cf.canvaspanel.getHeight());
		JOptionPane.showMessageDialog(null, "1P가 이겼습니다!");
		cf.startBtn.setEnabled(true);
		
	}
	
	
	
}
