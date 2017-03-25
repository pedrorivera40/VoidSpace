package main;

public class GameLevel {
	private static int level = 1;
	
	public static int getLevel(){
		return level;
	}
	public static void levelUp(){
		if(level < 10)
		level++;
	}
	public static void reset(){
		level = 1;
	}
}
