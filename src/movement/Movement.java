package movement;

import gameObjects.GameObject;

public abstract class Movement {
	
	protected double speed;
		
	public abstract void move(GameObject o);
	
}
