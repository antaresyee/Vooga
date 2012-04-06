package movement;

import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 *
 */

public abstract class Movement {
	
	protected double speed;
		
	public abstract void move(GameObject o);
	
}
