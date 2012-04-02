package gameObjects;
import java.awt.image.BufferedImage;

public abstract class GameObjectFactory {

    protected String path;
    protected double myX;
    protected double myY;
    protected String myName;

    public abstract GameObject makeObject();

    public boolean isMyObject(String name) {
        return myName.equals(name);
    }
 
    public abstract void setFactory(double x, double y, String path);

    public String getMyName() {
        return myName;
    }
    
    public String toString() {
        return myName + " " + myX + " " + myY;
    }

}