
package cakeClicker;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CakePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	final int WIDTH = 500;
	final int HEIGHT = 800;
	GridLayout grid = new GridLayout(3, 2);
	JFrame frame = new JFrame();
	boolean needImage = true;
	boolean gotImage = false;
	public static BufferedImage image;
	public static BufferedImage sprinklesImage;
	public static BufferedImage iceCreamImage;
	public static BufferedImage whippedCreamImage;
	public static BufferedImage brownieImage;
	Font normalFont = new Font("Arial", Font.PLAIN, 26);
	Cursor c = new Cursor(30, 40, 50, 50);
	Timer timer;
	Rectangle collisionBox;
	Shop shop = new Shop();
	boolean hasSprinkles = false;
	boolean hasIceCream = false;
	boolean hasWhippedCream = false;
	boolean hasBrownie = false;

	@Override
	public void paintComponent(Graphics g) {
		needImage = true;
		g.drawImage(image, 20, 20, 474, 338, null);
		collisionBox.setBounds(20, 20, 474, 338);
		g.setFont(normalFont);
		g.drawString("$" + Shop.money, 20, 30);
		shop.draw(g);
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
		c.draw(g);
	}

	CakePanel() {
		timer = new Timer(1000 / 60, this);
		timer.start();
		collisionBox = new Rectangle(10, 10, 474, 338);
		sprinklesImage = loadImage("sprinkles.png");
		image = loadImage("cake.png");
		iceCreamImage = loadImage("iceCream.png");
		whippedCreamImage = loadImage("whippedCream.png");
		brownieImage = loadImage("brownie.png");
		playSound("cakeClickerMusic.wav", true);
	}

	public void showWindow() {
		frame.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
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
	
	
} 
