
package cakeClicker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Shop {

	ArrayList<Button> buttons = new ArrayList<Button>();
	public static int money = 1000000; // MAKE SURE TO SET THIS BACK TO ZERO!!!
	int buttonWidth = 150;
	int buttonHeight = 50;
	Button sprinkles = new Button(10, 400, buttonWidth, buttonHeight, "Sprinkles $50", Color.PINK, 1, 2, 50);
	Button iceCream = new Button(170, 400, buttonWidth, buttonHeight, "Ice cream $150", new Color(54, 193, 214), 1, 1,150);
	Button whippedCream = new Button(330, 400, buttonWidth, buttonHeight, "Whipped Cream $500", new Color(209, 188, 188), 2, 0.5, 500);
	Button brownie = new Button(10, 475, buttonWidth, buttonHeight, "Brownie $1,250", new Color(201, 161, 101), 4, 0.25, 1250);

	public void start() {
		buttons.add(sprinkles);
		buttons.add(iceCream);
		buttons.add(whippedCream);
		buttons.add(brownie);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}
}
