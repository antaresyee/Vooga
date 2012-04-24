package game;

public class PlayerInfo {
	
	private static double playerX = 0;
	private static double playerY = 0;
	private static boolean movingUp = false;
	private static boolean movingDown = false;
	private static boolean movingLeft = false;
	private static boolean movingRight = false;
	
	public PlayerInfo(){
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
	
	public void setUpwardMovement(boolean b){
    	movingUp = b;
    }
    
    public void setDownwardMovement(boolean b){
    	movingDown = b;
    }
    
    public void setLeftwardMovement(boolean b){
    	movingLeft = b;
    }
    
    public void setRightwardMovement(boolean b){
    	movingRight = b;
    }
    
    public boolean getUpwardMovement(){
    	return movingUp;
    }
    
    public boolean getDownwardMovement(){
    	return movingDown;
    }

    public boolean getLeftwardMovement(){
    	return movingLeft;
    }
    
    public boolean getRightwardMovement(){
    	return movingRight;
    }
}
