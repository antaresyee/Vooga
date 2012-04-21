package movement;

import java.awt.Point;
import java.util.ArrayList;

import gameObjects.GameObject;

/**
 * 
 * @author James Pagliuca
 * 
 */
public class PathMovement extends Movement {

	private ArrayList<Point> myPath;
	private int pointIndex;

	public PathMovement(ArrayList<Point> path) {
		myPath = path;
		pointIndex = 0;
		mySpeed = .2;
	}

	@Override
	public void move(GameObject o) {
		if (pointIndex == myPath.size()) {
			pointIndex = 0;
			reversePath();
		}
		Point currentPoint = myPath.get(pointIndex);
		moveToPoint(o, currentPoint);
	}

	public void moveToPoint(GameObject o, Point p) {
		double myY = o.getY();
		double myX = o.getX();
		int targetX = p.x;
		int targetY = p.y;
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
			o.setLocation(targetX, targetY);
			o.setVerticalSpeed(0);
		}
		if (myX > targetX - 5 && myX < targetX + 5 && myY > targetY - 5
				&& myY < targetY + 5) {
			pointIndex++;
		}

	}
	
	private void reversePath(){
		ArrayList<Point> reversedPath = new ArrayList<Point>();
		int reverseIndex = myPath.size() - 1;
		for (int i = 0; i < myPath.size(); i++){
			Point reversePoint = myPath.get(reverseIndex);
			reversedPath.add(i, reversePoint);
			reverseIndex--;
		}
		myPath = reversedPath;
	}

	public static class PathMovementFactory extends MovementFactory {

		public PathMovementFactory() {
			myName = "P";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			ArrayList<Point> path = new ArrayList<Point>();
			for (int i = 1; i < parameters.length; i += 2) {
				int x = (int) Double.parseDouble(parameters[i]);
				int y = (int) Double.parseDouble(parameters[i + 1]);
				System.out.println("X = " + x);
				System.out.println("Y = " + y);
				Point p = new Point(x, y);
				path.add(p);
			}
			return new PathMovement(path);
		}

	}
}
