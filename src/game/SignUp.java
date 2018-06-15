package game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class SignUp {
	//초기화 코드입니다
	
	public static void main(String[] args) {
		ArrayList<User> userList = new ArrayList<>();
		User u1 = new User();
		u1.setId("admin");
		u1.setPwd("admin");
		u1.setName("운영자");
		
		
		userList.add(u1);
		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("users.inc"));
			oos.writeObject(userList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
