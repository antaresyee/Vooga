package decorator;

import gameObjects.Player;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class CompanionDecorator extends PowerUpDecorator{

	Player comp;
	public boolean hasBeenCreated = false; 
	
	public CompanionDecorator(PowerUp decoratedPowerUp) {
		super(decoratedPowerUp);
		this.decoratedPowerUp = decoratedPowerUp;
		// TODO Auto-generated constructor stub
	}
	
	public CompanionDecorator(PowerUp decoratedPowerUp, Sprite t) {
		super(decoratedPowerUp, t);
		this.decoratedPowerUp = decoratedPowerUp;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void powerUp(Game g, Sprite t, Object o) {
		// TODO Auto-generated method stub
//		comp = new Player(t.getX()-10, t.getY() -15, "resources/smallShip.png",((Player) t).getDecStrings()); 
//		comp.setImage(g.getImage("resources/smallShip.png"));
		
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "PowerUp Decorator";
	}
	
	
	public Player getCompanion(){
		return comp; 
	}
	
	public boolean beenCreated(){
		return hasBeenCreated; 
	}
	
	public void setCreated(){
		hasBeenCreated = true;
	}

}
