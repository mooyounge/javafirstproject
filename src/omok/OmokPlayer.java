package omok;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import game.Message;

public class OmokPlayer {

	//입출력관련
	ObjectOutputStream os;
	ObjectInputStream is;
	Socket socket;
	Omok omok;
	
	//메세지를 항상받는곳 
	Thread messageThread;
	

	
	//생성자
	OmokPlayer(Socket socket,Omok omok){
		this.socket = socket;
		this.omok = omok;
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
			case Message.EXIT :	os.close();	is.close();	socket.close();	return true;
				
			
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public void exit() {
		try {
		os.writeObject(new Message(Message.EXIT));
		os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
