
package cakeClicker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Shop {

	ArrayList<Button> buttons = new ArrayList<Button>();
	public static int money = 0;
	int buttonWidth = 150;
	int buttonHeight = 50;
	Button sprinkles = new Button(35, 400, buttonWidth, buttonHeight, "Sprinkles $50", Color.LIGHT_GRAY, 1, 1, 50);

	public void start() {
		buttons.add(sprinkles);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}
}
