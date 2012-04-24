package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class VerticalDecorator extends MovementDecorator{

	protected int myVertSpeed; 	

	public VerticalDecorator (SpaceShip decoratedSpaceShip){
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myVertSpeed = 3; 
	}
	

	public VerticalDecorator(SpaceShip decoratedSpaceShip, Sprite t){
		super(decoratedSpaceShip,t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myVertSpeed = 3; 
	}
	
	@Override
	public void move(Game g, Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.move(g, t); 
		
		if (g.keyDown(java.awt.event.KeyEvent.VK_W)){
			t.moveY(-myVertSpeed); 
		}
	
		if (g.keyDown(java.awt.event.KeyEvent.VK_S)){
			t.moveY(myVertSpeed); 
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "VerticalDecorator"; 
	}

}

