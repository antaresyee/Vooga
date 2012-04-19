package movement;

import java.io.Serializable;

import gameObjects.Enemy;
import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class BackForthMovement extends Movement implements Serializable {

	private double myLeftBound;
	private double myRightBound;

	public BackForthMovement(double l, double r, double s) {
		myLeftBound = l;
		myRightBound = r;
		mySpeed = s;
	}

	@Override
	public void move(GameObject o) {
		// System.out.println(o.getX());
		if (o.getX() >= myRightBound) {
			o.setHorizontalSpeed(-mySpeed);
		} else if (o.getX() <= myLeftBound) {
			o.setHorizontalSpeed(mySpeed);
		}
	}
	
	public static class BFMovementFactory extends MovementFactory{
		
		public BFMovementFactory(){
			myName = "BF";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			double leftBound = Double.parseDouble(parameters[1]);
			double rightBound = Double.parseDouble(parameters[2]);
			double speed = Double.parseDouble(parameters[3]);
			return new BackForthMovement(leftBound,
					rightBound, speed);
		}
		
	}


}
