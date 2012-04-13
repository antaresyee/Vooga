package gameObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.golden.gamedev.Game;

import states.FullHealthState;
import states.HalfHealthState;
import states.State;
import states.StateLoader;
import movement.BackForthMovement;
import movement.Movement;
import movement.TargetedMovement;

/**
 * 
 * @author James Pagliuca
 * 
 */
public class Enemy extends GameObject {

	private ArrayList<State> possibleStates;
	private State currentState;
	private StateLoader loader;
	private int currentHealth;

	public Enemy(double x, double y, String imgPath, FileInputStream f) {
		myX = x;
		myY = y;
		myImgPath = imgPath;
		myType = "Enemy";
		setLocation(myX, myY);
		loader = new StateLoader(f);
		possibleStates = new ArrayList<State>();
		parseStates(parseMovementTypes());
		currentState = possibleStates.get(0);
		currentHealth = 500;
	}

	@Override
	public String getImgPath() {
		return myImgPath;
	}

	public void move() {
		currentState.move();
		currentHealth--;
		System.out.println(currentHealth);
	}

	public void update() {
		move();
		checkState();
	}

	public void checkState() {
		for (State s : possibleStates) {
			if (s.shouldBeCurrentState()) {
				currentState = s;
			}
		}
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void sustainDamage(int damage) {
		currentHealth -= damage;
	}

	private ArrayList<Movement> parseMovementTypes() {
		System.out.println("in parseMovementTypes");
		ArrayList<String> movementInfo = loader.getMovementInfo();
		ArrayList<Movement> movementTypes = new ArrayList<Movement>();
		for (String s : movementInfo) {
			String[] parameters = s.split(",");
			if (parameters[0].equals("BF")) {
				BackForthMovement m = parseBackForthMovement(parameters);
				movementTypes.add(m);
			}
		}
		return movementTypes;
	}

	private BackForthMovement parseBackForthMovement(String[] parameters) {
		double leftBound = Double.parseDouble(parameters[1]);
		double rightBound = Double.parseDouble(parameters[2]);
		double speed = Double.parseDouble(parameters[3]);
		BackForthMovement m = new BackForthMovement(leftBound,
				rightBound, speed);
		return m;
	}

	private void parseStates(ArrayList<Movement> movementTypes) {
		System.out.println("in parseStates");
		ArrayList<String> stateInfo = loader.getStateInfo();
		for (int i = 0; i < stateInfo.size(); i++) {
			String[] parameters = stateInfo.get(i).split(",");
			if (parameters[0].equals("FH")) {
				System.out.println(parameters[1]);
				FullHealthState full = new FullHealthState(this,
						movementTypes.get(i), Integer.parseInt(parameters[1]));
				possibleStates.add(full);
			}
			if (parameters[0].equals("HH")) {
				HalfHealthState full = new HalfHealthState(this,
						movementTypes.get(i), Integer.parseInt(parameters[1]));
				possibleStates.add(full);
			}
		}
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		FileInputStream f;
		try {
			f = new FileInputStream("StateInfo1.txt");
			return new Enemy(x, y, imgPath, f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
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
