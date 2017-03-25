package sounds;

import java.applet.Applet; 
import java.applet.AudioClip;

import main.GameScreen;

/**
 * Manages and plays the game's sounds.
 */
public class SoundManager {
	private static final boolean SOUND_ON = true;

    private AudioClip shipExplosionSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/sounds/shipExplosion.wav"));
    private AudioClip bulletSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/sounds/laser.wav"));
    private AudioClip GameOver = Applet.newAudioClip(GameScreen.class.getResource(
    "/sounds/GameOver.wav"));
    private AudioClip BackGroud = Applet.newAudioClip(GameScreen.class.getResource(
    	    "/sounds/Neon.wav")); // taken from https://www.youtube.com/watch?v=iNGpW4zmGVg
    
    
    /**
     */
    public void playBulletSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				bulletSound.play();
    			}
    		}).start();
    	}
    }
    public void playBackGroud(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				BackGroud.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for ship explosions.
     */
    public void playShipExplosionSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				shipExplosionSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for asteroid explosions.
     */
    public void playAsteroidExplosionSound(){
		// play sound for asteroid explosions
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				shipExplosionSound.play();
    			}
    		}).start();
    	}
    }
    public void playGameOverSound(){
		// play sound for asteroid explosions
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				GameOver.play();
    			}
    		}).start();
    	}
    }
}
