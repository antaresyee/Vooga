package movement;

import game.PlayerInfo;
import gameObjects.Enemy;
import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class TargetedMovement extends Movement {

	private double myTargetX;
	private double myTargetY;
	private PlayerInfo playerInfo;

	public TargetedMovement() {
		playerInfo = new PlayerInfo();
		myTargetX = playerInfo.getPlayerX();
		myTargetY = playerInfo.getPlayerY();
		mySpeed = .2;
	}

	@Override
	public void move(GameObject o) {
		double myY = o.getY();
		double myX = o.getX();
		myTargetX = playerInfo.getPlayerX();
		myTargetY = playerInfo.getPlayerY();
		if (myY < myTargetY) {
			o.setVerticalSpeed(mySpeed);
		}
		if (myY > myTargetY) {
			o.setVerticalSpeed(-mySpeed);
		}
		if (myX < myTargetX) {
			o.setHorizontalSpeed(mySpeed);
		}
		if (myX > myTargetX) {
			o.setHorizontalSpeed(-mySpeed);
		}

		if (myX > myTargetX && myX < myTargetX + 5) {
			o.setHorizontalSpeed(0);
		}
		if (myX < myTargetX && myX > myTargetX - 5) {
			o.setHorizontalSpeed(0);
		}
		if (myY > myTargetY && myY < myTargetY + 5) {
			o.setVerticalSpeed(0);
		}
		if (myY < myTargetY && myY > myTargetY - 5) {
			o.setVerticalSpeed(0);
		}

	}
	
	public void setSpeed(double s){
		mySpeed = s;
	}

	public static class TargetedMovementFactory extends MovementFactory{
		
		public TargetedMovementFactory(){
			myName = "T";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			return new TargetedMovement();
		}
		
	}

}
