package com.inc.finalProjcet;
import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class finalProject2 extends JFrame{
	
	CardLayout cl;
	
	JLabel startLabel, firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel;
	JLabel oneLabel, twoLabel, threeLabel, fourLabel, fiveLabel;
	
	ImageIcon bigImg, bigImg2, bigImg3, bigImg4, bigImg5;

	JButton oneButton;
	JButton twoButton;
	
	JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	
	
	JPanel firstPanel, secondPanel, thirdPanel, fourthPanel, fifthPanel ;
	
	
	public finalProject2() {
		setTitle("행복심리테스트");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(300, 300, 500, 500);
		setResizable(false);
		setLayout(new BorderLayout());
		
		
		init();
		setVisible(true);
		

		
		
	}

	public void init() {
		initComponent();
		initPanel();
		initEvent();
	}
	
	

	private void initComponent() {
		startLabel = new JLabel("문장을 읽고  '예' 또는 '아니오'를 누르세요.");
		startLabel.setFont(new Font("맑은고딕", Font.BOLD, 23));
		//startLabel.setpreferredSize(Dimension(48,30);
		firstLabel = new JLabel("귀엽나요?");
		firstLabel.setFont(new Font(null, 0, 30));
		secondLabel = new JLabel("혹시 이 정도면 귀엽나요?");
		secondLabel.setFont(new Font(null, 0, 30));
		thirdLabel = new JLabel("정말 이 사진도 안 귀엽나요?");
		thirdLabel.setFont(new Font(null, 0, 30));
		fourthLabel = new JLabel("<html>마지막 사진입니다.<br> 정말정말 귀엽지요?<html>");
		fourthLabel.setFont(new Font(null, 0, 30));
		fifthLabel = new JLabel("<html>당신은 정신상태과 정상적이며<br> "
				+ "마음이 따뜻한 사람입니다.<br> 그 따뜻한 마음을 주위사람들과 <br>공유하시는"
				+ " 멋진 분이<br> 되시길 바랍니다.</html>");
		fifthLabel.setFont(new Font(null, 0, 29));
		oneLabel = new JLabel();
		twoLabel = new JLabel();
		threeLabel = new JLabel();
		fourLabel = new JLabel();
		fiveLabel = new JLabel();
		//CardLayout cl = new CardLayout();
		
		oneButton = new roundButton("예");
		oneButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//twoButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		twoButton = new roundButton("아니오");
		oneButton.setBackground(Color.GREEN);
		oneButton.setPreferredSize(new Dimension(100, 30));
		twoButton.setBackground(Color.RED);
		twoButton.setPreferredSize(new Dimension(100, 30));
		northPanel = new JPanel();
		centerPanel = new JPanel();
		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		fourthPanel = new JPanel();
		fifthPanel = new JPanel();
		
		southPanel = new JPanel();
	}

	private void initPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(Color.lightGray);
		northPanel.setPreferredSize(new Dimension(0, 50));
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new CardLayout(5,5));
		
		northPanel.add(startLabel);
		southPanel.add(oneButton);
		southPanel.add(twoButton);
		southPanel.setPreferredSize(new Dimension(0, 100));
		
		bigImg = new ImageIcon("src/com/inc/im/강아지1.jpg");
		oneLabel.setIcon(bigImg);
		firstPanel.add(firstLabel);
		firstPanel.add(oneLabel);
		
		bigImg2 = new ImageIcon("src/com/inc/im/고양이1.jpg");
		twoLabel.setIcon(bigImg2);
		secondPanel.add(secondLabel);
		secondPanel.add(twoLabel);
		
		bigImg3 = new ImageIcon("src/com/inc/im/아기1.jpg");
		threeLabel.setIcon(bigImg3);
		thirdPanel.add(threeLabel);
		thirdPanel.add(thirdLabel);
		
		bigImg4 = new ImageIcon("src/com/inc/im/아기2.jpg");
		fourLabel.setIcon(bigImg4);
		fourthPanel.add(fourLabel);
		fourthPanel.add(fourthLabel);
		fifthPanel.add(fifthLabel);
		
		cl = new CardLayout();
		centerPanel.setLayout(cl);
		
		centerPanel.add(firstPanel);
		centerPanel.add(secondPanel);
		centerPanel.add(thirdPanel);
		centerPanel.add(fourthPanel);
		centerPanel.add(fifthPanel);
		
		firstPanel.add(firstLabel);
		
		
		
		add(northPanel,BorderLayout.NORTH);
		add(centerPanel);
		add(southPanel,BorderLayout.SOUTH);
		
		
		
	}

	private void initEvent() {
		oneButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				cl.last(centerPanel);
				}
		
		});
		
		twoButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl.next(centerPanel);
			}
			
		});
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int choice 
					= JOptionPane.showConfirmDialog(
						null, "심리테스트 다 했니?\n 아니면 못 믿겠니?", "종료", 
						JOptionPane.YES_NO_OPTION);
				
				System.out.println(choice);
				
				if(choice == 0) {
					dispose();//System.exit(0);
				}if(choice == 1) {
					
				}
			}
			
		});
	}

	

	
	


	public static void main(String[] args) {
		new finalProject2();
	}
}



