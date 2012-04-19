package movement;

import java.util.ArrayList;

/**
 * Data class used for holding all possible MovementFactories. Iterated over by
 * the Enemy class during loading
 * 
 * @author James Pagliuca
 * 
 */

public class MovementFactories {

	private ArrayList<MovementFactory> allMovementFactories;

	public MovementFactories() {
		allMovementFactories = new ArrayList<MovementFactory>();
		BackForthMovement.BFMovementFactory bf = new BackForthMovement.BFMovementFactory();
		TargetedMovement.TargetedMovementFactory t = new TargetedMovement.TargetedMovementFactory();
		allMovementFactories.add(bf);
		allMovementFactories.add(t);
	}

	public ArrayList<MovementFactory> getAllMovementFactories() {
		return allMovementFactories;
	}
}
