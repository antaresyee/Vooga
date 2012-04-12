package states;

import movement.Movement;

import gameObjects.Enemy;

/**
 * 
 * @author James Pagliuca
 *
 */
public abstract class State {
	
	protected Enemy myEnemy;
	protected Movement myMovement;
	protected int priorityLevel;
	
	public void move(){
		myMovement.move(myEnemy);
	}
	
	public int getPriorityLevel(){
		return priorityLevel;
	}
	
	public boolean takesPriority(){
		State currentState = myEnemy.getCurrentState();
		int currentPriorityLevel = currentState.getPriorityLevel();
		return priorityLevel >= currentPriorityLevel;
		
	}
	
	public abstract boolean shouldBeCurrentState();
}
