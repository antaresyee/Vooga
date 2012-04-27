package decorator;

import game.PlayerInfo;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class VerticalDecorator extends MovementDecorator{

	protected int myVertSpeed;
	private PlayerInfo playerInfo;


	public VerticalDecorator (DecoratedShip decoratedSpaceShip){
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		playerInfo = new PlayerInfo();

		myVertSpeed = 3; 
	}
	

	public VerticalDecorator(DecoratedShip decoratedSpaceShip, Sprite t){
		super(decoratedSpaceShip,t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		playerInfo = new PlayerInfo();

		myVertSpeed = 3; 
	}
	
	@Override
	public void move(Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.move(t); 
		
		if (playerInfo.getUpwardMovement()){
			t.moveY(-myVertSpeed); 
		}
	
		if (playerInfo.getDownwardMovement()){
			t.moveY(myVertSpeed); 
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "VerticalDecorator"; 
	}

}

