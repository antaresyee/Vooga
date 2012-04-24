package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class HorizontalDecorator extends MovementDecorator{

	protected int myHozSpeed; 	

	public HorizontalDecorator (SpaceShip decoratedSpaceShip){
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myHozSpeed = 3; 
	}
	

	public HorizontalDecorator(SpaceShip decoratedSpaceShip, Sprite t){
		super(decoratedSpaceShip,t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myHozSpeed = 3; 
	}
	
	public void action(Game g, Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.action(g, t);
		if (g.keyDown(java.awt.event.KeyEvent.VK_A)){ 
			t.moveX(-myHozSpeed); 
		}
	
		if (g.keyDown(java.awt.event.KeyEvent.VK_D)){
			t.moveX(myHozSpeed); 
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "HorizontalDecorator"; 
	}

}
