package com.github.kalvisan.spaceship.materiali;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.github.kalvisan.spaceship.Board;
import com.github.kalvisan.spaceship.Craft;

public class Bonusi {

	private int			x, y;
	private Image		image;
	boolean				visible;

	private final int	BONUS_WIDTH		= 0;
	private final int	BONUS_SPEED	= 1;

	public Bonusi(int x, int y) {
		Random n = new Random();
		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream("/bonus"+ n.nextInt(3) +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = ii;
		visible = true;
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public void setInvisible() {
		visible = false;
	}

	public void move() {
		if(visible == true) {
		x -= BONUS_SPEED;
		
		for (int i = 0; i < 30; i++) {
			if(x == Craft.getX() && (Craft.getShipRange(y+i))) {
				visible = false;
				Board.shield = true;
				Craft.bo = 800;
			}
		}
		if (x < BONUS_WIDTH) visible = false;
		}
	}
}
