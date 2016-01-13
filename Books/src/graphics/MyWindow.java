package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class MyWindow extends JFrame implements KeyListener {

	int width = 500;
	int length = 500;
	int c = 0;
	Hero girl;
	BufferedImage landscape;
	boolean itemPickedUp;
	
	public static void main(String[] args) {
		new MyWindow();
	}

	public MyWindow() {
		girl = new Hero("Girl", "/memes/Pepe.png",200,200);
		landscape = new BufferedImage(width,length,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g5 = (Graphics2D)landscape.getGraphics();
		//paintLandscape(g5);
		// the following are JFrame methods
		setVisible(true);
		setSize(width, length);
		setLocation(200, 150);// 200px right, 150px down
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// static constant
														// referencing for
														// special close
														// operation
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		// Graphics is a crayon box
		// Graphics2d is like an art kit
		//Random random = new Random();
		BufferedImage image = new BufferedImage(width,length,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) g;
		/*g2.setColor(Color.white);
		g2.fillRect(0, 0, width, length);
		g2.setColor(Color.red);
		g2.fillOval(50, 100, 200, 100);
		g2.setColor(Color.blue);
		g2.drawOval(50, 100, 200, 100);
		//x, y, width, length, startDEG, lengthDEG
		g2.drawArc(50, 300, 200, 100, 0, 90);
		//string, x, y
		g2.drawString("String", 300, 300);
		//lines: startX, startY, endX, endY
		g2.drawLine(0, 0, width, length);*/
		g2.setColor(new Color(0, 255, 255));
		g2.fillRect(0,0,width,length);
		//paintLandscape(g2);
		g2.drawImage(girl.getImage(), girl.getX(), girl.getY(), null);
		itemPickedUp=false;
		/*int ovalD = 50;
		int margin = 30;
		for(int x = 0; x < width; x+=ovalD+margin) {
			g2.setColor(Color.white);
			g2.fillOval(x, 50, ovalD, 20);
		}
		g2.setColor(new Color(255, 204, 0));
		g2.fillRect(0, 150, width, length);
		for(int num = 0; num < 7; num++) {
			int x = random.nextInt(width - 0 + 1) + 0;
			int y = random.nextInt(length - 150) + 150;
			g2.setColor(Color.green);
			g2.fillRect(x,y, 50, 100);
		}
		/*g2.setColor(Color.blue);
		int squareD = 20;
		int margin = 2;
		for (int x = 0; x < width; x += squareD + margin) {
			for (int y = 0; y < length; y += squareD + margin) {
				c++;
				if(c > 255) {
					c = 0;
					g2.setColor(new Color(0, 0, c));
					g2.fillRect(x, y, squareD, squareD);
				}
				else {
					g2.setColor(new Color(0, 0, c));
					g2.fillRect(x, y, squareD, squareD);
				}

			}

		}*/
		//draw the buffered image on the canvas
		/**
		if (Math.abs(girl.getX()-potion.getX()) + Math.abs(girl.getY()-potion.getY()) <10){
			itemPickedUp=true;
		}**/
		g.drawImage(image, 0, 0, null);
		if(itemPickedUp){
			
		}

	}

	public void keyPressed(KeyEvent arg0) {
		int key=arg0.getKeyCode();
		if(key==KeyEvent.VK_UP){
			girl.moveUp();
		}else{
			if(key==KeyEvent.VK_DOWN){
				girl.moveDown();
			}else{
				if(key==KeyEvent.VK_RIGHT){
					girl.moveRight();
				}else{
					if(key==KeyEvent.VK_LEFT){
						girl.moveLeft();
					}
				}
			}
		}
		repaint();
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
		
	}

}