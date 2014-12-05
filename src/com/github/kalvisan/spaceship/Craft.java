package com.github.kalvisan.spaceship;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.github.kalvisan.spaceship.materiali.Bonusi;
import com.github.kalvisan.spaceship.materiali.Missile;
import com.github.kalvisan.spaceship.materiali.Monsters;
import com.github.kalvisan.spaceship.materiali.Spradzieni;

public class Craft {

	private String						craft		= "/craft.png";

	private int							dx;
	private int							dy;
	public static int					x;
	private static int					y;
	private int							t, t2;
	public static int					diff, bo;
	private Image						image, image2;

	private static ArrayList<Missile>	missiles;
	private ArrayList<Monsters>			mob;
	private ArrayList<Bonusi>			bon;
	public static ArrayList<Spradzieni> boom;

	private final static int			CRAFT_SIZE	= 23, SPRADZIENA_LAIKS = 50;

	public Craft() {
		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream(craft));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = ii;
		
		BufferedImage ii2 = null;
		try {
			ii2 = ImageIO.read(getClass().getResourceAsStream("/sd.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image2 = ii2;

		missiles = new ArrayList<Missile>();
		mob = new ArrayList<Monsters>();
		bon = new ArrayList<Bonusi>();
		boom = new ArrayList<Spradzieni>();
		x = 40;
		y = 60;
	}

	public static boolean getShipRange(int py) {
		for (int i = 0; i < 10; i++) {
			if (py == y + i) return true;
		}
		for (int i = 0; i < 10; i++) {
			if (py == y - i) return true;
		}
		return false;
	}

	public void move() {
		t++;
		t2++;
		bo--;
		x += dx;
		y += dy;
		if (x < 1) x = 1;
		if (y < 1) y = 1;
		if (x > SpaceShipMain.WIDTH / 4) x = SpaceShipMain.WIDTH / 4;
		if (y > SpaceShipMain.HEIGHT - 65) y = SpaceShipMain.HEIGHT - 65;
		// TODO Izmainit kad bus Score!
		diff = 90 - (Board.Score / 10);
		if(Board.Score < 1500)
		if (diff < 20) diff = 20;
		if (diff < 5) diff = 5;
		if (t > diff) {
			spawn();
			t = 0;
		}

		if ((Board.shield == true) && bo < 1) {
			Board.shield = false;
		}
		if (bo < 0) bo = 0;

		if (t2 > 2550) {
			bonus();
			t2 = 0;
		}
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public Image getShield() {
		return image2;
	}

	public static ArrayList<Missile> getMissiles() {
		return missiles;
	}

	public ArrayList<Monsters> getMob() {
		return mob;
	}

	public ArrayList<Bonusi> getBon() {
		return bon;
	}
	
	public ArrayList<Spradzieni> getBoom() {
		return boom;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			dx = -2;
		}

		if (key == KeyEvent.VK_D) {
			dx = 2;
		}

		if (key == KeyEvent.VK_W) {
			dy = -2;
		}

		if (key == KeyEvent.VK_S) {
			dy = 2;
		}
	}

	public void fire() {
		missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE / 2));
	}

	public static void spawnBoom(int x, int y) {
		boom.add(new Spradzieni(x, y,SPRADZIENA_LAIKS));
	}
	
	public void spawn() {
		Random random = new Random();
		mob.add(new Monsters(SpaceShipMain.WIDTH, random.nextInt(SpaceShipMain.HEIGHT - 79)));
	}

	public void bonus() {
		Random random = new Random();
		bon.add(new Bonusi(SpaceShipMain.WIDTH, random.nextInt(SpaceShipMain.HEIGHT - 55)));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			fire();
		}

		if (key == KeyEvent.VK_A) {
			dx = 0;
		}

		if (key == KeyEvent.VK_D) {
			dx = 0;
		}

		if (key == KeyEvent.VK_W) {
			dy = 0;
		}

		if (key == KeyEvent.VK_S) {
			dy = 0;
		}
	}
}
