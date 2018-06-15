package gameServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class GameServer {
	ArrayList<User_server> userList;
	
	private void run() {
		
		ServerSocket serverSocket = null;
		userList = new ArrayList<>();
		
		try {
			
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
			
			System.out.println("서버 시작");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			Socket socket ;
			User_server user;
			try {
				socket = serverSocket.accept();
				
				user = new User_server(socket,userList);
				
				userList.add(user);
				
				user.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		new GameServer().run();
	}
	
}
