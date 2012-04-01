package gameObjects;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public abstract class GameObject extends Sprite {

    protected BufferedImage myImage;
    protected double myX;
    protected double myY;


    public abstract void makeObj(double x, double y, BufferedImage image);


}