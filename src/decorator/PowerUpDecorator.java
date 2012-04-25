package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class PowerUpDecorator implements PowerUp{
	
	protected DecoratedShip decoratedSpaceShip;
	
	public PowerUpDecorator (DecoratedShip decoratedSpaceShip){
		this.decoratedSpaceShip = decoratedSpaceShip;
	}
	
	public PowerUpDecorator(DecoratedShip decoratedSpaceShip, Sprite t){
		this.decoratedSpaceShip = decoratedSpaceShip;
	}
	

	@Override
	public void powerUp(Game g, Sprite t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "PowerUp Decorator";
	}


}
