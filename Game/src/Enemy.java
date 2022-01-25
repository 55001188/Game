import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy extends Character {
	private double scale;
	
	public Enemy() {
		super();
		x = 300; 
		y = 200;
		img = getImage("alienE.png");
		scale = 0.15;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
	}
	
	public Enemy(String fileName, int newX, int newY) {
		// TODO Auto-generated constructor stub
		super();
		x = newX;
		y = newY;
		file = fileName;
		img = getImage(file);
		scale = 0.15;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		vx = 2;
	}
	
	public void paint(Graphics g) {
		//g.fillRect(x, y, w, h);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		
		x+=vx;
		update();
		w = img.getWidth(null)*3/20;
		h = img.getHeight(null)*3/20;
		
		//multiply width and height by the scale as a fraction
		
	}
	
	public void move() {
		if(x+w >= -20 && x <= 600) {
			vx = -5;
		} else {
			vx = 0;
		}
		
		if(x+w <= -10) { //figure out how to make ship move and stop when necessary
			x = 605;
		}
		
	}
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(scale, scale); //0.15
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scale, scale);
	}
	
	public void setTx(double x) {
		scale = x;
		tx.scale(scale, scale);
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
	
	public int getW() {
		return w;
	}
	

}
