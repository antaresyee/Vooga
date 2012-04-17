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

	public TargetedMovement(double s) {
		playerInfo = new PlayerInfo();
		myTargetX = playerInfo.getPlayerX();
		myTargetY = playerInfo.getPlayerY();
		speed = s;
	}

	@Override
	public void move(GameObject o) {
		double myY = o.getY();
		double myX = o.getX();
		if (myY < myTargetY) {
			o.setVerticalSpeed(speed);
		}
		if (myY > myTargetY) {
			o.setVerticalSpeed(-speed);
		}
		if (myX < myTargetX) {
			o.setHorizontalSpeed(speed);
		}
		if (myX > myTargetX) {
			o.setHorizontalSpeed(-speed);
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
		speed = s;
	}

	public static class TargetedMovementFactory extends MovementFactory{
		
		public TargetedMovementFactory(){
			myName = "T";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			double speed = Double.parseDouble(parameters[1]);
			return new TargetedMovement(speed);
		}
		
	}

}
