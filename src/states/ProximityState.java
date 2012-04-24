package states;

import game.PlayerInfo;
import gameObjects.Enemy;

import java.util.ArrayList;

import movement.Movement;

public class ProximityState extends State {

	private PlayerInfo playerInfo;
	private int upperBound;
	private int lowerBound;

	public ProximityState(Enemy e, Movement m, int l, int u, int p) {
		playerInfo = new PlayerInfo();
		myEnemy = e;
		myMovement = m;
		priorityLevel = p;
		lowerBound = l;
		upperBound = u;
	}

	@Override
	public boolean changeCondition() {
		double playerX = playerInfo.getPlayerX();
		double playerY = playerInfo.getPlayerY();
		double distance = getDistance(playerX, playerY);
		return lowerBound < distance && distance < upperBound;
	}

	private double getDistance(double x, double y) {
		return Math.sqrt(Math.pow((myEnemy.getX() - x), 2)
				+ Math.pow((myEnemy.getY() - y), 2));
	}

	public static class ProximityStateFactory extends StateFactory {

		public ProximityStateFactory() {
			myName = "PR";
		}

		@Override
		public State makeMyState(Enemy e, String[] parameters,
				ArrayList<Movement> movementTypes, int index) {
			int upperBound = Integer.parseInt(parameters[1]);
			int lowerBound = Integer.parseInt(parameters[2]);
			int priority = Integer.parseInt(parameters[3]);
			Movement movement = movementTypes.get(index);
			return new ProximityState(e, movement, upperBound, lowerBound,
					priority);
		}

	}

}
