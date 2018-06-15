package game;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class SignUpFrame extends JFrame {
	
	JLabel id;
	JLabel pwd;
	JLabel pwdcheck;
	JLabel name;
	JTextField idfield;
	JPasswordField pwdfield;
	JPasswordField pwdcheckfield;
	JTextField namefield;
	JButton sign;
	JButton resign;
	
 public SignUpFrame() {

		setTitle("회원가입");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 300, 200, 200);
		setResizable(false);
		setLayout(new GridLayout(5,2));

		init();

		setVisible(true);


 }
 
 public void run() {
	 
 }

 public void init() {
	
	initComponent();
	initEvent();
	
 }
  boolean checkpwd() {
	 if(!new String(pwdfield.getPassword()).equals(new String(pwdcheckfield.getPassword()))||new String(pwdfield.getPassword()).equals("") || new String(pwdcheckfield.getPassword()).equals("") ) {
			JOptionPane.showMessageDialog(null, "입력하신 비번이 다르거나 공백입니다.");
			pwdfield.setText("");
			pwdcheckfield.setText("");
			pwdfield.grabFocus();
			return	true;
	 }else {
		 return false;
	 }
 }

 private void initEvent() {
	 sign.addMouseListener(new MouseAdapter() {
		 
			
		 
		@Override
		public void mouseClicked(MouseEvent arg0) {
				if(checkpwd()) {
					return;	
				}else {
				 ObjectInputStream ois = null;
				 ObjectOutputStream oos = null;
				 
				 try {
					ois = new ObjectInputStream(new FileInputStream("users.inc"));
					ArrayList<User> users = null;
					try {
						users = (ArrayList<User>) ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					User u1 = new User();
					u1.setId(idfield.getText());
					u1.setPwd(new String(pwdfield.getPassword()));
					u1.setName(namefield.getText());
					
					
					for(User u : users) {
						if(u.getId().equals(idfield.getText())){
							JOptionPane.showMessageDialog(null, "이미 등록된 아이디 입니다.");
							idfield.setText("");
							idfield.grabFocus();
							return;
						}
					}	

					users.add(u1);
					oos = new ObjectOutputStream(new FileOutputStream("users.inc"));
					oos.writeObject(users);
					oos.close();
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					dispose();
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			  }
			}
		
	 	});
	
	 resign.addMouseListener(new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			idfield.setText(null);
			pwdfield.setText(null);
			pwdcheckfield.setText(null);
			namefield.setText(null);
			idfield.grabFocus();
		}
		 
	});
	 
	 
 }

 public void initComponent() {
	
	id = new JLabel("아이디:");
	pwd = new JLabel("패스워드:");
	pwdcheck = new JLabel("패스워드 확인");
	name = new JLabel("이름:");
	idfield = new JTextField(10);
	pwdfield = new JPasswordField(10);
	pwdcheckfield = new JPasswordField(10);
	namefield = new JTextField(10);
	sign = new JButton("회원가입");
	resign = new JButton("다시입력");
	
	
	add(id);
	add(idfield);
	add(pwd);
	add(pwdfield);
	add(pwdcheck);
	add(pwdcheckfield);
	add(name);
	add(namefield);
	add(sign);
	add(resign);
	
 }

 public static void main(String[] args) {
	new SignUpFrame();
 }
}
