import java.awt.Color;
import java.awt.Graphics;

public class Player extends Character{
	private boolean rEdge, lEdge;
	
	public Player() {
		super();
		file = "/imgs/Bat.gif";
		x = 300;
		y = 550;
		w = 40;
		h = 20;
		rEdge = false;
		lEdge = false;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
		x+=vx;
		if(x + w > 600) { //fix boundaries 
			rEdge = true;
			x--;
		} else {
			rEdge = false;
		}
		
		if(x < 0) {
			lEdge = true;
			x++;
		} else {
			lEdge = false;
		}
	}
	
	public boolean getREdge() {
		return rEdge;
	}
	
	public boolean getLEdge() {
		return lEdge;
	}

}
