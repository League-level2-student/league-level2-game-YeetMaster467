package cakeClicker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Cursor {

	boolean needImage = true;
	boolean gotImage = false;
	public static BufferedImage image;
	int speed;
	int x;
	int y;
	int width;
	int height;
	Rectangle collisionBox;

	public void loadCursorImage() {
		loadImage("cakeCursor.png");
	}

	Cursor(int x, int y, int width, int height) {
		speed = 10;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		collisionBox.setBounds(x, y, width, height);
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
