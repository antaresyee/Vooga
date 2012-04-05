package states;

import movement.Movement;
import gameObjects.Enemy;

public abstract class State {
	
	protected Enemy myEnemy;
	protected Movement myMovement;
	
	public void move(){
		myMovement.move(myEnemy);
		checkStateChange();
	}
	
	public abstract void checkStateChange();
}
