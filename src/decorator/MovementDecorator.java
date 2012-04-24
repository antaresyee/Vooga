package decorator;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class MovementDecorator implements SpaceShip {

	protected SpaceShip decoratedSpaceShip;

	public MovementDecorator(SpaceShip decoratedSpaceShip) {
		this.decoratedSpaceShip = decoratedSpaceShip;
	}

	public MovementDecorator(SpaceShip decoratedSpaceShip, Sprite t) {
		this.decoratedSpaceShip = decoratedSpaceShip;
	}

	@Override
	public void action(Game g, Sprite t) {
		decoratedSpaceShip.action(g, t);
	}

	@Override
	public String getDescription() {
		return "Movement Decorator";
	}

}
