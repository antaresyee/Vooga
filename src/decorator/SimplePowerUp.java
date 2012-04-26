package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class SimplePowerUp implements PowerUp{


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "this guy does nothing!";
	}

	@Override
	public void powerUp(Game g, Sprite t, Object o) {
		// TODO Auto-generated method stub
		
	}

}
