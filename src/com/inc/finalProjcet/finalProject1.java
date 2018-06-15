package com.inc.finalProjcet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class finalProject1 extends JFrame {

	JLabel westLabel, southLabel, eastLabel;

	ImageIcon bigImg, bigImg2, bigImg3;

	JButton firstBtn, restartBtn;
	JTextArea printArea;

	JPanel northPanel;
	JPanel southPanel;
	JPanel centerPanel;
	JPanel eastPanel;
	JPanel westPanel;
	JComboBox<String> sBox, sBox2, sBox3, sBox4;

	public finalProject1() {
		setTitle("오늘의 운세");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(300, 300, 550, 550);
		setResizable(false);
		// setLayout(new FlowLayout());

		init();
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, "와 못 믿겠나?\n 아니면 운세 봤니?"
						, "종료", JOptionPane.YES_NO_OPTION);

				if (choice == 0) {
					//System.exit(0);
					dispose();
				}
			}

		});

		setVisible(true);
	}

	public void init() {
		initComponent();
		initPanel();
		initEvent();

	}

	private void initEvent() {

		firstBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				printResult();
			}
			
			

		});
		restartBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				printArea.setText(null);
			}
		});

	}
	
	private void printResult() {
		
		String t [] = {"아주 좋은 날입니다.\n 모든지 시작해 보세요.^^.",
						"수업을 열심히 들으시면\n 일이 잘 될거예요^^.", 
						"걱정하던일이 해결되는 날입니다.\n 주위사람한테\n 고마움을 표현해 보세요^^.",
						"오늘하루 음악을 들으면서\n 자중하셔야 되는 날입니다."};
		
		
			int rn = (int)(Math.random()*t.length);
			printArea.append(t[rn]);
		
	}
	private void initPanel() {
		northPanel = new JPanel();

		northPanel.add(sBox);
		northPanel.add(sBox2);
		northPanel.add(sBox3);
		northPanel.add(sBox4);
		northPanel.add(firstBtn);
		northPanel.add(restartBtn);

		centerPanel = new JPanel();

		centerPanel.add(printArea);
		centerPanel.setBackground(Color.GREEN);

		southPanel = new JPanel();
		southLabel.setIcon(bigImg);
		southPanel.add(southLabel);

		westPanel = new JPanel();
		westLabel.setIcon(bigImg2);
		westPanel.add(westLabel);

		/*eastPanel = new JPanel();
		eastLabel.setIcon(bigImg3);
		eastPanel.add(eastLabel);*/

		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		//add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
	}

	public void initComponent() {

		String[] y = { "1966년", "1967년", "1968년", "1969년", "1970년", "1971년", "1972년", "1973년", "1974년", "1975년",
				"1976년", "1977년", "1978년", "1979년", "1980년", "1981년", "1982년", "1983년", "1984년", "1985년", "1986년",
				"1987년", "1988년", "1989년", "1990년", "1991년", "1992년", "1993년", "1994년", "1995년", "1996년", "1997년",
				"1998년", "1999년" };
		sBox = new JComboBox(y);

		String[] m = { "1월", "2월", "3월", "4월", "5월", "6월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };

		sBox2 = new JComboBox(m);

		String[] d = { "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일", "15일",
				"16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일",
				"31일" };

		sBox3 = new JComboBox(d);

		String[] t = { "1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시", "13시", "14시", "15시",
				"16시", "17시", "18시", "19시", "20시", "21시", "22시", "23시", "24시" };

		sBox4 = new JComboBox(t);

		firstBtn = new JButton("운세보기");
		restartBtn = new JButton("다시보기");

		printArea = new JTextArea();
		printArea.setBackground(Color.WHITE);
		printArea.setForeground(Color.BLUE);
		printArea.setFont(new Font(null, 2, 20));
		// add(printArea);

		bigImg = new ImageIcon("src/com/inc/im/img26.gif");
		bigImg2 = new ImageIcon("src/com/inc/im/뱀.jpg");
		//bigImg3 = new ImageIcon("src/com/inc/im/운세도사.webp");

		southLabel = new JLabel();
		westLabel = new JLabel();
		//eastLabel = new JLabel();
	}

	public static void main(String[] args) {
		new finalProject1();
	}
}
