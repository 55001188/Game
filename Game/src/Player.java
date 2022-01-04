import java.awt.Graphics;

public class Player extends Character{

	public Player() {
		super();
		file = "/imgs/Bat.gif";
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		x+=vx;
	}

}
