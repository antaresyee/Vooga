package states;

import movement.Movement;
import gameObjects.Enemy;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class FullHealthState extends State {

	public FullHealthState(Enemy e, Movement m, int p) {
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
	}

	@Override
	public boolean shouldBeCurrentState() {
		return myEnemy.getCurrentHealth() > 250 && takesPriority();
	}

}
