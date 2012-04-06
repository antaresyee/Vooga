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
	public void checkStateChange() {
		if (myEnemy.getCurrentHealth() == 0){
			myEnemy.setX(1000);
			myEnemy.setY(1000);
			myEnemy.setSpeed(0, 0);
		}
	}

}
