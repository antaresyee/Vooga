package movement;

import java.awt.Point;

import gameObjects.GameObject;

public class TargetedMovement extends Movement {
	
	Point myTarget;
	
	public TargetedMovement(Point t){
		myTarget = t;
	}

	@Override
	public void move(GameObject o) {
		if (o.getX() < myTarget.x){
			o.setHorizontalSpeed(.3);
		}
		if (o.getX() > myTarget.x){
			o.setHorizontalSpeed(-.3);
		}
		if (o.getY() < myTarget.y){
			o.setVerticalSpeed(.3);
		}
		if (o.getY() > myTarget.y){
			o.setVerticalSpeed(-.3);
		}
		if (o.getX() > myTarget.x && o.getX() < myTarget.x + 3){
			o.setHorizontalSpeed(0);
		}
		if (o.getX() < myTarget.x && o.getX() > myTarget.x - 3){
			o.setHorizontalSpeed(0);
		}
		if (o.getY() > myTarget.y && o.getY() < myTarget.y + 3){
			o.setVerticalSpeed(0);
		}
		if (o.getY() < myTarget.y && o.getY() > myTarget.y - 3){
			o.setVerticalSpeed(0);
		}
		
	}

}
