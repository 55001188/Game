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
	
	public boolean collideE(Enemy other) {
		//collide with enemy or barrier
		//when collide, disappear or set it to shooting position
		int x1 = this.getX();
		int y1 = this.getY();
		int w1 = this.getW();
		int h1 = this.getH();
		int x2 = other.getX();
		int y2 = other.getY();
		int w2 = other.getW();
		int h2 = other.getH();
		boolean collision = false;
		
		if(x1-(x2 + w2) < 0 || (x1 + w1)-x2 > 0) { //collision x (left, right)
			if(y1 - (y2 + h2) < 0) {//collision y (top)
				collision = true;
			}
		}
		
		return collision;
	}
	
	public boolean collideB(Barrier other) {
		//collide with enemy or barrier
		//when collide, disappear or set it to shooting position
		int x1 = this.getX();
		int y1 = this.getY();
		int w1 = this.getW();
		int h1 = this.getH();
		int x2 = other.getX();
		int y2 = other.getY();
		int w2 = other.getW();
		int h2 = other.getH();
		boolean collision = false;
		
		if(x1-(x2 + w2) < 0 || (x1 + w1)-x2 > 0) { //collision x (left, right)
			if((y1 + h1) - y2 > 0) {//collision y (bottom)
				collision = true;
			}
		}
		
		return collision;
	
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
