package movement;


import gameObjects.GameObject;

public class TargetedMovement extends Movement {
	
	private GameObject myTarget;
	
	public TargetedMovement(GameObject o, double s){
		myTarget = o;
		speed = s;
	}

	@Override
	public void move(GameObject o) {
		double myY = o.getY();
		double myX = o.getX();
		double targetY = myTarget.getY();
		double targetX = myTarget.getX();
		if (myY < targetY){
			o.setVerticalSpeed(speed);
		}
		if (myY > targetY){
			o.setVerticalSpeed(-speed);
		}
		if (myX < targetX){
			o.setHorizontalSpeed(speed);
		}
		if (myX > targetX){
			o.setHorizontalSpeed(-speed);
		}
		
		if (myX > targetX && myX < targetX + 5){
			o.setHorizontalSpeed(0);
		}
		if (myX < targetX && myX > targetX - 5){
			o.setHorizontalSpeed(0);
		}
		if (myY > targetY && myY < targetY + 5){
			o.setVerticalSpeed(0);
		}
		if (myY < targetY && myY > targetY - 5){
			o.setVerticalSpeed(0);
		}
		
	}
	
	public void changeTarget(GameObject o){
		myTarget = o;
	}

}
