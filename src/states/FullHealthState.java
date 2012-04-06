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
	public void checkStateChange() {
		if (myEnemy.getCurrentHealth() < 250) {
			myEnemy.setState(myEnemy.getHalfHealthState());
		}
	}

}
