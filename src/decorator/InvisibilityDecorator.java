package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class InvisibilityDecorator extends PowerUpDecorator{

	public InvisibilityDecorator(SpaceShip decoratedSpaceShip, Sprite t) {
		super(decoratedSpaceShip, t);
		// TODO Auto-generated constructor stub
	
	}
	
	
	@Override
	public void powerUp(Game g, Sprite t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Invisibility Decorator";
	}

}
