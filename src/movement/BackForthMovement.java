package movement;

import java.io.Serializable;

import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class BackForthMovement extends Movement implements Serializable {

	private double leftBound;
	private double rightBound;

	public BackForthMovement(double l, double r, double s) {
		leftBound = l;
		rightBound = r;
		speed = s;
	}

	@Override
	public void move(GameObject o) {
		// System.out.println(o.getX());
		if (o.getX() >= rightBound) {
			o.setHorizontalSpeed(-speed);
		} else if (o.getX() <= leftBound) {
			o.setHorizontalSpeed(speed);
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
