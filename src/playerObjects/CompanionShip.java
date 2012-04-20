package playerObjects;

import gameObjects.Player;

import com.golden.gamedev.Game;

public class CompanionShip extends Ship{

	SmallShip comp; 
	protected int myHozSpeed; 
	
	public CompanionShip(double x, double y, String imgPath) {
		super(x, y, imgPath);
		// TODO Auto-generated constructor stub
		
		myHozSpeed = 3; 
		comp = new SmallShip(this.myX-5, this.myY -5, "resources/smallShip.png"); 
		
	}

	@Override
	public void move(Game g, int width) {
		comp.move(g, width); 
		
		if (this.getX() > 0 && g.keyDown(java.awt.event.KeyEvent.VK_J)){
			this.moveX(-myHozSpeed); 
			comp.moveX(myHozSpeed);
		}
	
		if (this.getX() < width - this.getWidth()  && g.keyDown(java.awt.event.KeyEvent.VK_L)){
			this.moveX(myHozSpeed); 
			comp.moveX(myHozSpeed);
		}
	}
	
	public SmallShip getComp(){
		return comp; 
	}
	

	
	

}
