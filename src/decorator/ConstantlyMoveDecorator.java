package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class ConstantlyMoveDecorator extends MovementDecorator{

	public ConstantlyMoveDecorator(DecoratedShip decoratedSpaceShip) {
		super(decoratedSpaceShip);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(Game g, Sprite t) {
		decoratedSpaceShip.move(g, t);
		t.moveY(-2); 
		if (g.keyPressed(java.awt.event.KeyEvent.VK_J)){
			t.moveY(200); 
		}
	}

	@Override
	public String getDescription() {
		return "Constantly Move Decorator";
	}

}
