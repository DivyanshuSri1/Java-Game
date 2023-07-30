package com.rungame.running.sprites;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.rungame.running.Constants;

public class Enemy extends Sprite implements Constants {
	public boolean isAlive;
	public Enemy(int x,int speed) throws IOException{
		this.x= x;
		y = 50;
		w = 100;
		h = 100;
		isAlive = true;
		this.speed = speed;
		bi = ImageIO.read(Enemy.class.getResource("spider.png"));
	}
	@Override
	public void move() {
		y = y + speed;
		outOfScreen();
	}
	public void outOfScreen() {
		if(y>BOARD_HEIGHT && isAlive) {
			y = 0;
		}
	}
	public void fall() {
		x = x + 20;
	}
	
}
