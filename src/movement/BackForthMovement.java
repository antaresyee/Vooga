package movement;

import gameObjects.GameObject;

public class BackForthMovement extends Movement {

	int leftBound;
	int rightBound;
	
	public BackForthMovement(int l, int r, double s){
		leftBound = l;
		rightBound = r;
		speed = s;
	}

	@Override
	public void move(GameObject o) {
		System.out.println(o.getX());
		if (o.getX() >= rightBound){
			o.setHorizontalSpeed(-speed);
		}
		else if (o.getX() < leftBound){
			o.setHorizontalSpeed(speed);
		}
	}

}
