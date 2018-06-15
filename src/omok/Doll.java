package omok;

import java.awt.Color;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Doll {
	
	//돌의 위치
	public Point p;
	
	//돌의 크기
	public static final int CELL_SIZE = 30;
	
	//돌의 색상
	private Color c;
	
	//보여져야하는지 여부
	private boolean isVisible;
	
	//돌이 놓여져있는지 여부
	private boolean isFixed;
	
	//흑돌
	ImageIcon blackDoll = new ImageIcon("src/omok/blackStone.png");
	
	//흰돌
	ImageIcon whiteDoll = new ImageIcon("src/omok/whiteStone.png");
	
	public Doll(Point p,Color c) {
		this.p = p;
		this.c = c;
	}
	public Doll(Point p) {
		this.p = p;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
	
	
}
