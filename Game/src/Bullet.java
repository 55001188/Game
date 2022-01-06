import java.awt.Graphics;

public class Bullet {
	private int x,y,w, h;
	private int vy;
	
	public Bullet() {
		x = 300; //middle of player image
		y = 500; //change later
		vy = 0;
		w = 4;
		h = 10;
	}
	
	public Bullet(int nX, int nY) {
		x = nX; //middle of player image
		y = nY; //change later
		vy = 0;
		w = 4;
	}
	
	public void collide(Object other) {
		//collide with enemy or barrier
		//when collide, disappear or set it to shooting position
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, w, h);
		y+=vy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
	
	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}
