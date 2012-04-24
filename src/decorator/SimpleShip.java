package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class SimpleShip implements SpaceShip{

	@Override
	public void action(Game g, Sprite t) {
		// TODO Auto-generated method stub
		// base case
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "this guy does nothing!";
	}

}
