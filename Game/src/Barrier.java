 
public class Barrier{
	private int x,y, w, h;
	
	public Barrier() {
		x = 0;
		y = 0;
		w = 20;
		h = 10;
	}
	
	public Barrier(int nX, int nY) {
		x = nX;
		y = nY;
		w = 20;
		h = 10;
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
