package states;

import gameObjects.Enemy;

import java.util.ArrayList;

import movement.Movement;

public abstract class StateFactory {

	protected String myName;

	public boolean isMyState(String name) {
		return name.equals(myName);
	}

	public abstract State makeMyState(Enemy e, String[] parameters,
			ArrayList<Movement> movementTypes, int index);

}
