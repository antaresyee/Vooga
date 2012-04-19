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

	public PathMovement(ArrayList<Point> p, double s) {
		myPath = p;
		mySpeed = s;
	}

	@Override
	public void move(GameObject o) {
		// TODO Auto-generated method stub

	}
	
	public void moveToPoint(GameObject o, Point p){
		
	}

	public static class PathMovementFactory extends MovementFactory {

		public PathMovementFactory() {
			myName = "P";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			return null;
		}

	}
}
