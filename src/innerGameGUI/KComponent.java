package innerGameGUI;

import java.awt.Color;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
/**
 * Superclass of all KComponents such as KButton, KPanel etc.
 * Functioning method is getWidth() and getHeight(), so all subclass can organize their location if desired
 * 
 * @author Kaitlyn
 *
 */
public class KComponent extends Sprite{
	protected Game myGame;
	protected KComponent myParent;
	private int myWidth, myHeight;
	protected Color myColor;
	
	public KComponent(KComponent parent, Game game)
	{
		setParent(parent);
		myGame = game;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public void setColor(Color color)
	{
		myColor = color;
	}
	
	public void setWidthHeight(int width, int height){
		myWidth = width;
		myHeight = height;
		if(getImage() != null && (getWidth() + getHeight()) > 0) setImage(ImageUtil.resize(getImage(), getWidth(), getHeight()));
	}
	
	public void setParent(KComponent parent){
		myParent = parent;
	}
	
	@Override
	public int getWidth()
	{
		return myWidth;
	}
	
	@Override
	public int getHeight()
	{
		return myHeight;
	}
}
