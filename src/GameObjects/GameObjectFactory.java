package gameObjects;
import java.awt.image.BufferedImage;


public abstract class GameObjectFactory {

	protected BufferedImage myImage;
	protected double myX;
	protected double myY;

	public abstract GameObject makeObject();
	
	public abstract boolean isMyObject(String name);

}
