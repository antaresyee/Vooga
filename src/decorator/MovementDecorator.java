package decorator;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class MovementDecorator implements DecoratedShip {

	protected DecoratedShip decoratedSpaceShip;

	public MovementDecorator(DecoratedShip decoratedSpaceShip) {
		this.decoratedSpaceShip = decoratedSpaceShip;
	}

	public MovementDecorator(DecoratedShip decoratedSpaceShip, Sprite t) {
		this.decoratedSpaceShip = decoratedSpaceShip;
	}

	@Override
	public void move(Sprite t) {
		decoratedSpaceShip.move(t);
		
	}

	@Override
	public String getDescription() {
		return "Movement Decorator";
	}

}
