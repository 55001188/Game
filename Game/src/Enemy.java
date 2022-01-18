import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy extends Character {

	public Enemy() {
		super();
		x = 300; 
		y = 200;
		w = 20;
		h = 20;
		img = getImage("alienE.png");
		
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
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		w = 20;
		h = 20;
	}
	
	public void paint(Graphics g) {
		//g.fillRect(x, y, w, h);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		update();
		w = img.getWidth(null);
		h = img.getHeight(null);
		
		
	}
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(0.15, 0.15);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.15, 0.15);
	}
	
	public void setTx(double x, double y) {
		tx.scale(x, y);
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
