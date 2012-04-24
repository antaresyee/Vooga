package bars;

import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.font.SystemFont;

import gameObjects.Ship;

public abstract class Bar {

	String myType; 
	
	Font myFont = new Font("test", Font.BOLD, 15);
	SystemFont myF = new SystemFont(myFont);
	
	public Bar(Ship s){
		myType = s.getMyUtility(); 
	}
	
	public void render(Graphics2D pen){
		myF.drawString(pen, myType+": ", 330, 30);
	}
	
	
	
	
}
