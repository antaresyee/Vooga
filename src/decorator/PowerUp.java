package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public interface PowerUp {
	
	public void powerUp(Game g, Sprite t); 
	public String getDescription(); 

}
