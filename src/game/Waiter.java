package game;

import java.awt.TextArea;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;



//waiterList 는 현재 접속한 사람임. 그사람들의 소켓이 있다. 로그인유저랑 


public class Waiter extends Thread{
	
	ArrayList<Waiter> waiterList;
	Socket socket;
	ObjectInputStream is;
	ObjectOutputStream os;
	TextArea nicknameArea;
	Vector<String> nameList;
	
	ArrayList<Waiter> playerList;
	
	public Waiter(ArrayList<Waiter> waiterList,Vector<String> nameList, Socket socket,ArrayList<Waiter> playerList) {

		this.waiterList = waiterList;
		this.socket = socket;
		this.nameList = nameList;
		this.playerList = playerList;
		
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
			
	

	@Override
	public void run() {
		try {
			
			
			while(true) {
				
				Message message = (Message)is.readObject();
				
				String msg = message.getMsg();
				
				
				
				switch(message.getType()) {
					case Message.START : login(message); break;
					case Message.SEND : send(message); break;
					case Message.EXIT : exit(message);	return;
					case Message.CLOSE : close(message); break;
					case Message.PLAY : play(message); break;
					case Message.GAMEING : gameing(message); break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	

	
	
	
	private void gameing(Message message) throws IOException {
		
		for(Waiter w : playerList) {
			w.os.writeObject(message);
		}
		
	}



	private void play(Message message) throws IOException{
			playerList.add(this);
			message.setType(message.GAMEING);
			for(Waiter w : playerList) {
				w.os.writeObject(message);
			}
			
	}



	private void exit(Message message) throws IOException {
		
		waiterList.remove(this);
		
		nameList.remove(message.getNickName());
		
		message.setNameList((Vector<String>)nameList.clone());
		
		message.setType(message.START);
		
		for(Waiter w : waiterList) {
			w.os.writeObject(message);
		}
		
		message.setType(message.EXIT);
		
		os.writeObject(message);

		is.close();
		socket.close();
	}
	
	
	
	
	private void login(Message message) throws IOException {
		
		nameList.add(message.getNickName());
		
		message.setNameList((Vector<String>)nameList.clone());
	
			for(Waiter w : waiterList) {
				w.os.writeObject(message);
			}
		
		
		
	}
	
	
	
	private void send(Message message) throws IOException {
		for(Waiter w : waiterList) {
			w.os.writeObject(message);
		}
		
	}
	
	private void close(Message message) throws IOException {
		
		for(Waiter w : waiterList) {
			w.os.writeObject(message);
		}
		
	}
	
	
	
	
	
	
	
	
}
