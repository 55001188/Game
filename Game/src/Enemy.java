import java.awt.Graphics;

public class Enemy extends Character {

	public Enemy() {
		super();
		x = 300; 
		y = 200;
		w = 20;
		h = 10;
		file = "";
	}
	
	public Enemy(String fileName) {
		// TODO Auto-generated constructor stub
		super();
		file = fileName;
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, w, h);
		
		
	}
	
	

}
