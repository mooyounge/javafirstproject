package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Message implements Serializable{
	private String nickName;
	private String msg;
	private int type;
	private Vector<String> nameList;

	
	
	public Vector<String> getNameList() {
		return nameList;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setNameList(Vector<String> nameList) {
		this.nameList = nameList;
	}

	public static final int START = 0;
	
	public static final int SEND = 1;
	
	public static final int EXIT = 2;
	
	public static final int CLOSE = 3;

	public static final int PLAY = 4;

	public static final int GAMEING = 5;

	public Message() {
	}
	public Message(int type) {
		this.type = type;
	}
	public Message(String nickName, String msg, int type) {
		this.nickName = nickName;
		this.msg = msg;
		this.type = type;
	}

	public String getNickName() {
		return nickName;
	}

	public String getMsg() {
		return msg;
	}

	public int getType() {
		return type;
	}
	
}
