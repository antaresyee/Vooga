package states;

import gameObjects.Enemy;
import movement.Movement;

public class HalfHealthState extends State {

	public HalfHealthState(Enemy e, Movement m){
		myEnemy = e;
		myMovement = m;
	}
	
	@Override
	public void checkStateChange() {
		if (myEnemy.getCurrentHealth() == 0){
			myEnemy.setActive(false);
		}
	}

}
