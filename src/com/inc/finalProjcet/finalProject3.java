package com.inc.finalProjcet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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

public class finalProject3 extends JFrame {
	CardLayout cl;
	JLabel startLabel, aLabel, bLabel, cLabel;
	JLabel dLabel, eLabel, fLabel;
	JLabel gLabel, hLabel, iLabel;
	JLabel taroLabel, oneLabel, twoLabel, threeLabel;
	JLabel fourLabel, fiveLabel, sixLabel;
	JLabel sevenLabel, eightLabel, nineLabel;

	ImageIcon bigImgtaro, bigImg, bigImg2, bigImg3;
	ImageIcon bigImg4, bigImg5, bigImg6;
	ImageIcon bigImg7, bigImg8, bigImg9;

	JButton firstBtn, restartBtn;
	JTextArea printArea;

	JPanel northPanel;
	JPanel southPanel;
	JPanel centerPanel;
	JPanel taroPanel, aPanel, bPanel, cPanel, dPanel, ePanel, fPanel, gPanel, hPanel, iPanel, jPanel;

	public finalProject3() {
		setTitle("타로카드");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(300, 300, 550, 550);
		setResizable(false);
		// setLayout(new FlowLayout());

		init();
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, "연애안할거니?\n 나가면 후회할낀데?"
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
				/*ArrayList<String> number = new ArrayList<>(); 
					number.add("1");
					number.add("2");
					number.add("3");
					number.add("4");
					number.add("5");
					number.add("6");
					number.add("7");
					number.add("8");
					number.add("9");
				String a = number.get((int)(Math.random()*8)+1);
				cl.show(centerPanel,a);*/
			
				String [] s = {"2","3","4","5","6","7","8","9","10"};
				int a = (int)(Math.random()*9);
				
				cl.show(centerPanel, s[a]);
			
			}
		
			

		});
		restartBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				cl.show(centerPanel, "1");
			}
		});

	}
	
	private void printResult() {
		
		
	}
	private void initPanel() {
		northPanel = new JPanel();
		northPanel.add(startLabel);
		
		centerPanel = new JPanel();
		cl = new CardLayout();
		centerPanel.setLayout(cl);
		
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(0, 150));
		southPanel.add(firstBtn);
		southPanel.add(restartBtn);
		
		taroPanel = new JPanel();
		aPanel = new JPanel();
		bPanel = new JPanel();
		cPanel = new JPanel();
		dPanel = new JPanel();
		ePanel = new JPanel();
		fPanel = new JPanel();
		gPanel = new JPanel();
		hPanel = new JPanel();
		iPanel = new JPanel();
		jPanel = new JPanel();
		
		taroPanel.add(taroLabel);
		aPanel.add(aLabel);
		aPanel.add(oneLabel);
		bPanel.add(bLabel);
		bPanel.add(twoLabel);
		cPanel.add(cLabel);
		cPanel.add(threeLabel);
		dPanel.add(dLabel);
		dPanel.add(fourLabel);
		ePanel.add(eLabel);
		ePanel.add(fiveLabel);
		fPanel.add(fLabel);
		fPanel.add(sixLabel);
		gPanel.add(gLabel);
		gPanel.add(sevenLabel);
		hPanel.add(hLabel);
		hPanel.add(eightLabel);
		iPanel.add(iLabel);
		iPanel.add(nineLabel);
		
		
	
		
		centerPanel.add(taroPanel, "1");
		centerPanel.add(aPanel, "2");
		centerPanel.add(bPanel, "3");
		centerPanel.add(cPanel, "4");
		centerPanel.add(dPanel, "5");
		centerPanel.add(ePanel, "6");
		centerPanel.add(fPanel, "7");
		centerPanel.add(gPanel, "8");
		centerPanel.add(hPanel, "9");
		centerPanel.add(iPanel, "10");
		
		
		
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
	}

	public void initComponent() {
		
		taroLabel = new JLabel();
		aLabel = new JLabel();
		bLabel = new JLabel();
		cLabel = new JLabel();
		dLabel = new JLabel();
		eLabel = new JLabel();
		fLabel = new JLabel();
		gLabel = new JLabel();
		hLabel = new JLabel();
		iLabel = new JLabel();
		
		oneLabel = new JLabel("완벽주의자");
		oneLabel.setFont(new Font(null, 0, 30));
		twoLabel = new JLabel("운명주의자");
		twoLabel.setFont(new Font(null, 0, 30));
		threeLabel = new JLabel("자유연애주의자");
		threeLabel.setFont(new Font(null, 0, 30));
		fourLabel = new JLabel("은둔주의자");
		fourLabel.setFont(new Font(null, 0, 30));
		fiveLabel = new JLabel("스타주의자");
		fiveLabel.setFont(new Font(null, 0, 30));
		sixLabel = new JLabel("정의주의자");
		sixLabel.setFont(new Font(null, 0, 30));
		sevenLabel = new JLabel("물질주의자");
		sevenLabel.setFont(new Font(null, 0, 30));
		eightLabel = new JLabel("절망주의자");
		eightLabel.setFont(new Font(null, 0, 30));
		nineLabel = new JLabel("이상주의자");
		nineLabel.setFont(new Font(null, 0, 30));
		
		startLabel = new JLabel("자신의 연애스타일을 알아보세요.");
		startLabel.setFont(new Font("맑은고딕", Font.BOLD, 23));
		firstBtn = new JButton("연애스타일알아보기");
		firstBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));
		restartBtn = new JButton("안맞아서다시보기");
		restartBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));

		printArea = new JTextArea();
		printArea.setBackground(Color.GREEN);
		printArea.setForeground(Color.BLUE);
		// add(printArea);
		bigImgtaro = new ImageIcon("src/com/inc/im/타로카드 연애운대문.jpg");
		bigImg = new ImageIcon("src/com/inc/im/다운로드교황.jpg");
		bigImg2 = new ImageIcon("src/com/inc/im/다운로드수레바퀴.jpg");
		bigImg3 = new ImageIcon("src/com/inc/im/타로카드광대.jpg");
		bigImg4 = new ImageIcon("src/com/inc/im/타로카드달.jpg");
		bigImg5 = new ImageIcon("src/com/inc/im/타로카드별.jpg");
		bigImg6 = new ImageIcon("src/com/inc/im/타로카드심판.jpg");
		bigImg7 = new ImageIcon("src/com/inc/im/타로카드악마.jpg");
		bigImg8 = new ImageIcon("src/com/inc/im/타로카드죽음.jpg");
		bigImg9 = new ImageIcon("src/com/inc/im/타로카드태양.jpg");
		
		taroLabel.setIcon(bigImgtaro);
		aLabel.setIcon(bigImg);
		bLabel.setIcon(bigImg2);
		cLabel.setIcon(bigImg3);
		dLabel.setIcon(bigImg4);
		eLabel.setIcon(bigImg5);
		fLabel.setIcon(bigImg6);
		gLabel.setIcon(bigImg7);
		hLabel.setIcon(bigImg8);
		iLabel.setIcon(bigImg9);
		

	
	}

	public static void main(String[] args) {
		new finalProject3();
	}
}
