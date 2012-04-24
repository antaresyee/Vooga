package movement;

import java.awt.Point;

import game.PlayerInfo;
import gameObjects.GameObject;

public class TargetedDiamondMovement extends Movement {

	private PlayerInfo playerInfo;
	private int myRadius;
	private Point currentPoint;
	private int currentTarget;
	private final int right = 0;
	private final int up = 1;
	private final int left = 2;
	private final int down = 3;

	public TargetedDiamondMovement() {
		playerInfo = new PlayerInfo();
		myRadius = 75;
		currentPoint = new Point((int) playerInfo.getPlayerX() + myRadius,
				(int) playerInfo.getPlayerY());
		mySpeed = .2;
	}

	@Override
	public void move(GameObject o) {
		double myY = o.getY();
		double myX = o.getX();
		updateCurrentPoint();
		
		int targetX = currentPoint.x;
		int targetY = currentPoint.y;
		movementDynamics(o, myY, myX, targetX, targetY);
	}

	private void movementDynamics(GameObject o, double myY, double myX,
			int targetX, int targetY) {
		if (myY < targetY) {
			o.setVerticalSpeed(mySpeed);
		}
		if (myY > targetY) {
			o.setVerticalSpeed(-mySpeed);
		}
		if (myX < targetX) {
			o.setHorizontalSpeed(mySpeed);
		}
		if (myX > targetX) {
			o.setHorizontalSpeed(-mySpeed);
		}
		if (myX > targetX - 5 && myX < targetX + 5) {
			o.setLocation(targetX, myY);
			o.setHorizontalSpeed(0);
		}
		if (myY > targetY - 5 && myY < targetY + 5) {
			o.setLocation(myX, targetY);
			o.setVerticalSpeed(0);
		}
		if (myX > targetX - 5 && myX < targetX + 5 && myY > targetY - 5
				&& myY < targetY + 5) {
			currentTarget++;
			if (currentTarget == 4) {
				currentTarget = right;
			}
		}
		compensateSpeed(o);
	}

	private void updateCurrentPoint() {
		if (currentTarget == right) {
			currentPoint.move((int) playerInfo.getPlayerX() + myRadius,
					(int) playerInfo.getPlayerY());
		}
		if (currentTarget == up) {
			currentPoint.move((int) playerInfo.getPlayerX(),
					(int) playerInfo.getPlayerY() + myRadius);
		}
		if (currentTarget == left) {
			currentPoint.move((int) playerInfo.getPlayerX() - myRadius,
					(int) playerInfo.getPlayerY());
		}
		if (currentTarget == down) {
			currentPoint.move((int) playerInfo.getPlayerX(),
					(int) playerInfo.getPlayerY() - myRadius);
		}
	}

	private void compensateSpeed(GameObject o) {
		boolean upwardMovement = playerInfo.getUpwardMovement();
		boolean downwardMovement = playerInfo.getDownwardMovement();
		boolean leftwardMovement = playerInfo.getLeftwardMovement();
		boolean rightwardMovement = playerInfo.getRightwardMovement();
		if (upwardMovement){
			o.moveY(-2.5);
		}
		if (downwardMovement){
			o.moveY(2.5);
		}
		if (leftwardMovement){
			o.moveX(-2.5);
		}
		if (rightwardMovement){
			o.moveX(2.5);
		}

	}

	public static class TDMovementFactory extends MovementFactory {

		public TDMovementFactory() {
			myName = "TD";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			return new TargetedDiamondMovement();
		}

	}
}
