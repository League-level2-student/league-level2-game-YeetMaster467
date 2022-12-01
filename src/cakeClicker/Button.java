
package cakeClicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {

	int x;
	int y;
	int width;
	int height;
	String text;
	Color color;
	Font normalFont = new Font("Arial", Font.PLAIN, 12);
	Rectangle collisionBox;

	Button(int x, int y, int width, int height, String text, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.color = color;
		collisionBox = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(normalFont);
		g.drawString(text, x, y);
		collisionBox.setBounds(x, y, width, height);
	}

}
