package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class HorizontalDecorator extends MovementDecorator{

	protected int myHozSpeed; 	
	
	public HorizontalDecorator()
	{
		super(null);
		myHozSpeed = 0;
	}

	public HorizontalDecorator (DecoratedShip decoratedSpaceShip){
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myHozSpeed = 3; 
	}
	

	public HorizontalDecorator(DecoratedShip decoratedSpaceShip, Sprite t){
		super(decoratedSpaceShip,t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myHozSpeed = 3; 
	}
	
	public void move(Game g, Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.move(g, t);
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
