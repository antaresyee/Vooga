package gameObjects;

import com.golden.gamedev.Game;

public class VerticalShip extends HorizontalShip{

	protected int myVertSpeed; 	
	
	public VerticalShip(double x, double y, String imgPath) {
		super(x, y, imgPath);
		// TODO Auto-generated constructor stub
	}

	public void setVertSpeed(int s) {
		myVertSpeed = s;
	}
	
	public void moveVert(Game g){
		if (g.keyDown(java.awt.event.KeyEvent.VK_W)){
			this.moveY(-myHozSpeed); 
		} 
		
		if (g.keyDown(java.awt.event.KeyEvent.VK_S)){
			this.moveY(myHozSpeed); 
		} 
	}	
	
	private VerticalShip() {
		super(); 
		myType = "HorizontalShip";
	}

	public static GameObjectFactory getFactory() {
		return new GameObjectFactory(new VerticalShip());
	}
}
