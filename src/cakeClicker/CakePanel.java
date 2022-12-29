
package cakeClicker;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
// import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CakePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

	final int WIDTH = 500;
	final int HEIGHT = 800;
	GridLayout grid = new GridLayout(3, 2);
	JFrame frame = new JFrame();
	boolean needImage = true;
	boolean gotImage = false;
	ImageIcon cupcakeIcon = new ImageIcon("src/cakeClicker/cupcakeIcon.png");
	public static BufferedImage image;
	public static BufferedImage sprinklesImage;
	public static BufferedImage iceCreamImage;
	public static BufferedImage whippedCreamImage;
	public static BufferedImage brownieImage;
	public static BufferedImage cookieImage;
	public static BufferedImage pieImage;
	public static BufferedImage puddingImage;
	public static BufferedImage croissantImage;
	Font normalFont = new Font("Arial", Font.PLAIN, 26);
	Cursor c = new Cursor(30, 40, 50, 50);
	MessageText message = new MessageText();
	Timer timer;
	Rectangle collisionBox;
	Shop shop = new Shop();
	boolean hasSprinkles = false;
	boolean hasIceCream = false;
	boolean hasWhippedCream = false;
	boolean hasBrownie = false;
	boolean hasCookie = false;
	boolean hasPie = false;
	boolean hasPudding = false;
	boolean hasCroissant = false;

	@Override
	public void paintComponent(Graphics g) {
		needImage = true;
		g.drawImage(image, 20, 20, 474, 338, null);
		collisionBox.setBounds(20, 20, 474, 338);
		g.setFont(normalFont);
		g.drawString("$" + Shop.money, 20, 30);
		shop.draw(g);
		message.draw(g);
		if (hasSprinkles) {
			g.drawImage(sprinklesImage, 60, 60, 400, 100, null);
		}
		if (hasIceCream) {
			g.drawImage(iceCreamImage, 15, 90, 150, 250, null);
		}
		if (hasWhippedCream) {
			g.drawImage(whippedCreamImage, 150, -5, 200, 150, null);
		}
		if (hasBrownie) {
			g.drawImage(brownieImage, 250, 275, 225, 125, null);
		}
		if (hasCookie) {
			g.drawImage(cookieImage, 150, 225, 175, 150, null);
		}
		if (hasPie) {
			g.drawImage(pieImage, -10, 225, 215, 160, null);
		} if (hasPudding) {
			g.drawImage(puddingImage, 245, 130, 175, 275, null);
		}
		c.draw(g);
	}

	CakePanel() {
		timer = new Timer(1000 / 60, this);
		timer.start();
		frame.addKeyListener(this);
		collisionBox = new Rectangle(10, 10, 474, 338);
		sprinklesImage = loadImage("sprinkles.png");
		image = loadImage("cake.png");
		iceCreamImage = loadImage("iceCream.png");
		whippedCreamImage = loadImage("whippedCream.png");
		brownieImage = loadImage("brownie.png");
		cookieImage = loadImage("cookies.png");
		pieImage = loadImage("pieSlice.png");
		puddingImage = loadImage("pudding.png");
		croissantImage = loadImage("croissant.png");
		playSound("cakeClickerMusic.wav", true);
	}

	public void showWindow() {
		frame.add(this);
		/*	JButton button = new JButton("Buy Money");
		 *	this.add(button);
		 *
		 *  I was thinking about adding micro transactions here,
		 *  But I didn't. (Thankfully)
		 */
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(450, 25);
		frame.setSize(WIDTH, HEIGHT);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		c.loadCursorImage();
		shop.start();
	}

	BufferedImage loadImage(String imageFile) {
		try {
			return ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void playSound(String soundFile, boolean shouldLoop) {
		String path = "src/cakeClicker/";
		File sound = new File(path + soundFile);
		if (sound.exists()) {
			new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
					if (shouldLoop) {
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
					Thread.sleep(clip.getMicrosecondLength() / 1000);
				} catch (Exception e) {
					System.out.println("Could not play this sound");
				}
			}).start();
		} else {
			System.out.println("File does not exist");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (c.collisionBox.intersects(collisionBox)) {
			Shop.money += 1;
			playSound("cash.wav", false);
		} else {
			for (int i = 0; i < shop.buttons.size(); i++) {
				if (c.collisionBox.intersects(shop.buttons.get(i).collisionBox)) {
					if (!shop.buttons.get(i).buttonPressed) {
						if (Shop.money == shop.buttons.get(i).cost || Shop.money > shop.buttons.get(i).cost) {
							playSound("cash.wav", false);
							Shop.money -= shop.buttons.get(i).cost;
							try {
								shop.buttons.get(i).autoclick();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							if (shop.buttons.get(i) == shop.sprinkles) {
								hasSprinkles = true;
							}
							if (shop.buttons.get(i) == shop.iceCream) {
								hasIceCream = true;
							}
							if (shop.buttons.get(i) == shop.whippedCream) {
								hasWhippedCream = true;
							}
							if (shop.buttons.get(i) == shop.brownie) {
								hasBrownie = true;
							}
							if (shop.buttons.get(i) == shop.cookie) {
								hasCookie = true;
							}
							if (shop.buttons.get(i) == shop.pie) {
								hasPie = true;
							} if (shop.buttons.get(i) == shop.pudding) {
								hasPudding = true;
							}
							shop.buttons.get(i).buttonPressed = true;
						} else {
							playSound("deny.wav", false);
						}
					}
				}
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		c.x = e.getX() - c.width / 2;
		c.y = e.getY() - c.height / 2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
			JOptionPane.showMessageDialog(null, "Click the cake to get money,\nUse the money to buy upgrades,\nUpgrades give you more money,\nKeep getting money until you get the FINAL INGREDIENT.\nAfter that you are an immortal being.", "Instructions", JOptionPane.INFORMATION_MESSAGE, cupcakeIcon);
			if (!message.instructionsRead) {
				message.text = "                 Great! Get Clicking!";
			}
			message.instructionsRead = true;
			message.textShowing = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
} 
