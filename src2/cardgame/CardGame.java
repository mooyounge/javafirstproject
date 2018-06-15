package cardgame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameClient.User_client;
import gameServer.Message;

public class CardGame extends JFrame {

	//도둑잡기 
	
	//카드뒷면이미지
	ImageIcon img = new ImageIcon("src2/gameClient/card.jpg");
	
	//유저1카드
	JLabel cardLabel1;
	JLabel cardLabel2;
	
	//유저2카드
	JLabel cardLabel3;
	JLabel cardLabel4;
	
	//카드생성버튼
	JButton cardBtn;
	
	JPanel cardPanel;
	
	User_client user;
	Message message;
	Socket socket;
	
	
	public CardGame() {
		setTitle("Main Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 300, 500, 500);
		setResizable(false);

		init();
		
		setVisible(true);
	}

	public void init() {
		initPanel();
		initComponent();
		initEvent();
	}

	private void initPanel() {
		cardPanel = new JPanel(new FlowLayout());
		add(cardPanel,BorderLayout.CENTER);
	}

	private void initEvent() {
		//카드생성 버튼
		cardBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				makecard();
				setVisible(true);
				requestFocus();
			}
		});
		
		//카드지워보기
		cardLabel1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				cardLabel1.setVisible(false);
			}
			
		});
	}

	public void initComponent() {
		//카드버튼
		cardBtn = new JButton("생성");
		cardPanel.add(cardBtn);
		
		//유저카드 미리정의
		cardLabel1 = new JLabel(img);
		Card card1 = new Card();
		cardLabel2 = new JLabel(img);
		Card card2 = new Card();
		//유저2카드 미리정의
		cardLabel3 = new JLabel(img);
		Card card3 = new Card();
		cardLabel4 = new JLabel(img);
		Card card4 = new Card();
	}
	
	
	//카드생성
	private void makecard() {
		//유저1카드
		cardPanel.add(cardLabel1);
		cardPanel.add(cardLabel2);
		
		//유저2카드
		cardPanel.add(cardLabel3);
		cardPanel.add(cardLabel4);
		
	}
	
	public static void main(String[] args) {
		new CardGame();
	}
	
}
