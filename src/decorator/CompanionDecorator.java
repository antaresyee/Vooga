package decorator;

import gameObjects.Player;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class CompanionDecorator extends PowerUpDecorator{

	Player comp;
	public boolean hasBeenCreated = false; 
	SpaceShip compDecorator;
	
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
		comp = new Player(t.getX()-10, t.getY() -15,"resources/smallShip.png"); 
		comp.setImage(g.getImage("resources/smallShip.png"));
		compDecorator = new HorizontalDecorator(new SimpleShip()); 
		
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
