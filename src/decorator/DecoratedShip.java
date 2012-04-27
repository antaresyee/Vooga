package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public interface DecoratedShip {

	public void move(Sprite t); 
	public String getDescription(); 
	
}
