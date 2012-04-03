package movement;

import gameObjects.GameObject;

public class BackForthMovement extends Movement {

	int leftBound;
	int rightBound;
	
	public BackForthMovement(int l, int r){
		leftBound = l;
		rightBound = r;
	}

	@Override
	public void move(GameObject o) {
		System.out.println(o.getX());
		if (o.getX() >= rightBound){
			o.setHorizontalSpeed(-.3);
		}
		else if (o.getX() < leftBound){
			o.setHorizontalSpeed(.3);
		}
	}

}
