package movement;

import java.awt.Point;
import java.util.ArrayList;

import gameObjects.GameObject;

public class DiamondMovement extends Movement {
	
	private PathMovement myPathMovement;
	private Point myTarget;

	public DiamondMovement(double x, double y){
		myTarget = new Point((int) x, (int) y);
		myPathMovement = new PathMovement(calculatePath());
	}
	
	@Override
	public void move(GameObject o) {
		myPathMovement.move(o);
	}
	
	public ArrayList<Point> calculatePath(){
		ArrayList<Point> path = new ArrayList<Point>();
		Point right = new Point(myTarget.x + 50, myTarget.y);
		Point up = new Point(myTarget.x, myTarget.y + 50);
		Point left = new Point(myTarget.x - 50, myTarget.y);
		Point down = new Point(myTarget.x, myTarget.y - 50);
		path.add(right);
		path.add(up);
		path.add(left);
		path.add(down);
		return path;
	}
	
	public static class DiamondMovementFactory extends MovementFactory{
		
		public DiamondMovementFactory(){
			myName = "D";
		}

		@Override
		public Movement makeMyMovement(String[] parameters) {
			double x = Double.parseDouble(parameters[1]);
			double y = Double.parseDouble(parameters[2]);
			return new DiamondMovement(x,y);
		}
		
	}

}
