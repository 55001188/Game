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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FrameForGame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	private Background b = new Background(0,0);
	private Player p = new Player();
	private Bullet bulletP = new Bullet();
	private Bullet bulletE = new Bullet(255, 0, 0);
	private Enemy s = new Enemy("ship.png", 600, 70);
	private ArrayList<Enemy> e = new ArrayList<Enemy> ();
	private ArrayList<Enemy> e2 = new ArrayList<Enemy> ();
	private ArrayList<Enemy> m = new ArrayList<Enemy> ();
	private ArrayList<Enemy> h = new ArrayList<Enemy> ();
	private ArrayList<Player> life = new ArrayList<Player>();
	
	
	int score = 0; //e = 10, m = 20, h = 30
	int lives = 2;
	
	boolean shoot = false;
	
	public void paint(Graphics g) {
		//super.paintComponent(g);
		b.paint(g); //background
		p.paint(g); //player
		s.setTx(0.5);//ship
		s.paint(g);
		
		//paint e and e2
		for(Enemy thisEnemy : e) {
			thisEnemy.paint(g);
		}
		for(Enemy thisEnemy : e2) {
			thisEnemy.paint(g);
		}
		//paint m
		for(Enemy thisEnemy : m) {
			thisEnemy.paint(g);
		}
		//paint h
		for(Enemy thisEnemy : h) {
			thisEnemy.paint(g);
		}
		
		//paint lives of player
		for(Player thisPlayer : life) {
			thisPlayer.paint(g);
		}
		
		bulletP.paint(g);
		bulletE.paint(g);
		
		//shoot at random intervals
		//bulletE.shoot();
		//
		
		//bullet for player
		if(!shoot) {
			bulletP.reset(p);
		}
		
		//collision w/ e
		for(Enemy thisEnemy : e) {
			bulletP.collideE(thisEnemy); 
			if(bulletP.isCollision()){
				shoot = false;
				score += 10;
			}
		}
		for(Enemy thisEnemy : e2) {
			bulletP.collideE(thisEnemy); 
			if(bulletP.isCollision()){
				shoot = false;
				score += 10;
			}
		}
		
		//collision w/ m
		for(Enemy thisEnemy : m) {
			bulletP.collideE(thisEnemy); 
			if(bulletP.isCollision()){
				shoot = false;
				score += 20;
			}
				
		}
		//collision w/ h
		for(Enemy thisEnemy : h) {
			bulletP.collideE(thisEnemy); 
			if(bulletP.isCollision()){
				shoot = false;
				score += 30;
			}
		}
		
		//collision with player
		bulletE.collideP(p);
		if(bulletP.isColP()) {
			//return bullet to random alien
			//lose a life
			//player explodes or something
		}
		
		
		//if player's bullet goes off of screen
		if(bulletP.getY() + bulletP.getH() < -5) {
			shoot = false;
		}
		
		//if enemy's bullet goes off screen
		if(bulletP.getY() + bulletP.getH() > 615) {
			//bulletE.reset(//enemy); reset to random enemy
			//
			//
		}
		
		//Score
		Font c = new Font("Arial", Font.PLAIN, 20);
		g.setFont(c);
		g.setColor(Color.CYAN);
		g.drawString("SCORE: " + score + "", 60, 30);
		
		
		//movement for enemy aliens
		slide(e);
		slide(e2);
		slide(m);
		slide(h);
		//move spaceship at random intervals
		//
		//
		
		//game over if enemies reach player
		for(Enemy thisEnemy : e) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545) {
				//game over
			}
		}
		for(Enemy thisEnemy : e2) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545) {
				//game over
			}
		}
		for(Enemy thisEnemy : m) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545) {
				//game over
			}
		}
		for(Enemy thisEnemy : h) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545) {
				//game over
			}
		}
		
	}
	
	public static void main(String[] arg) {
		FrameForGame f = new FrameForGame();
		
	} 
	
	public void slide(ArrayList<Enemy> enemy) {
		for(Enemy thisEnemy : enemy) {
			if(thisEnemy.getX() + thisEnemy.getW() > 540 && thisEnemy.getX() + thisEnemy.getW() < 1000) {
				for(int i = 0; i < enemy.size(); i++) {
					enemy.get(i).setVx(-2);
					enemy.get(i).setY(enemy.get(i).getY() + 5);
				}
			} 
			if(thisEnemy.getX() < 60) {
				for(int i = 0; i < enemy.size(); i++) {
					enemy.get(i).setVx(2);
					enemy.get(i).setY(enemy.get(i).getY() + 5);
				}
			}
		}
		
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
		
		int x = 100;
		int y = 250;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienE.png", x, y);
			//add to array list
			e2.add(temp);
			x+=60;
		}
		
		x = 100;
		y = 300;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienE.png", x, y);
			//add to array list
			e.add(temp);
			x+=60;
		}
		
		//medium
		x = 100;
		y -= 100;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienM.png", x, y);
			//add to array list
			m.add(temp);
			x+=60;
		}
		
		//hard
		x = 100;
		y -= 50;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienH.png", x, y);
			//add to array list
			h.add(temp);
			x+=62;
		}
		
		//lives
		int xl = 450;
		for(int g = 0; g < 2; g++) {
			Player temp = new Player(xl, 620, 0.1);
			life.add(temp);
			xl += 50;
		}
		
		
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
		//System.out.println(arg0.getKeyCode());
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
			bulletP.setShow(true);
			bulletP.setVy(-7);
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
