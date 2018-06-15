package cardgame;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Card {
	int number;
	Color c;
	Color fontc;
	ImageIcon Img = new ImageIcon("src/realgame/card.jpg");
	
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public Color getFontc() {
		return fontc;
	}
	public void setFontc(Color fontc) {
		this.fontc = fontc;
	}
}
