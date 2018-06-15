package gameClient;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gameServer.Message;


public class ClientFrame extends JFrame {

	Image manImg;
	JPanel canvaspanel;
	Point manp;
	Point manp2;
	Man man;
	KeyEvent e;
	User_client user;
	Message message;
	Socket socket;
	
	JButton startBtn;
	
	//공떨어지는것과 관계되는부분
	Vector<Point> pointList;
	double distance;
	double distance2;
	Timer ti;
	boolean hi = true;
	
	//공이 랜덤으로 떨어지는것
	Point p ;
	
	public ClientFrame() {
		setTitle("Main Frame");
		setBounds(300, 300, 500, 500);
		setResizable(false);

		init();

		setVisible(true);
		
	}

	public void init() {
		pointList = new Vector<>();
		initComponent();
		initEvent();
		
		login();
	}
	
	public void start() {
		initman();
		
		 ti = new Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gongstart();				
			}
		});
		
		ti.start();
		
		initmanEvent();
	}
	private void initEvent() {
		startBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				user.userset();
				canvaspanel.getGraphics().clearRect(0, 0, canvaspanel.getWidth(), canvaspanel.getHeight());
				hi=true;
				//start(); 이것도 서버에서 보내줘야대는데 -> User_client에서 보내줫음
				requestFocus();
				
			}
			
		});
		
	}

	private void initmanEvent() {
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode()== KeyEvent.VK_RIGHT ) {
					
					right();
					
				}
				if( e.getKeyCode() == KeyEvent.VK_LEFT ) {
					left();
					
				}
			
			
			}
		});
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				user.exit();
				System.exit(0);
			}
		});
		
	}
	
	private void left() {
		
		try {
			 message = new Message(Message.LEFT,user.user);
			user.os.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void right() {
		message = new Message(Message.RIGHT,user.user);
		try {
			user.os.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void initman() {
		
		new Thread(()->{
			while(true) {
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				canvaspanel.repaint();
			
			}
			
		}).start();
	}
		

	public void initComponent() {
		
		imgstart();
		
		man = new Man(manp);
		
		startBtn = new JButton("시작");
		
		add(startBtn,BorderLayout.NORTH);
		
	}
	
	public void imgstart() {

		ImageIcon i = new ImageIcon("src2/gameClient/man.png");
		manImg = i.getImage();
		manp = new Point(0,200);
		manp2 = new Point(400,200);
		canvaspanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
				synchronized(pointList) {
				g.clearRect(0, 0, canvaspanel.getWidth(), canvaspanel.getHeight());
				for(Point p : pointList) {
					 g.fillOval(p.x-15, p.y-15, 30, 30);	
					 
				}
			}
				
				
				g.drawImage(manImg, manp.x, manp.y, this);
				g.drawImage(manImg, manp2.x, manp2.y, this);
			}
		};
		
		add(canvaspanel);
	
	}
	
	
	public void login() {
		
		socket = new Socket();
		try {
			
			socket.connect(new InetSocketAddress("localhost", 5001));
			
			
			user = new User_client(socket,this);
			
			
		}catch (IOException e1) {
			JOptionPane.showMessageDialog(null,"\n 잠시후 다시 시도해 주세요.");
			System.exit(0);
		}
	}
	
	public void gongstart () {
		
		new Thread(()->{
			
			p = new Point();
			
			p.x = (int)(Math.random()*getSize().width);
			pointList.add(p);
			
			boolean check = true;
			while(hi) {
				if(check) {
					p.y ++;	
				}
				
				if(p.y == getSize().height) {
					synchronized(pointList) {
						pointList.remove(p);	
					}
				}
				
				distance = Point.distance(p.x, p.y, manp.x, manp.y);
				distance2 = Point.distance(p.x, p.y, manp2.x, manp2.y);
				
				if( distance<20){
					//2피가이김
					message = new Message(Message.END,user.user);
					try {
						user.os.writeObject(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				if(distance2<20) {
					//1피가이김
					
					 message = new Message(Message.END,user.user);
						try {
							user.os.writeObject(message);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				}
				
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				canvaspanel.repaint();
				
			}
			
		}).start();
	}
	
	

	public static void main(String[] args) {
		new ClientFrame();
	}
}
