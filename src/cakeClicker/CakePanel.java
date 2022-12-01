
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
	Font normalFont = new Font("Arial", Font.PLAIN, 26);
	Cursor c = new Cursor(30, 40, 50, 50);
	Timer timer;
	Rectangle collisionBox;

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 20, 20, 474, 338, null);
		collisionBox.setBounds(20, 20, 474, 338);
		g.setFont(normalFont);
		g.drawString("$" + Shop.money, 20, 30);
		c.draw(g);
	}

	CakePanel() {
		timer = new Timer(1000 / 60, this);
		timer.start();
		collisionBox = new Rectangle(10, 10, 474, 338);
	}

	public void showWindow() {
		loadImage("cake.png");
		frame.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(WIDTH, HEIGHT);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		c.loadCursorImage();
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

	private void playSound(String soundFile) {
		String path = "src/cakeClicker/";
		File sound = new File(path + soundFile);
		if (sound.exists()) {
			new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
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
			playSound("cash.wav");
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
