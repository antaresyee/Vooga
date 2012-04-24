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
	public void move(Game g, Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.move(g, t); 
	}
	
	
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();	
		//return new MovementDecorator(x, y, imgPath);
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Movement Decorator";
	}


}
