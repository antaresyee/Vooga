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

	public LowHealthState(Enemy e, Movement m, int p, int l) {
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
		lowHealthPoint = l;
	}

	@Override
	public boolean shouldBeCurrentState() {
		return myEnemy.getCurrentHealth() < lowHealthPoint && takesPriority();
	}

	public static class LHStateFactory extends StateFactory {

		public LHStateFactory() {
			myName = "LH";
		}

		@Override
		public State makeMyState(Enemy e, String[] parameters,
				ArrayList<Movement> movementTypes, int index) {
			return new LowHealthState(e, movementTypes.get(index),
					Integer.parseInt(parameters[1]),
					Integer.parseInt(parameters[2]));
		}

	}

}
