package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Hero {
	//I commented this online
	BufferedImage sprite;
	String name;
	int x;
	int y;
	int moveChange=20;
	
	public Hero(String name, String imageLocation, int locationX, int locationY) {
		this.name = name;
		int width = 130;
		int height = 171;
		x = locationX;
		y = locationY;
		sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//"images/heros/jarjar.jpg"
		URL url = getClass().getResource(imageLocation);
		try {
			BufferedImage original = ImageIO.read(url);
			//draw the image file into a scaled version on the sprite canvas
			Graphics2D g = (Graphics2D) sprite.getGraphics();
			int w = original.getWidth();
			int h = original.getHeight();
			//what to draw, where to start(x,y), width of canvas(relative to start), height of canvas(relative to start), where to start from original(x,y), width of original, height, null 
			g.drawImage(original,0,0,width,height,0,0,w,h,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BufferedImage getImage() {
		return sprite;
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void moveUp() {
		y = y-moveChange;
		
	}
	public void moveDown() {
		y = y+moveChange;
		
	}
	public void moveRight() {
		x = x+moveChange;
		
	}
	public void moveLeft() {
		x = x-moveChange;
		
	}

}
