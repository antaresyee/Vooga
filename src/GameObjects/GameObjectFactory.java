package GameObjects;
import java.awt.image.BufferedImage;


public abstract class GameObjectFactory {

	protected BufferedImage myImage;
	protected double myX;
	protected double myY;

	public abstract GameObject makeObject();

}
