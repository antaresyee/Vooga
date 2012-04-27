package gameObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import enemyLoader.EnemyDataLoader;
import enemyLoader.MovementFactories;
import enemyLoader.StateFactories;

import levelLoadSave.ForSave;
import movement.Movement;
import movement.MovementFactory;
import states.State;
import states.StateFactory;
import weapons.Status;
import weapons.Weapon;

/**
 * 
 * @author James Pagliuca
 * 
 */

@ForSave
public class Enemy extends GameObject {

	private ArrayList<State> possibleStates;
	private State currentState;
	private EnemyDataLoader loader;
	private int currentHealth = 3;
	private List<Status> myStatuses;
	private List<Weapon> myWeapons;
	private Weapon myActiveWeapon;

	public Enemy(double x, double y, String imgPath, String filename) {
		myX = x;
		myY = y;
		myImgPath = imgPath;
		myType = "Enemy";
		myStatuses = new ArrayList<Status>();
		myWeapons = new ArrayList<Weapon>();
		
		setLocation(myX, myY);
		System.out.println(filename);
		try {
			FileInputStream f = new FileInputStream(filename);
			loader = new EnemyDataLoader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		possibleStates = new ArrayList<State>();
		parseStates(parseMovementTypes());
		currentState = possibleStates.get(0);
	}

	public void addWeapon(Weapon w) {
	    myWeapons.add(w);
	}
	
	@Override
	public String getImgPath() {
		return myImgPath;
	}

	public void move() {
		currentState.move();
	}
	
	public void setWeapon(int slot){
		myActiveWeapon = myWeapons.get(slot);
	}

	public void updateEnemy(long elapsedTime) {
		checkState();
		move();
		checkIfDead();
		for (Status s : myStatuses) {
			s.iterate(this);
		}
		if(myActiveWeapon != null){
			myActiveWeapon.fire(elapsedTime, myX, myY);
		}
	}

	private void checkIfDead() {
		if (currentHealth <= 0) {
			this.setActive(false);
		}
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

	public void addStatus(Status status) {
		myStatuses.add(status);
	}

	private ArrayList<Movement> parseMovementTypes() {
		ArrayList<MovementFactory> movementFactories = loader
				.getPossibleMovements();
		ArrayList<String> movementInfo = loader.getMovementInfo();
		ArrayList<Movement> movementTypes = new ArrayList<Movement>();
		for (String s : movementInfo) {
			String[] parameters = s.split(",");
			String name = parameters[0];
			System.out.println(name);
			for (MovementFactory f : movementFactories) {
				if (f.isMyMovement(name)) {
					Movement newMovement = f.makeMyMovement(parameters);
					movementTypes.add(newMovement);
				}
			}
		}
		return movementTypes;
	}

	private void parseStates(ArrayList<Movement> movementTypes) {
		ArrayList<StateFactory> stateFactories = loader.getPossibleStates();
		ArrayList<String> stateInfo = loader.getStateInfo();
		for (int i = 0; i < stateInfo.size(); i++) {
			String[] parameters = stateInfo.get(i).split(",");
			String name = parameters[0];
			for (StateFactory f : stateFactories) {
				if (f.isMyState(name)) {
					State newState = f.makeMyState(this, parameters,
							movementTypes, i);
					possibleStates.add(newState);
				}
			}
		}
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		String filename = god.getEnemyConfigFile();
		//Weapon w = god.getWeapon();
		return new Enemy(x, y, imgPath, filename);

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
