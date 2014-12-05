package com.github.kalvisan.spaceship.materiali;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.kalvisan.spaceship.SpaceShipMain;

public class Missile {

	private int			x, y;
	private Image		image;
	boolean				visible;

	private final int	BOARD_WIDTH		= SpaceShipMain.WIDTH-10;
	private final int	MISSILE_SPEED	= 2;

	public Missile(int x, int y) {

		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream("/missile.png"));
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
		x += MISSILE_SPEED;		
		if (x > BOARD_WIDTH) visible = false;
		}
	}
}
