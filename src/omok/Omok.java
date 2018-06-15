package omok;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Omok extends JFrame{
	
	//바둑판 만들기
	JPanel centerPanel;
	JLabel badookpan;
	ImageIcon img;
	
	//바둑돌이 들어가는 포인트
	Point p;
	
	public static final int ROWS = 18;
	public static final int COLS = 18;
	
	//바둑돌들의 배열. 생성 -> 
	public Doll[][] dolls = new Doll[ROWS][COLS];
	
	public Omok() {
		setTitle("Main Frame");
		img = new ImageIcon("src/omok/badookpan.jpg");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 300, 720, 720);
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
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				System.out.println(e.getPoint());
			}
			
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	public void initPanel() {
		centerPanel = new JPanel();
		add(centerPanel);
		
		
	}

	public void initComponent() {
		makepan();
		makeDoll();
	}

	private void makeDoll() {
		
		p = new Point(38,58);
		
		
		for(int i = 0 ; i<ROWS;i++) {
			for(int j = 0 ; j<COLS;j++) {
				p = new Point((i+1)*38,(j+1)*58);
				Doll doll = new Doll(p);
				dolls[i][j] = doll;
			}
		}
		
	}

	private void makepan() {
		badookpan = new JLabel(img);
		centerPanel.add(badookpan);
		
		
	}

	public static void main(String[] args) {
		new Omok();
	}
	
}
