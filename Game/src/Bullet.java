import java.awt.Graphics;

public class Bullet {
	private int x,y,w, h;
	private int vy;
	private boolean collision;
	private boolean colP;
	private boolean show;
	
	public Bullet() {
		x = 300; //middle of player image
		y = 500; //change later
		vy = 0;
		w = 4;
		h = 15;
		collision = false;
	} 
	
	public Bullet(int nX, int nY) {
		x = nX; //middle of player image
		y = nY; //change later
		vy = 0;
		w = 4;
	}
	
	public void collideE(Enemy other) {
		//collide with enemy
		//when collide, disappear or set it to shooting position
		int x1 = this.getX();
		int y1 = this.getY();
		int w1 = this.getW();
		int h1 = this.getH();
		int x2 = other.getX();
		int y2 = other.getY();
		int w2 = other.getW();
		int h2 = other.getH();
		
		if(y1 < (y2+h2) && y1 > y2) {//y detection
			//collision x (left, right)
			if((x1 > x2 && x1 < (x2+w2)) || ((x1+w1) > x2 && (x1+w1) < (x2+w2))) { 
				other.setX(1500);
				other.setY(1500);
				collision = true;
			} else {
				collision = false;
			}
		} else {
			collision = false;
		}
		
		
	}
	
	public void collideP(Player other) {
		//collide with barrier
		//when collide, disappear or set it to shooting position
		int x1 = this.getX();
		int y1 = this.getY();
		int w1 = this.getW();
		int h1 = this.getH();
		int x2 = other.getX();
		int y2 = other.getY();
		int w2 = other.getW();
		int h2 = other.getH();

		if((y1+h1) > y2 && (y1+h1) < (y2+h2)) {//y detection
			//collision x (left, right)
			if((x1 > x2 && x1 < (x2+w2)) || ((x1+w1) > x2 && (x1+w1) < (x2+w2))) {
				colP = true;
			} else {
				colP = false;
			}
		} else {
			colP = false;
		}
	
	}
	
	public void reset(Player p) {
		show = false;
		x = (p.getX() + p.getW()/2) - w;
		y = p.getY();
		collision = false;
	}
	
	public void reset(Enemy e) {
		show = false;
		x = (e.getX() + e.getW()/2) - w;
		y = e.getY() + e.getH() - h;
		colP = false;
	}
	
	public void shoot() {
		show = true;
		vy = 7;
	}
	
	public void paint(Graphics g) {
		if(show) {
			g.fillRect(x, y, w, h);
		}
		
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

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isColP() {
		return colP;
	}

	public void setColP(boolean colP) {
		this.colP = colP;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
	
}
