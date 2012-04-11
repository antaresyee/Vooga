package states;

import gameObjects.Enemy;
import movement.Movement;

/**
 * 
 * @author James Pagliuca
 *
 */

public class HalfHealthState extends State {

	public HalfHealthState(Enemy e, Movement m){
		myEnemy = e;
		myMovement = m;
	}

	@Override
	public boolean shouldBeCurrentState() {
		if (myEnemy.getCurrentHealth() < 250){
			return true;
		}
		return false;
	}

}
