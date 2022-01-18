import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FrameForGame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Background b = new Background(0,0);
	Player p = new Player();
	Bullet bulletP = new Bullet();
	Enemy e = new Enemy("alienE.png");
	Enemy m = new Enemy("alienM.png");
	Enemy h = new Enemy("alienH.png");
	Enemy s = new Enemy("ship.png");
	
	
	//use 2d array for enemy?
	//create 3 different levels of enemy
	
	
	int score;
	int lives;
	
	boolean shoot = false;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//ask objects to paint themselves
		b.paint(g); //background
		p.paint(g); //player
		e.paint(g); //enemy
		m.setX(100);
		m.paint(g);
		h.setX(200);
		h.paint(g);
		s.setX(300);
		s.setTx(2, 2);
		s.paint(g);
		
		bulletP.paint(g);
		
		//bullet for player
		if(!shoot) {
			bulletP.setX((p.getX() + p.getW()/2) - bulletP.getW());
			bulletP.setY(p.getY());
		}
		/*
		if(bulletP.collideE(e)) {
			shoot = false;
			bulletP.setX((p.getX() + p.getW()/2) - bulletP.getW());
			bulletP.setY(p.getY());
		}
		*/
		if(bulletP.getY() + bulletP.getH() < -5) { //add if collide is true 
			shoot = false;
			bulletP.setX((p.getX() + p.getW()/2) - bulletP.getW()/2);
			bulletP.setY(p.getY());
		}
		
		
		
		
		
	}
	
	public static void main(String[] arg) {
		FrameForGame f = new FrameForGame();
		
	} 
	
	public FrameForGame() {
		JFrame f = new JFrame("Game");
		f.setSize(new Dimension(600, 700)); //frame dimensions
		f.setBackground(Color.black);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		/*
		//change cursor image
		BufferedImage cursorImg;
		try {
				cursorImg =  ImageIO.read(new File("crosshair.png"));
				Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
						    cursorImg, new Point(0, 0), "blank cursor");
				f.getContentPane().setCursor(blankCursor);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
		
		
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		//mouse location
		//int my = MouseInfo.getPointerInfo().getLocation().y;
		//int mx = MouseInfo.getPointerInfo().getLocation().x;
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		//39 right, 40 middle, 37 left, 38 top
		
		if(arg0.getKeyCode() == 39) {
			p.setVx(5);
		}
		
		if(arg0.getKeyCode() == 37) {
			p.setVx(-5);
			
		}
		
		//shoot
		if(arg0.getKeyCode() == 32) {
			shoot = true;
			bulletP.setVy(-5);
			System.out.print("hi");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 39) {
			p.setVx(0);
		}
		if(arg0.getKeyCode() == 37) {
			p.setVx(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
