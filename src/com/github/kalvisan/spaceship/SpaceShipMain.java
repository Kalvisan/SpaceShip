package com.github.kalvisan.spaceship;

import javax.swing.JFrame;

/**
 * @author Kalvisan
 * @version 2.0v
 */
public class SpaceShipMain extends JFrame {
	private static final long	serialVersionUID	= 1L;
	public static final double	version				= 2.0;

	public static final int		WIDTH				= 400;
	public static final int		HEIGHT				= 350;
	public static boolean turnoff;

	public SpaceShipMain() {
		
		add(new Board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Space Ship " + version + "v");
		setResizable(false);
		setVisible(true);
		
		if(turnoff == true){
			setVisible(false);
			dispose();
		}
	}

	public static void main(String[] args) {
		Board.shield = false;
		Board.Life = 10;
		Board.Score = 0;
		turnoff = false;
		new SpaceShipMain();
	}
}