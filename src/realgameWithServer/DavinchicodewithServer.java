package realgameWithServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.LoginUser;
import game.User;

public class DavinchicodewithServer extends JFrame{
	
	ArrayList<Integer> blackcard;
	ArrayList<Integer> whitecard;
	
	ArrayList<Integer> user1;
	ArrayList<Integer> user2;
	
	int color;
	
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	public static final int FIRST = 0;
	public static final int SECOND = 1;
	
	
	
	JPanel centerPanel;
	JPanel westPanel;
	JPanel eastPanel;
	
	JPanel user1Panel;
	JButton user1cardBtn1;
	JButton user1cardBtn2;
	JButton user1cardBtn3;
	JButton user1cardBtn4;
	
	JPanel user2Panel;
	JButton user2cardBtn1;
	JButton user2cardBtn2;
	JButton user2cardBtn3;
	JButton user2cardBtn4;
	
	JTextArea textArea;
	JTextField textField;
	
	JButton blackBtn;
	JButton whiteBtn;
	
	ArrayList<Card> cardList;
	
	JLabel user1Label;
	JLabel user2Label;
	JButton startBtn; 
	
	int choice;
	int check = 1 ;
	
	JTextArea chatArea;
	JTextField chatField;
	JButton sendBtn;
	
	LoginUser loginuser;
	
	JPanel chatPanel;
	
	JTextArea userListArea;
	
	User user;
	Socket socket;
	
	public DavinchicodewithServer(LoginUser loginuser) {
		
		this.loginuser = loginuser;
		this.user = loginuser.getUser();
		this.socket = loginuser.getSocket();
		chatArea = loginuser.getChatArea();
		userListArea = loginuser.getuserListArea();
		
		
		
		setTitle("다빈치 코드");
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
		
		/*sendBtn.addMouseListener(new MouseAdapter() {

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
		});*/
		
		
		blackBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				if(blackBtn.isEnabled()) {
					pickupblack();
				}
				
			}
			
		});
		
		whiteBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
			
				if(whiteBtn.isEnabled()) {
					pickupwhite();
				}
				
			}
			
		});
		
		startBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				whiteBtn.setEnabled(true);
				blackBtn.setEnabled(true);
				initGame();
				
			}

			
			
			
		});
		
		
		MouseListener cardml = new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if( ((JButton)e.getComponent()).getText().equals(textField.getText())) {
					
					e.getComponent().setVisible(false);
					
					textField.setText("");
					
				}
			}
			
		};
		
		
		user1cardBtn1.addMouseListener(cardml);
		user1cardBtn2.addMouseListener(cardml);
		user1cardBtn3.addMouseListener(cardml);
		user1cardBtn4.addMouseListener(cardml);
		user2cardBtn1.addMouseListener(cardml);
		user2cardBtn2.addMouseListener(cardml);
		user2cardBtn3.addMouseListener(cardml);
		user2cardBtn4.addMouseListener(cardml);
		
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
	}

	public void initPanel() {	
		centerPanel = new JPanel(new BorderLayout());
		westPanel = new JPanel(new GridLayout(4,1));
		eastPanel = new JPanel();
		
		chatPanel = new JPanel(new FlowLayout());
		
		user1Panel = new JPanel();
		user2Panel = new JPanel();
		
		centerPanel.add(user1Panel,BorderLayout.NORTH);
		centerPanel.add(user2Panel,BorderLayout.SOUTH);
		
		add(centerPanel,BorderLayout.CENTER);
		add(westPanel,BorderLayout.WEST);
		add(eastPanel,BorderLayout.EAST);
		
		
	}

	public void initComponent() {
		user1Label = new JLabel("유저1");
		user1cardBtn1 = new JButton("임시1");
		user1cardBtn2 = new JButton("임시2");
		user1cardBtn3 = new JButton("임시3");
		user1cardBtn4 = new JButton("임시4");
		
		user1Panel.add(user1Label);
		user1Panel.add(user1cardBtn1);
		user1Panel.add(user1cardBtn2);
		user1Panel.add(user1cardBtn3);
		user1Panel.add(user1cardBtn4);
		
		user1cardBtn1.setBackground(Color.gray);
		user1cardBtn2.setBackground(Color.gray);
		user1cardBtn3.setBackground(Color.gray);
		user1cardBtn4.setBackground(Color.gray);
		
		
		user2Label = new JLabel("유저2");
		user2cardBtn1 = new JButton("임시5");
		user2cardBtn2 = new JButton("임시6");
		user2cardBtn3 = new JButton("임시7");
		user2cardBtn4 = new JButton("임시8");
		
		user2Panel.add(user2Label);
		user2Panel.add(user2cardBtn1);
		user2Panel.add(user2cardBtn2);
		user2Panel.add(user2cardBtn3);
		user2Panel.add(user2cardBtn4);
		
		user2cardBtn1.setBackground(Color.gray);
		user2cardBtn2.setBackground(Color.gray);
		user2cardBtn3.setBackground(Color.gray);
		user2cardBtn4.setBackground(Color.gray);
		
		//게임메인프레임과연관
		//chatArea.setEnabled(false);
		//sendBtn = new JButton("입력");
		
		//chatField = new JTextField();
		
		//chatPanel.add(chatField);
		//chatPanel.add(sendBtn);
		
		
		
		//userListArea = new JTextArea(10,20);
		//userListArea.setEnabled(false);
		
		//eastPanel.add(userListArea,BorderLayout.NORTH);
		//eastPanel.add(new JScrollPane(chatArea),BorderLayout.CENTER);
		//eastPanel.add(chatPanel,BorderLayout.SOUTH);
		
		//여기까지
		
		
		textArea = new JTextArea(20,20);
		textField = new JTextField(5);
		
		//textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setForeground(Color.black);
		whiteBtn = new JButton("화이트");
		blackBtn = new JButton("블랙");
		whiteBtn.setEnabled(false);
		blackBtn.setEnabled(false);
		
		startBtn = new JButton("START");
		eastPanel.add(startBtn);
		
		
		westPanel.add(new JScrollPane(textArea));
		westPanel.add(textField);
		westPanel.add(whiteBtn);
		westPanel.add(blackBtn);
		
		cardList = new ArrayList<>();
	}
	
	private void initGame() {
		
		new Thread(()->{
			start();
			user1setup();
			check=2;
			user2setup();
			check = 0;
			go();
			play();
		}).start();
		
		
	}
	
	private void play() {
		
		// 첫번째 유저가 플레이
		textArea.append("첫번째유저 차례입니다");
		textArea.append("숫자를 입력하고 맞추고싶은 카드를 선택해 주세요");
		
		
		
		
	}

	private void go() {
		
		Collections.sort(user1);
		Collections.sort(user2);
		
		user1cardBtn1.setText(""+user1.get(0));
		
		user1cardBtn2.setText(""+user1.get(1));
		user1cardBtn3.setText(""+user1.get(2));
		user1cardBtn4.setText(""+user1.get(3));

		for(int i=0; i < 4 ; i ++) {
			if(user1cardBtn1.getText().equals(String.valueOf(cardList.get(i).getNumber())) && user1cardBtn1.getBackground() == Color.gray){
				user1cardBtn1.setBackground(cardList.get(i).getC());
				user1cardBtn1.setForeground(cardList.get(i).getFontc());
				
			}else
			if(user1cardBtn2.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user1cardBtn2.getBackground() == Color.gray ){
				user1cardBtn2.setBackground(cardList.get(i).getC());
				user1cardBtn2.setForeground(cardList.get(i).getFontc());
			}else
			if(user1cardBtn3.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user1cardBtn3.getBackground() == Color.gray ){
				user1cardBtn3.setBackground(cardList.get(i).getC());
				user1cardBtn3.setForeground(cardList.get(i).getFontc());
			}else
			if(user1cardBtn4.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user1cardBtn4.getBackground() == Color.gray ){
				user1cardBtn4.setBackground(cardList.get(i).getC());
				user1cardBtn4.setForeground(cardList.get(i).getFontc());
			}
		};
		
		user2cardBtn1.setText(""+user2.get(0));
		user2cardBtn2.setText(""+user2.get(1));
		user2cardBtn3.setText(""+user2.get(2));
		user2cardBtn4.setText(""+user2.get(3));
		
		for(int i=4; i < 8 ; i ++) {
			if(user2cardBtn1.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user2cardBtn1.getBackground() == Color.gray ){
				user2cardBtn1.setBackground(cardList.get(i).getC());
				user2cardBtn1.setForeground(cardList.get(i).getFontc());
			}else
			if(user2cardBtn2.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user2cardBtn2.getBackground() == Color.gray ){
				user2cardBtn2.setBackground(cardList.get(i).getC());
				user2cardBtn2.setForeground(cardList.get(i).getFontc());
			}else
			if(user2cardBtn3.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user2cardBtn3.getBackground() == Color.gray ){
				user2cardBtn3.setBackground(cardList.get(i).getC());
				user2cardBtn3.setForeground(cardList.get(i).getFontc());
			}else
			if(user2cardBtn4.getText().equals(String.valueOf(cardList.get(i).getNumber()))&& user2cardBtn4.getBackground() == Color.gray ){
				user2cardBtn4.setBackground(cardList.get(i).getC());
				user2cardBtn4.setForeground(cardList.get(i).getFontc());
			}
		};
		
		
	}
	
	public void user1setup() {
		
		textArea.append("첫번째 유저의 카드를 뽑아주세요 \n");
		
		while(user1.size()!=4) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void user2setup() {
		textArea.append("두번째 유저의 카드를 뽑아주세요 \n");
		while(user2.size()!=4) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		
		blackcard = new ArrayList<>();
		whitecard = new ArrayList<>();
		user1 = new ArrayList<>();
		user2 = new ArrayList<>();
		
		for(int i = 0 ; i<12;i++) {
			blackcard.add(i);
		}
		for(int i = 0 ; i<12;i++) {
			whitecard.add(i);
		}
		
	}
	
	public void pickupblack() {
		
		if(check==1) {
			int i = (int)(Math.random()*(blackcard.size()-1));
			
			user1.add(blackcard.get(i));
			
			Card card = new Card();
			card.setNumber(blackcard.get(i));
			card.setC(Color.black);
			card.setFontc(Color.white);
			cardList.add(card);
			
			
			blackcard.remove(i);
			
			System.out.println("블랙 유저1");
			
		}
		if(check==2) {
			int i = (int)(Math.random()*(blackcard.size()-1));
			user2.add(blackcard.get(i));
			
			Card card = new Card();
			card.setNumber(blackcard.get(i));
			card.setC(Color.black);
			card.setFontc(Color.white);
			cardList.add(card);
			
			blackcard.remove(i);
			System.out.println("블랙 유저2");
		}
			
			
	}
			
		
public void pickupwhite() {
		if(check==1) {
			int i = (int)(Math.random()*(whitecard.size()-1));
			
			user1.add(whitecard.get(i));
			
			Card card = new Card();
			card.setNumber(whitecard.get(i));
			card.setC(Color.WHITE);
			card.setFontc(Color.black);
			cardList.add(card);
			
			whitecard.remove(i);
			System.out.println("화이트 유저1");
		}
			
			
			if(check==2) {
				int i = (int)(Math.random()*(whitecard.size()-1));
				user2.add(whitecard.get(i));
				
				Card card = new Card();
				card.setNumber(whitecard.get(i));
				card.setC(Color.WHITE);
				card.setFontc(Color.black);
				cardList.add(card);
				
				whitecard.remove(i);
				System.out.println("화이트 유저2");
			}
		
	}
	
	
	
}
