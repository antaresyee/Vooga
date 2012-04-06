package gameObjects;

import states.FullHealthState;
import states.HalfHealthState;
import states.State;
import movement.BackForthMovement;
import movement.Movement;

/**
 * 
 * @author James Pagliuca
 * 
 */
public class Enemy extends GameObject {

	private State fullHealthState;
	private State halfHealthState;
	private State currentState;
	private int currentHealth;

	public Enemy(double x, double y, String imgPath) {
		myX = x;
		myY = y;
		myImgPath = imgPath;
		myType = "Enemy";
		setLocation(myX, myY);
		fullHealthState = new FullHealthState(this, new BackForthMovement(
				getX(), getX() + 200, .2));
		halfHealthState = new HalfHealthState(this, new BackForthMovement(
				getX(), getX() + 200, 1));
		currentState = fullHealthState;
		currentHealth = 500;
	}

	@Override
	public String getImgPath() {
		return myImgPath;
	}

	public void move() {
		currentState.move();
		currentHealth--;
	}

	public void update() {
		move();
	}

	public void setState(State s) {
		currentState = s;
	}

	public State getCurrentState() {
		return currentState;
	}

	public State getFullHealthState() {
		return fullHealthState;
	}

	public State getHalfHealthState() {
		return halfHealthState;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		return new Enemy(x, y, imgPath);
	}

	/**
	 * Enemy() and getFactory() must be implemented by each game object; they
	 * are used for the factory system.
	 */
	private Enemy() {
		myType = "Enemy";
	}

	public static GameObjectFactory getFactory() {
		return new GameObjectFactory(new Enemy());
	}

}
