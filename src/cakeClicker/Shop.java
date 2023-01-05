
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
	Button brownie = new Button(10, 460, buttonWidth, buttonHeight, "Brownie $1,250", new Color(201, 161, 101), 4, 0.25, 1_250);
	Button cookie = new Button(170, 460, buttonWidth, buttonHeight, "Cookies $3,500", new Color(235, 169, 120), 10, 0.5, 3_500);
	Button pie = new Button(330, 460, buttonWidth + 7, buttonHeight, "Pie Slice $5,700", Color.LIGHT_GRAY, 20, 0.6, 5_700);
	Button pudding = new Button(10, 520, buttonWidth, buttonHeight, "Pudding $10,000", new Color(242, 224, 136), 50, 0.5, 10_000);
	Button croissant = new Button(170, 520, buttonWidth, buttonHeight, "Croissant $17,000", new Color(255, 238, 176), 150, 0.7, 17_000);
	Button candy = new Button(330, 520, buttonWidth + 7, buttonHeight, "Candy $35,000", new Color(83, 222, 75), 350, 0.6, 35_000);
	Button cherry = new Button(125, 580, 250, 75, "THE CHERRY ON TOP $150,000", new Color(214, 96, 96), 1000, 0.1, 150_000);
	
	public void start() {
		buttons.add(sprinkles);
		buttons.add(iceCream);
		buttons.add(whippedCream);
		buttons.add(brownie);
		buttons.add(cookie);
		buttons.add(pie);
		buttons.add(pudding);
		buttons.add(croissant);
		buttons.add(candy);
		buttons.add(cherry);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}
}
