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
		PathMovement.PathMovementFactory p = new PathMovement.PathMovementFactory();
		allMovementFactories.add(bf);
		allMovementFactories.add(t);
		allMovementFactories.add(p);
	}

	public ArrayList<MovementFactory> getAllMovementFactories() {
		return allMovementFactories;
	}
}
