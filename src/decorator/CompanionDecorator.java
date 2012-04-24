package decorator;

import gameObjects.Ship;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class CompanionDecorator extends PowerUpDecorator{

	Ship comp;
	public boolean hasBeenCreated = false; 
	
	public CompanionDecorator(SpaceShip decoratedSpaceShip) {
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}
	
	public CompanionDecorator(SpaceShip decoratedSpaceShip, Sprite t) {
		super(decoratedSpaceShip, t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void powerUp(Game g, Sprite t) {
		// TODO Auto-generated method stub
		comp = new Ship(t.getX()-5, t.getY() -5,"resources/smallShip.png"); 
		comp.setImage(g.getImage("resources/smallShip.png"));
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "PowerUp Decorator";
	}
	
	
	public Ship getCompanion(){
		return comp; 
	}
	
	public boolean beenCreated(){
		return hasBeenCreated; 
	}
	
	public void setCreated(){
		hasBeenCreated = true;
	}

}
