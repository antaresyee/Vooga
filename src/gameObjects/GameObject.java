package gameObjects;

import com.golden.gamedev.object.Sprite;

public abstract class GameObject extends Sprite {

	protected String myName;
	protected String myImgPath;
    protected double myX;
    protected double myY;
    public abstract String getImgPath();

}