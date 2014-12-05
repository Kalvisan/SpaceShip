package com.github.kalvisan.spaceship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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

import com.github.kalvisan.spaceship.materiali.Bonusi;
import com.github.kalvisan.spaceship.materiali.Missile;
import com.github.kalvisan.spaceship.materiali.Monsters;
import com.github.kalvisan.spaceship.materiali.Spradzieni;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	private Timer			timer;
	private Craft			craft;
	public static boolean	shield;
	public static JPanel	gpanel	= new JPanel();
	public static int		Life,Score;

	public Board() {
		setBackground(new Color(255, 255, 255));
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);

		craft = new Craft();

		timer = new Timer(5, this);
		timer.start();

	}

	public void paint(Graphics g) {
		super.paint(g);
		
		BufferedImage ii = null;
		try {
			ii = ImageIO.read(getClass().getResourceAsStream("/space.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(ii, 0, 0, null);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(craft.getImage(), Craft.getX(), Craft.getY(), this);
		g2d.drawString("Life left:   " + Life + "       Score:  " + Score, 10, SpaceShipMain.HEIGHT - 35);

		if (shield == true) g2d.drawImage(craft.getShield(), Craft.getX(), Craft.getY(), this);

		ArrayList<Missile> ms = Craft.getMissiles();
		ArrayList<Monsters> mob = craft.getMob();
		ArrayList<Bonusi> bon = craft.getBon();
		ArrayList<Spradzieni> boom = craft.getBoom();
		
		
		for (int i = 0; i < boom.size(); i++) {
			Spradzieni m = (Spradzieni) boom.get(i);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}

		for (int i = 0; i < bon.size(); i++) {
			Bonusi m = (Bonusi) bon.get(i);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}

		for (int i = 0; i < mob.size(); i++) {
			Monsters m = (Monsters) mob.get(i);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public int t;
	

	public void actionPerformed(ActionEvent e) {
		ArrayList<Missile> ms = Craft.getMissiles();
		ArrayList<Monsters> mob = craft.getMob();
		ArrayList<Bonusi> bon = craft.getBon();
		ArrayList<Spradzieni> boom = craft.getBoom();
		
		for (int i = 0; i < boom.size(); i++) {
			Spradzieni m = (Spradzieni) boom.get(i);
			if (m.isVisible()) m.time();
			else boom.remove(i);
		}
		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			if (m.isVisible()) m.move();
			else ms.remove(i);
		}
		for (int i = 0; i < mob.size(); i++) {
			Monsters m = (Monsters) mob.get(i);
			if (m.isVisible()) m.move();
			else mob.remove(i);
		}
		for (int i = 0; i < bon.size(); i++) {
			Bonusi m = (Bonusi) bon.get(i);
			if (m.isVisible()) m.move();
			else bon.remove(i);
		}
		repaint();
		craft.move();

	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			craft.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
		}
	}
}
