package gameObjects;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public abstract class GameObject extends Sprite {

	protected String myName;
	protected String pathToImage;
    protected BufferedImage myImage;
    protected double myX;
    protected double myY;

    public GameObject(){}

    public abstract void makeObj(double x, double y, String path, BufferedImage image);
    public abstract String getPath();
    public abstract void setImage(BufferedImage image);

}