import java.awt.Graphics;

public class Enemy extends Character {

	public Enemy() {
		super();
		x = 300; 
		y = 200;
		file = "";
	}
	
	public Enemy(String fileName) {
		// TODO Auto-generated constructor stub
		super();
		file = fileName;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		
		
	}
	
	

}
