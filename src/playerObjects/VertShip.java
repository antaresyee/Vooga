package playerObjects;

import com.golden.gamedev.Game;

public class VertShip extends Ship{

	protected int myVertSpeed; 	
	
	public VertShip(double x, double y, String imgPath) {
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
}
