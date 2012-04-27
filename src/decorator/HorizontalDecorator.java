package decorator;

import game.PlayerInfo;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class HorizontalDecorator extends MovementDecorator{

	protected int myHozSpeed; 	
	private PlayerInfo playerInfo;

	
	public HorizontalDecorator()
	{
		super(null);
		playerInfo = new PlayerInfo();
		myHozSpeed = 0;
	}

	public HorizontalDecorator (DecoratedShip decoratedSpaceShip){
		super(decoratedSpaceShip);
		this.decoratedSpaceShip = decoratedSpaceShip;
		playerInfo = new PlayerInfo();

		myHozSpeed = 3; 
	}
	

	public HorizontalDecorator(DecoratedShip decoratedSpaceShip, Sprite t){
		super(decoratedSpaceShip,t);
		this.decoratedSpaceShip = decoratedSpaceShip;
		myHozSpeed = 3; 
	}
	
	public void move(Sprite t) {
		// TODO Auto-generated method stub
		decoratedSpaceShip.move(t);
		if (playerInfo.getLeftwardMovement()){ 
			t.moveX(-myHozSpeed); 
		}
	
		if (playerInfo.getRightwardMovement()){
			t.moveX(myHozSpeed); 
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "HorizontalDecorator"; 
	}

}
