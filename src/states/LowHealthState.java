package states;

import java.util.ArrayList;

import gameObjects.Enemy;
import movement.Movement;

/**
 * 
 * @author James Pagliuca
 * 
 */

public class LowHealthState extends State {

	private int lowHealthPoint;

	public LowHealthState(Enemy e, Movement m, int p) {
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
		lowHealthPoint = 200;
	}

	@Override
	public boolean changeCondition() {
		return myEnemy.getCurrentHealth() < lowHealthPoint;
	}

	public static class LHStateFactory extends StateFactory {

		public LHStateFactory() {
			myName = "LH";
		}

		@Override
		public State makeMyState(Enemy e, String[] parameters,
				ArrayList<Movement> movementTypes, int index) {
			return new LowHealthState(e, movementTypes.get(index),
					Integer.parseInt(parameters[1]));
		}

	}

	

}
