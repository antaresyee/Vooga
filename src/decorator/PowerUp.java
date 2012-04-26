package decorator;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public interface PowerUp {
	
	public void powerUp(Game g, Sprite t, Object o); 
	public String getDescription(); 

}
