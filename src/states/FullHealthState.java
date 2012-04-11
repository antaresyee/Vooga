package states;

import movement.Movement;
import gameObjects.Enemy;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class FullHealthState extends State {

	public FullHealthState(Enemy e, Movement m) {
		myEnemy = e;
		myMovement = m;
	}

	@Override
	public boolean shouldBeCurrentState() {
		if (myEnemy.getCurrentHealth() > 250){
			return true;
		}
		return false;
	}


}
