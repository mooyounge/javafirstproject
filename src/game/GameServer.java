package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class GameServer {

	ArrayList<Waiter> waiterList ;
	ObjectInputStream is;
	ObjectOutputStream os;
	Vector<String> nameList;  
	
	ArrayList<Waiter> playerList;
	
	private void run() {
		waiterList = new ArrayList<>();
		nameList = new Vector<>();
		playerList = new ArrayList<>();
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
			
			System.out.println("Server Start at localhost:5001");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			Socket socket ;
			try {
				socket = serverSocket.accept();
				
								
				
				
				Waiter waiter = new Waiter(waiterList,nameList,socket,playerList);
				waiterList.add(waiter);
				
				
				waiter.start();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public static void main(String[] args) {
		new GameServer().run();
	}

}
