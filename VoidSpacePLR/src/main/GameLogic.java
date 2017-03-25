package main;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import model.Asteroid;
import model.Bullet;
import model.EnemyShip;
import model.Ship;
import sounds.SoundManager;


/**
 * Handles general game logic and status.
 */
public class GameLogic {
	private static GameScreen gameScreen;
	private GameStatus status;
	private SoundManager soundMan;
	private static Asteroid[] list = new Asteroid[10];
	private Ship ship;
	private Asteroid asteroid;
	private EnemyShip enemy;
	private List<Bullet> bullets;
	//private List<Asteroid> asteroids;

	/**
	 * Craete a new game logic handler
	 * @param gameScreen the game screen
	 */
	public GameLogic(GameScreen gameScreen){
		this.gameScreen = gameScreen;

		// initialize game status information
		status = new GameStatus();
		// initialize the sound manager
		soundMan = new SoundManager();

		// init some variables
		bullets = new ArrayList<Bullet>();
	}

	/**
	 * Returns the game status
	 * @return the game status 
	 */
	public GameStatus getStatus() {
		return status;
	}

	public SoundManager getSoundMan() {
		return soundMan;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	/**
	 * Prepare for a new game.
	 */
	public void newGame(){
		status.setGameStarting(true);

		// init game variables
		bullets = new ArrayList<Bullet>();

		status.setShipsLeft(5);
		status.setGameOver(false);
		status.setAsteroidsDestroyed(0);
		status.setNewAsteroid(false);
		status.setNewEnemy(false);

		// init the ship and the asteroid
		newShip(gameScreen);
		newAsteroid(gameScreen);
		newEnemy(gameScreen);

		// prepare game screen
		gameScreen.doNewGame();

		// delay to display "Get Ready" message for 1.5 seconds
		Timer timer = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameStarting(false);
				status.setGameStarted(true);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	/**
	 * Check game or level ending conditions.
	 */
	public void checkConditions(){
		// check game over conditions
		if(!status.isGameOver() && status.isGameStarted()){
			if(status.getShipsLeft() == 0){
				gameOver();
			}
		}
	}

	/**
	 * Actions to take when the game is over.
	 */
	public void gameOver(){
		status.setGameStarted(false);
		status.setGameOver(true);
		gameScreen.doGameOver();

		// delay to display "Game Over" message for 3 seconds
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameOver(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	/**
	 * Fire a bullet from ship.
	 */
	public void fireBullet(){
		Bullet bullet = new Bullet(ship);
		bullets.add(bullet);
		soundMan.playBulletSound();
	}
	public void fireEnemyBullet(){
		Bullet bullet = new Bullet(enemy);
		bullet.setSpeed(-12);
		bullets.add(bullet);
		soundMan.playAsteroidExplosionSound();
	}

	/**
	 * Move a bullet once fired.
	 * @param bullet the bullet to move
	 * @return if the bullet should be removed from screen
	 */
	public boolean moveBullet(Bullet bullet){
		if(bullet.getY() - bullet.getSpeed() >= 0 || bullet.getY() - bullet.getSpeed() <= gameScreen.getHeight() || bullet.getX() - bullet.getSpeed() <= gameScreen.getWidth() || bullet.getX() - bullet.getSpeed() >= 0){
			bullet.translate(0, -bullet.getSpeed());
			return false;
		}
		else{
			return true;
		}
	}

	/**
	 * Create a new ship (and replace current one).
	 */
	public Ship newShip(GameScreen screen){
		this.ship = new Ship(screen);
		return ship;
	}

	/**
	 * Create a new asteroid.
	 */
	public Asteroid newAsteroid(GameScreen screen){
		this.asteroid = new Asteroid(screen);
		return asteroid;
	}

	/**
	 * Returns the ship.
	 * @return the ship
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Returns the asteroid.
	 * @return the asteroid
	 */
	public Asteroid getAsteroid() {
		return asteroid;
	}

	/**
	 * Returns the list of bullets.
	 * @return the list of bullets
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}

	public EnemyShip newEnemy(GameScreen screen) {
		this.enemy = new EnemyShip(screen);
		return this.enemy;
	}

	public EnemyShip getEnemy() {
		return enemy;
	}

	public static void setDirection(){
		for(int i = 0; i < list.length; i++){
			list[i].setDirection();
		}
	}
	public static void createAsteroids(){
		for(int i = 0; i < 10; i++){
			list[i] = (new Asteroid(gameScreen));
		}
	}
	public Asteroid[] getAsteroids() {
		return list;
	}
}
