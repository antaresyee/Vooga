package gameObjects;
import java.awt.image.BufferedImage;

public abstract class GameObjectFactory {

    protected BufferedImage myImage;
    protected String path;
    protected double myX;
    protected double myY;
    protected String myName;
    protected int w, h;

    public abstract GameObject makeObject();

    public boolean isMyObject(String name) {
        return myName.equals(name);
    }
    
    public void setWidthHegith(int w, int h)
    {
    	this.w=w;
    	this.h=h;
    }
    
    public int getWidth()
    {
    	return w;
    }
    
    public int getHeight()
    {
    	return h;
    }
    
    public abstract void setFactory(double x, double y, String path, BufferedImage image);

    public String getMyName() {
        return myName;
    }
    
    public String toString() {
        return myName + " " + myX + " " + myY;
    }

}