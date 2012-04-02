package gameObjects;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public abstract class GameObject extends Sprite {

	protected String myName;
	protected String pathToImage;
    protected BufferedImage myImage;
    protected double myX;
    protected double myY;
    protected int width, height;
    
    public GameObject(){}

    public abstract void makeObj(double x, double y, String path, BufferedImage image);
    public abstract String getPath();
    public void setWidthHeight(int w, int h)
    {
    	width=w;
    	height=h;
    }
//    public abstract void setImage(BufferedImage image);

}