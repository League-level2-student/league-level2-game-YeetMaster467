
package cakeClicker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Shop {

	ArrayList<Button> buttons = new ArrayList<Button>();
	public static long money = 1000000000; // MAKE SURE TO SET THIS BACK TO ZERO!!!
	int buttonWidth = 150;
	int buttonHeight = 50;
	Button sprinkles = new Button(10, 400, buttonWidth, buttonHeight, "Sprinkles $50", Color.PINK, 1, 2, 50);
	Button iceCream = new Button(170, 400, buttonWidth, buttonHeight, "Ice cream $150", new Color(54, 193, 214), 1, 1,150);
	Button whippedCream = new Button(330, 400, buttonWidth + 7, buttonHeight, "Whipped Cream $500", new Color(235, 202, 202), 2, 0.5, 500);
	Button brownie = new Button(10, 460, buttonWidth, buttonHeight, "Brownie $1,250", new Color(201, 161, 101), 4, 0.25, 1250);
	Button cookie = new Button(170, 460, buttonWidth, buttonHeight, "Cookies $3,500", new Color(235, 169, 120), 10, 0.5, 3500);
	Button pie = new Button(330, 460, buttonWidth + 7, buttonHeight, "Pie Slice $5,700", new Color(214, 96, 96), 20, 0.6, 5700);

	public void start() {
		buttons.add(sprinkles);
		buttons.add(iceCream);
		buttons.add(whippedCream);
		buttons.add(brownie);
		buttons.add(cookie);
		buttons.add(pie);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}
}
