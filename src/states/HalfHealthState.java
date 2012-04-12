package states;

import gameObjects.Enemy;
import movement.Movement;

/**
 * 
 * @author James Pagliuca
 *
 */

public class HalfHealthState extends State {

	public HalfHealthState(Enemy e, Movement m, int p){
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
	}

	@Override
	public boolean shouldBeCurrentState() {
		return myEnemy.getCurrentHealth() < 250 && takesPriority();
	}

}
