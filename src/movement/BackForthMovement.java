package movement;

import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 *
 */

public class BackForthMovement extends Movement {

	private double leftBound;
	private double rightBound;
	
	public BackForthMovement(double l, double r, double s){
		leftBound = l;
		rightBound = r;
		speed = s;
	}

	@Override
	public void move(GameObject o) {
		//System.out.println(o.getX());
		if (o.getX() >= rightBound){
			o.setHorizontalSpeed(-speed);
		}
		else if (o.getX() <= leftBound){
			o.setHorizontalSpeed(speed);
		}
	}

}
