package com.github.kalvisan.spaceship.materiali;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.github.kalvisan.spaceship.Board;
import com.github.kalvisan.spaceship.Craft;
import com.github.kalvisan.spaceship.SpaceShipMain;

public class Monsters {

	private int			x, y;
	private Image		image;
	boolean				visible;

	private final int	MONSTER_WIDTH		= 0;
	private final int	MONSTER_SPEED	= 2;

	public Monsters(int x, int y) {
		Random n = new Random();
		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream("/monster"+n.nextInt(3)+".png"));
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
	
	private boolean range(int py) {
		for (int i = 0; i < 38; i++) {
			if (py == y+i) return true;
		}
		for (int i = 0; i < 10; i++) {
			if (py == y-i) return true;
		}
		return false;
	}

	public void move() {
		if(visible == true) {
		x -= MONSTER_SPEED;
		
		ArrayList<Missile> ms = Craft.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			for (int j = 0; j < 10; j++)
			if((m.getX() == x+j) && range(m.getY())) {
				Craft.spawnBoom(x, y);
				Board.Score = Board.Score + 10;
				visible = false;
				m.setInvisible();
				break;
			}
		}
		
		for (int i = 0; i < 30; i++) {
			if(x == Craft.getX() && (Craft.getShipRange(y+i))) {
				Craft.spawnBoom(Craft.getX(), Craft.getY());
				Board.Life = Board.Life - 1;
				if(Board.shield == true) {
					Board.Life = Board.Life + 1;
					Board.shield = false;
				}
				if(Board.Life < 0) SpaceShipMain.turnoff = true;
				visible = false;
				break;
			}
		}
		if (x < MONSTER_WIDTH) visible = false;
		}
	}
}
