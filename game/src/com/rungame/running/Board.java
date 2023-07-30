package com.rungame.running;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.rungame.running.sprites.Bullet;
import com.rungame.running.sprites.Enemy;
import com.rungame.running.sprites.Player;
import com.rungame.running.sprites.Sprite;

public class Board extends JPanel implements Constants{
	
	// Constructor (Initlalize)
	BufferedImage bi ;
	Player player ;
	//Enemy enemy;
	Enemy enemies[] ;
	int x[] = new int[10];
	int count =0;
	String gameMessage = "";
	
	Board() throws Exception{
		//Sprite sprite = new Sprite();
		setSize(BOARD_WIDTH, BOARD_HEIGHT); // Board Size Set
		bi = ImageIO.read(Board.class.getResource("BG.jpg")); // Image Read
		player = new Player();
		enemies = new Enemy[MAX_ENEMY]; // all enemies are null
		loadEnemies();
		setFocusable(true);
		bindEvents();
		gameLoop();
		
	
	}
	
	void enemyFall() {
		for(Enemy e : enemies) {
			if(!e.isAlive) {
			e.fall();
			}
		}
	}
	
	boolean isCollide(Sprite one, Sprite two){
		int xDistance = Math.abs(one.getX() - two.getX());
		int yDistance = Math.abs(one.getY() - two.getY());
		int w = Math.max(one.getW(), two.getW());
		int h = Math.max(one.getH(), two.getH());
		return xDistance<=(w-40) && yDistance<=h-60;
		//return xDistance<=(w-10) && yDistance<=h-10;
		
	}
	
	boolean isCollideBullet(Sprite one, Sprite two){
		int xDistance = Math.abs(one.getX() - two.getX());
		int yDistance = Math.abs(one.getY() - two.getY());
		int w = Math.max(one.getW(), two.getW());
		int h = Math.max(one.getH(), two.getH());
		//return xDistance<=(w-70) && yDistance<=h-108;
		return xDistance<=(w-50) && yDistance<=h;
		
	}
	
	
	void checkCollision() {
		// Check Player is Collide with Enemy or not
		for(Enemy e : enemies) {
			if(isCollide(player, e)) {
				gameMessage = "GAME OVER!";
				timer.stop();
				return;
			}
		}
		//check enemy is collide with bullet or not
		for(Bullet bullet : player.getBullets()) {
			for(Enemy e : enemies) {
				if(isCollideBullet(e, bullet)) {
					e.isAlive = false;
				
				}
			}
		}
	}
	
	void isGameWin() {
		if(player.outOfScreen()) {
			gameMessage = "GAME WIN!";
			timer.stop();
		}
	}
	
	
	
	void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("KeyPress.....");
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.move();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveleft();
				}
				if(e.getKeyCode()== KeyEvent.VK_SPACE) {
					player.jump();
				}
				else if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					player.addBullet(player.getX() + player.getW(), player.getY() + player.getH()-140);
				}
				
			}
		});
	}
	Timer timer;
	
	void gameLoop(){
		
		// Delay
		 timer = new Timer(50,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				checkCollision();
				isGameWin();
				player.fall();
				enemyFall();
				count++;
				
			}
		});
		timer.start();
	}
	
	void loadEnemies() throws IOException {
		int x = 250;
		final int GAP = 250;
		int speed = 2;
		for (int i = 0 ; i<enemies.length; i++) {
			enemies[i] = new Enemy(x, speed);
			speed = speed + 4;
			x = x + GAP;
			
		}
	}
	void printBullets(Graphics pen) {
		ArrayList<Bullet> bullets = player.getBullets();
		for(int i = 0; i<bullets.size(); i++) {
			Bullet currentBullet = bullets.get(i);
			currentBullet.draw(pen);
			currentBullet.move();
			if(currentBullet.outOfScreen()) {
				bullets.remove(i);
			//	System.out.println(bullets.size());
			}
		}
//		for(Bullet bullet : bullets) {
//			bullet.draw(pen);
//			bullet.move();
//			if(bullet.outOfScreen()) {
//				bullets.remove(bullet);
//			}
//		}
	}
	
	
	
	void printEnemies(Graphics pen) {
		for(Enemy e : enemies) {
			e.draw(pen);
			e.move();
		}
	}
	
	void printMessage(Graphics pen) {
		if(player.outOfScreen()) {
			pen.setColor(Color.GREEN);
			pen.setFont(new Font("times", Font.BOLD, 50));
			pen.drawString(gameMessage, BOARD_WIDTH/2-120,BOARD_HEIGHT/2-150);
		}
	else{
		pen.setColor(Color.RED);
		pen.setFont(new Font("times", Font.BOLD, 50));
		pen.drawString(gameMessage, BOARD_WIDTH/2-120,BOARD_HEIGHT/2-150);
		}
		
	}
	
	// Painting on Board
	@Override
	public void  paintComponent(Graphics pen){
//		pen.setColor(Color.RED);
//		pen.fillRect(10, 50, 70, 50);
//		pen.drawRect(20, 100, 100, 100);
//		pen.drawOval(300, 50, 100, 100);
//		pen.setFont(new Font("times", Font.BOLD, 50));
//		pen.drawString("Game 2022", 300, 300);
		pen.drawImage(bi,0,0,BOARD_WIDTH,BOARD_HEIGHT, null);
		player.draw(pen);
		printEnemies(pen);
		printBullets(pen);
		if(gameMessage.length()>0) {
		printMessage(pen);
		}
		//enemy.draw(pen);
		
	}

}
