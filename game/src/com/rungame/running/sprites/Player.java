package com.rungame.running.sprites;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.rungame.running.Board;
import com.rungame.running.Constants;

public class Player extends Sprite implements Constants{
	int force;
	boolean isJump;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	public Player() throws IOException{
		x= 20;
		h = 150;
		y = BOARD_HEIGHT -FLOOR -h;
		w = 80;
		isJump= false;
		speed = 10;
		bi = ImageIO.read(Player.class.getResource("player.png"));
	}
	
	public ArrayList<Bullet> getBullets(){
			return bullets;
	}
	
	public void addBullet(int x, int y) {
		bullets.add(new Bullet(x,y));
	}
	
	public void jump() {
		if(!isJump) {
			force = -15;
			y=y+force;
			isJump = true;
		}
	}
	
	public void fall() {
		if(y>=BOARD_HEIGHT-FLOOR-h) {
			isJump = false;
			return ;
		}
		force = force + GRAVITY;
		y = y + force;
		
	}
	
	
	
	@Override
	public void move() {
		x = x + speed;
	}
	public boolean outOfScreen() {
//		if(x+15>BOARD_WIDTH) {
//			return true;
//		}
		if(x+150>BOARD_WIDTH) {
			return true;
		}
		return false;
	}
	public void moveleft() {
		x = x - speed;
	}
	
	
}
