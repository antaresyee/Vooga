package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class FollowDecorator extends MovementDecorator{

	public FollowDecorator(DecoratedShip decoratedSpaceShip) {
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}

	public FollowDecorator(DecoratedShip decoratedSpaceShip, Sprite t) {
		super(decoratedSpaceShip, t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		// TODO Auto-generated constructor stub
	}
	
	public void move(Sprite t){
		decoratedSpaceShip.move(t);
		
		
	}
	
}
