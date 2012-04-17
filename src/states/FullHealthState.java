package states;

import java.util.ArrayList;

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

	public static class FHStateFactory extends StateFactory {

		public FHStateFactory() {
			myName = "FH";
		}

		@Override
		public State makeMyState(Enemy e, String[] parameters,
				ArrayList<Movement> movementTypes, int index) {
			return new FullHealthState(e, movementTypes.get(index),
					Integer.parseInt(parameters[1]));
		}

	}

}
