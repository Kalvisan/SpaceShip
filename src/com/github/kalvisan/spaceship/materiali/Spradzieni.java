package com.github.kalvisan.spaceship.materiali;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spradzieni {
	private Image	image;
	private boolean visible;
	private int		x, y,time;

	public Spradzieni(int x, int y,int time) {
		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream("/boom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = ii;
		visible = true;
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getTime() {
		return time;
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
	
	public void time() {
		time--;
		if(visible == true && time > 0) {	
			if(time == 1) visible = false;
		}
	}
}
