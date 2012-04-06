package gameObjects;

import com.golden.gamedev.object.Sprite;

public abstract class GameObject extends Sprite {

	protected String myType;
	protected String myImgPath;
    protected double myX;
    protected double myY;
    
    public abstract String getImgPath();
    public abstract GameObject makeGameObject(GameObjectData god);
    
    public boolean isMyObject(GameObjectData god) {
        return myType.equals(god.getType());
    }
    
    public String getType(){
    	return myType;
    }

}