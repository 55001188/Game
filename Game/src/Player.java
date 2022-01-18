import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Player extends Character{
	private boolean rEdge, lEdge;
	
	public Player() {
		super();
		img = getImage("rocket.png");
		x = 280;
		y = 550;
		
		rEdge = false;
		lEdge = false;
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		w = 62;
		h = img.getHeight(null);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		//g.fillRect(x, y, w, h);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		x+=vx;
		update();
		
		if(x + w > 600) { //fix boundaries 
			x = 599-w;
			rEdge = true;
		} else {
			rEdge = false;
		}
		
		if(x < 0) {
			x = 1;
			lEdge = true;
		} else {
			lEdge = false;
		}
		
	}
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.15, 0.15);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.15, 0.15);
	}
	
	public boolean getREdge() {
		return rEdge;
	}
	
	public boolean getLEdge() {
		return lEdge;
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Character.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
