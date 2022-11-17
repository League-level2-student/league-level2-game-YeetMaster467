
package cakeClicker;

import javax.swing.JLabel;

public class Shop {
	public static int money = 0;
	public static JLabel moneyLabel = new JLabel();
	
	public void countMoney() {
		while(true) {
			moneyLabel.setText("$" + money);
		}
	}
}
