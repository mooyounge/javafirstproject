package gameServer;

import java.awt.Point;
import java.io.Serializable;

import gameClient.ClientFrame;

public class Message implements Serializable{

	private int type;

	private int user =0;
	
	// 랜덤 포인트 부분. 동일하게 송출하기 위해서
	private ClientFrame cf;
	Point p;
	
//type
	public static final int MOVE = 1;

	public static final int LEFT = 2;
	
	public static final int RIGHT = 3;
	
	public static final int EXIT = 0;
	
	public static final int STARTUSERSET = 4;
	
	public static final int END = 5;
	
	public static final int GAMEING = 6;

	
//user	
	public static final int USER1 = 1;
	public static final int USER2 = 2;
	
	
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public Message(){
		
	}
	
	
	public Message(int type){
		this.type = type;
	}
	public Message(int type,int user){
		this.type = type;
		this.user = user;
	}
	
	
	public ClientFrame getCf() {
		return cf;
	}
	public void setCf(ClientFrame cf) {
		this.cf = cf;
	}
	
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	
	
	
	
}
