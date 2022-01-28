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
	private ArrayList<ArrayList> en = new ArrayList<ArrayList>();
	
	
	int score = 0; //e = 10, m = 20, h = 30
	int lives = 2;
	boolean dead = false;
	boolean shoot = false;
	boolean gameOver = false;
	
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
		int r = 10*(int)(Math.random()*10)+1;
		if(score%r == 0 && score != 0 && !dead) {
			bulletE.shoot();
		}
		
		//reset to position
		if(!bulletE.isShow()) {
			int ran = (int)(Math.random()*6);
			int ran2 = (int)(Math.random()*4);
			bulletE.reset((Enemy)en.get(ran2).get(ran));
		}
		
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
		
		//collision with ship
		bulletP.collideE(s);
		if(bulletP.isCollision()) {
			shoot = false;
			score += 50;
		}
		
		//collision with player
		bulletE.collideP(p);
		if(bulletE.isColP()) {
			bulletE.setShow(false);
			dead = true;
			lives--;
			if(life.get(0).getY() > 750) {
				life.get(1).setVy(1);
			}else {
				life.get(0).setVy(1);
			}
		}
		
		if(dead) {
			p.changePicture();
		}
		
		//Resets player after losing a life
		if(life.get(0).getY() > 750 && life.get(0).getY() < 800 && !gameOver) {//fix
			p.reset();
			dead = false;
		}
		
		if(life.get(1).getY() > 750 && life.get(1).getY() < 800 && !gameOver) {//fix
			p.reset();
			dead = false;
		}
		
		if(life.get(0).getY() > 850) {
			life.get(0).setVy(0);
		}
		
		if(life.get(1).getY() > 850) {
			life.get(1).setVy(0);
		}
		
		//if player's bullet goes off of screen
		if(bulletP.getY() + bulletP.getH() < -5) {
			shoot = false;
		}
		
		//if enemy's bullet goes off screen
		if(bulletE.getY() + bulletE.getH() > 615) {
			bulletE.setShow(false);
		}
		
		//Score
		Font c = new Font("Arial", Font.PLAIN, 20);
		g.setFont(c);
		g.setColor(Color.CYAN);
		g.drawString("SCORE: " + score + "", 60, 30);
		
		if(lives == 0) {
			gameOver = true;
		}
		
		//movement for enemy aliens
		if(!gameOver) {
			slide(e);
			slide(e2);
			slide(m);
			slide(h);
		} else {
			lose();
		}
		
		
		
		//move spaceship
		if(score%20 == 0) { //fix
			s.move();
			if(s.getX()+s.getW() < -10) {
				s.reset();
			}
		}
		
		//game over if enemies reach player
		for(Enemy thisEnemy : e) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545 && thisEnemy.getY() < 600) {
				gameOver = true;
				System.out.println("t1");
			}
		}
		for(Enemy thisEnemy : e2) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545 && thisEnemy.getY() < 600) {
				gameOver = true;
				System.out.println("t2");
			}
		}
		for(Enemy thisEnemy : m) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545 && thisEnemy.getY() < 600) {
				gameOver = true;
				System.out.println("t3");
			}
		}
		for(Enemy thisEnemy : h) {
			if(thisEnemy.getY()+thisEnemy.getH() > 545 && thisEnemy.getY() < 600) {
				gameOver = true;
				System.out.println("t4");
			}
		}
		

		if(gameOver) {
			Font c1 = new Font("Arial", Font.PLAIN, 40);
			g.setFont(c1);
			g.setColor(Color.RED);
			g.drawString("GAME OVER!!!", 200, 50);
			dead = true;
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
	
	public void lose() {
		for(Enemy thisEnemy : e) {
			thisEnemy.setVx(0);
		}
		for(Enemy thisEnemy : e2) {
			thisEnemy.setVx(0);
		}
		for(Enemy thisEnemy : m) {
			thisEnemy.setVx(0);
		}
		for(Enemy thisEnemy : h) {
			thisEnemy.setVx(0);
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
		int y = 200;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienE.png", x, y);
			//add to array list
			e2.add(temp);
			x+=60;
		}
		
		x = 100;
		y = 260;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienE.png", x, y);
			//add to array list
			e.add(temp);
			x+=60;
		}
		
		//medium
		x = 100;
		y -= 120;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienM.png", x, y);
			//add to array list
			m.add(temp);
			x+=60;
		}
		
		//hard
		x = 100;
		y -= 60;
		for(int i = 0; i < 6; i++) {
			Enemy temp = new Enemy("alienH.png", x, y);
			//add to array list
			h.add(temp);
			x+=62;
		}
		
		//add enemy array to array list
		en.add(e);
		en.add(e2);
		en.add(h);
		en.add(m);
		
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
		if(!dead) {
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
