package game;

public class PlayerInfo {
	
	private static double playerX;
	private static double playerY;
	
	public PlayerInfo(){
		playerX = 0;
		playerY = 0;
	}
	
	public void updatePlayerPosition(double x, double y){
		playerX = x;
		playerY = y;
	}
	
	public double getPlayerX(){
		return playerX;
	}
	
	public double getPlayerY(){
		return playerY;
	}

}
