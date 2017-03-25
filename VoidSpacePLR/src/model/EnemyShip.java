package model;

import main.GameScreen;

public class EnemyShip extends Asteroid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnemyShip(GameScreen screen) {
		super(screen, 25, 25);
		this.setSize(25, 25);
	}
}
