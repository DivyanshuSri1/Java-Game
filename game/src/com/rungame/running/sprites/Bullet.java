package com.rungame.running.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rungame.running.Constants;

public class Bullet extends Sprite implements Constants {
	public Bullet(int x, int y) {
		h = 8;
		w =70;
		this.x= x;
		this.y= y;
		speed = 20;
		try {
			bi = ImageIO.read(Player.class.getResource("bullet.png"));
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean outOfScreen() {
		if(x>BOARD_WIDTH) {
				return true;
		}
		return false;
	}
	@Override
	public void move() {
		x = x+ speed;
		// TODO Auto-generated method stub
	}

}
