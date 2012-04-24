package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public interface SpaceShip {

	public void action(Game g, Sprite t); 
	public String getDescription(); 
	
}
