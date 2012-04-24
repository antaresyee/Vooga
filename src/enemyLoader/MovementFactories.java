package enemyLoader;

import java.util.ArrayList;

import movement.BackForthMovement;
import movement.DiamondMovement;
import movement.MovementFactory;
import movement.PathMovement;
import movement.TargetedDiamondMovement;
import movement.TargetedMovement;
import movement.BackForthMovement.BFMovementFactory;
import movement.DiamondMovement.DiamondMovementFactory;
import movement.PathMovement.PathMovementFactory;
import movement.TargetedDiamondMovement.TDMovementFactory;
import movement.TargetedMovement.TargetedMovementFactory;

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
		DiamondMovement.DiamondMovementFactory d = new DiamondMovement.DiamondMovementFactory();
		TargetedDiamondMovement.TDMovementFactory td = new TargetedDiamondMovement.TDMovementFactory();
		allMovementFactories.add(bf);
		allMovementFactories.add(t);
		allMovementFactories.add(p);
		allMovementFactories.add(d);
		allMovementFactories.add(td);
	}

	public ArrayList<MovementFactory> getAllMovementFactories() {
		return allMovementFactories;
	}
}
