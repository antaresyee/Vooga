package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class ConstantlyMoveDecorator extends MovementDecorator{

	public ConstantlyMoveDecorator(DecoratedShip decoratedSpaceShip) {
		super(decoratedSpaceShip);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(Sprite t) {
		decoratedSpaceShip.move(t);
		t.moveY(-2); 
		
	}

	@Override
	public String getDescription() {
		return "Constantly Move Decorator";
	}

}
