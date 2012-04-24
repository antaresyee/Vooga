package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class FollowDecorator extends MovementDecorator{

	public FollowDecorator(SpaceShip decoratedSpaceShip) {
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}

	public FollowDecorator(SpaceShip decoratedSpaceShip, Sprite t) {
		super(decoratedSpaceShip, t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}
	
	public void action(Game g, Sprite t){
		decoratedSpaceShip.action(g, t);
		
		
	}
	
}
