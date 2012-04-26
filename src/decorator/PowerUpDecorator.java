package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class PowerUpDecorator implements PowerUp{
	
	protected PowerUp decoratedPowerUp;
	
	public PowerUpDecorator (PowerUp decoratedPowerUp){
		this.decoratedPowerUp = decoratedPowerUp;
	}
	
	public PowerUpDecorator(PowerUp decoratedPowerUp, Sprite t){
		this.decoratedPowerUp = decoratedPowerUp;
	}
	

	@Override
	public void powerUp(Game g, Sprite t, Object o) {
		
	}

	@Override
	public String getDescription() {
		return "PowerUp Decorator";
	}


}
