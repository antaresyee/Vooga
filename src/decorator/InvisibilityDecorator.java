package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class InvisibilityDecorator extends PowerUpDecorator{

	SpriteGroup toAlter; 
	public InvisibilityDecorator(PowerUp decoratedPowerUp, Sprite t) {
		super(decoratedPowerUp, t);
		// TODO Auto-generated constructor stub
	
	}
	
	
	@Override
	public void powerUp(Game g, Sprite t, Object o) {
		// TODO Auto-generated method stub
		((SpriteGroup) o).remove(t); 
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Invisibility Decorator";
	}

}
