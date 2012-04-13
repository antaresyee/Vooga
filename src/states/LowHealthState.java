package states;

import gameObjects.Enemy;
import movement.Movement;

/**
 * 
 * @author James Pagliuca
 *
 */

public class LowHealthState extends State {
	
	private int lowHealthPoint;

	public LowHealthState(Enemy e, Movement m, int p, int l){
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
		lowHealthPoint = l;
	}

	@Override
	public boolean shouldBeCurrentState() {
		return myEnemy.getCurrentHealth() < lowHealthPoint && takesPriority();
	}

}
