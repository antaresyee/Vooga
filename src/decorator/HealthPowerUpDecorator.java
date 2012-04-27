package decorator;

import com.golden.gamedev.object.Sprite;

public class HealthPowerUpDecorator extends PowerUpDecorator{

	public HealthPowerUpDecorator(PowerUp decoratedPowerUp) {
		super(decoratedPowerUp);
		// TODO Auto-generated constructor stub
	}

	public HealthPowerUpDecorator(PowerUp decoratedPowerUp, Sprite t) {
		super(decoratedPowerUp, t);
		// TODO Auto-generated constructor stub
	}

}
