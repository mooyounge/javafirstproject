package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrame extends JFrame {

	//South
	JPanel outpanel;
	JPanel idpanel;
	JPanel pwdpanel;
	JPanel loginpanel;
	
	JLabel id;
	JLabel pwd;
	JTextField idfield;
	JPasswordField pwdfield;
	JButton loginBtn;
	JButton signupBtn;
	

	
	ObjectOutputStream oos ;
	
	
	ArrayList<User> users = new ArrayList<>();
	
	public LoginFrame() {
		setTitle("로그인");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 300, 500, 500);
		setResizable(false);

		init();

		setVisible(true);
	}

	public void init() {
		initComponent();
		initPanel();
		initEvent();
	}
	
	public void login() {

		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("users.inc"));
			users = (ArrayList<User>) ois.readObject();
			
			String id = idfield.getText();
			String pwd = new String(pwdfield.getPassword()) ;
			
			for(int i = 0; i<users.size();i++) {
				if(users.get(i).getId().equals(id) && users.get(i).getPwd().equals(pwd) ) {
					
					JOptionPane.showMessageDialog(null, "로그인 되셨습니다");
					Socket socket = new Socket();
					try {
						
						socket.connect(new InetSocketAddress("localhost", 5001));
						
					}catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"\n 잠시후 다시 시도해 주세요.");
						System.exit(0);
					}
					
					
					new GameMainFrame(users.get(i) ,socket);
					
					
					
					
					
					
					dispose();
					return;
					
				}
			}
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	
	}
	private void initEvent() {
		idfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !idfield.getText().equals("")) {
					pwdfield.requestFocus();
				}
			}
		});
		
		pwdfield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pwdfield.getPassword().equals("")) {
					loginBtn.requestFocus();
				}
				
			}
			
			
		});
		
		loginBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				login();
			}
			
			
		});
	
		loginBtn.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
			
			
		});
		
		signupBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				SignUpFrame sp = new SignUpFrame();
			}

			
			
			
		});
		
	
	
	
	
	}	

	private void initPanel() {
	
	outpanel = new JPanel(new GridLayout(3,1));
	idpanel = new JPanel(new FlowLayout(2));
	pwdpanel = new JPanel(new FlowLayout(2));
	loginpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	idpanel.add(id);
	idpanel.add(idfield);
	pwdpanel.add(pwd);
	pwdpanel.add(pwdfield);
	loginpanel.add(signupBtn);
	loginpanel.add(loginBtn);
	
	outpanel.add(idpanel);
	outpanel.add(pwdpanel);
	outpanel.add(loginpanel);
	add(outpanel,BorderLayout.SOUTH);
	
	}

	public void initComponent() {
		idfield = new JTextField(8);
		pwdfield = new JPasswordField(8);
		id = new JLabel("ID:");
		pwd = new JLabel("PWD:");
		loginBtn = new JButton("Login");
		
		signupBtn = new JButton("회원가입");
		
		
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
