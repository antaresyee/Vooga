package movement;

import game.PlayerInfo;
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
		updateTarget();
		mySpeed = .2;
	}

	@Override
	public void move(GameObject o) {
		double myY = o.getY();
		double myX = o.getX();
		updateTarget();
		movementDynamics(o, myY, myX);

	}

	private void movementDynamics(GameObject o, double myY, double myX) {
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

	private void updateTarget() {
		myTargetX = playerInfo.getPlayerX();
		myTargetY = playerInfo.getPlayerY();
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
