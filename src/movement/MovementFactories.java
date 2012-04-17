package movement;

import java.util.ArrayList;

public class MovementFactories {
	
	private ArrayList<MovementFactory> allMovementFactories;

	public MovementFactories(){
		allMovementFactories = new ArrayList<MovementFactory>();
		BackForthMovement.BFMovementFactory bf = new BackForthMovement.BFMovementFactory();
		TargetedMovement.TargetedMovementFactory t = new TargetedMovement.TargetedMovementFactory();
		allMovementFactories.add(bf);
		allMovementFactories.add(t);
	}
	
	public ArrayList<MovementFactory> getAllMovementFactories(){
		return allMovementFactories;
	}
}
