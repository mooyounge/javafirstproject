package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.inc.finalProjcet.finalProject1;
import com.inc.finalProjcet.finalProject2;
import com.inc.finalProjcet.finalProject3;

import omok.Omok;
import realgameWithServer.DavinchicodewithServer;

public class GameMainFrame extends JFrame {
	
	JPanel centerPanel;
	JPanel southPanel;
	JPanel eastPanel;
	JPanel chatPanel;
	User user;
	JLabel userlabel;
	LoginUser loginuser;
	JTextArea userListArea;
	
	JTextArea chatArea;
	JButton sendBtn;
	JTextField chatField;
	
	JButton davinchBtn;
	
	Socket socket;
	
	// 파이널 프로젝트 버튼3개
	JButton a;
	JButton b;
	JButton c;
	
	// 오목버튼
	JButton omokBtn;
	
	public GameMainFrame(User user,Socket socket) {
		this.user = user;
		this.socket = socket;
		
		
		setTitle("미니 게임 파티");
		setBounds(300, 300, 700, 700);
		setResizable(false);

		init();

		setVisible(true);
	}

	public void init() {
		initPanel();
		
		initComponent();
		
		initEvent();
	}
	
	private void initEvent() {
		
		sendBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				loginuser.send(chatField.getText());
				chatField.setText(null);
			}
		});
		
		
		chatField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginuser.send(chatField.getText());
					chatField.setText(null);
				}
				
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				loginuser.exit();
				System.exit(0);
			}
		});
		
		
		davinchBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				loginuser.game1 += 1;

				new DavinchicodewithServer(loginuser);
				
				
			}

		});
		
		
		//finalproject1,2,3
		a.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				new finalProject1();
			}
			
		});
		
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				new finalProject2();
			}
			
		});
		
		c.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				new finalProject3();
			}
			
		});
		
		//오목버튼누를때
		omokBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				new Omok();
			}
				
			
		});
		
		
	}

	public void initPanel() {
		
		centerPanel = new JPanel(new FlowLayout());
		southPanel = new JPanel(new FlowLayout());
		eastPanel = new JPanel(new BorderLayout());
		chatPanel = new JPanel(new FlowLayout());
		
		
		southPanel.setBackground(Color.LIGHT_GRAY);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
		add(eastPanel,BorderLayout.EAST);
		
		
		
		
	}

	public void initComponent() {
		
		userlabel = new JLabel(user.getName());
		southPanel.add(userlabel);
		
		userListArea = new JTextArea(10,20);
		userListArea.setEnabled(false);
		
		chatArea = new JTextArea();
		chatArea.setEnabled(false);
		sendBtn = new JButton("입력");
		chatField = new JTextField(8);
		
		chatPanel.add(chatField);
		chatPanel.add(sendBtn);
		eastPanel.add(userListArea,BorderLayout.NORTH);
		eastPanel.add(new JScrollPane(chatArea),BorderLayout.CENTER);
		eastPanel.add(chatPanel,BorderLayout.SOUTH);
		
		davinchBtn = new JButton("다빈치코드");
		
		//오목버튼
		omokBtn = new JButton("오목");
		centerPanel.add(omokBtn);
		centerPanel.add(davinchBtn);

		
		loginuser = new LoginUser(user,socket,this);
		
		loginuser.login();
		
		
		a = new JButton("오늘의 운세");
		b = new JButton("행복 심리테스트");
		c = new JButton("타로카드");
		
		
		centerPanel.add(a);
		centerPanel.add(b);
		centerPanel.add(c);
		
		
		
	}
	
	
	
}
