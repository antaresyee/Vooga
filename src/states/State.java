package states;

import movement.Movement;

import gameObjects.Enemy;

/**
 * 
 * @author James Pagliuca
 *
 */
public abstract class State {
	
	protected Enemy myEnemy;
	protected Movement myMovement;
	
	public void move(){
		myMovement.move(myEnemy);
	}
	
	public abstract boolean shouldBeCurrentState();
}
