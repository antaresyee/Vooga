package decorator;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.Player;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class MovementDecorator implements SpaceShip{

	protected SpaceShip decoratedSpaceShip;
	
	public MovementDecorator (SpaceShip decoratedSpaceShip){
		this.decoratedSpaceShip = decoratedSpaceShip;
	}
	
	public MovementDecorator(SpaceShip decoratedSpaceShip, Sprite t){
		this.decoratedSpaceShip = decoratedSpaceShip;
	}
	
	@Override
	public void action(Game g, Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.action(g, t); 
	}
	
	

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Movement Decorator";
	}


}
