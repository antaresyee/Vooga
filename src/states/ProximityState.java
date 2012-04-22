package states;

import movement.Movement;
import game.PlayerInfo;
import gameObjects.Enemy;
import gameObjects.GameObject;

public class ProximityState extends State {

	private PlayerInfo playerInfo;
	private int upperBound;
	private int lowerBound;

	public ProximityState(Enemy e, Movement m, int p, int u, int l) {
		playerInfo = new PlayerInfo();
		myEnemy = e;
		myMovement = m;
		upperBound = u;
		lowerBound = l;
		
	}

	@Override
	public boolean shouldBeCurrentState() {
		return false;
	}
	
	private double getDistance(double x, double y){
		return Math.sqrt(Math.pow((myEnemy.getX() - x), 2) + Math.pow((myEnemy.getY() - y), 2));
	}

}
