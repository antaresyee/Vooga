package gameObjects;

import levelLoadSave.ForSave;

@ForSave
public class Player extends GameObject {


    public Player(double x, double y, String imgPath){
        myX = x;
        myY = y;
        myImgPath = imgPath;
        myType = "Player";
        setLocation(myX, myY);
    }
    
    public String getImgPath()
    {
    	return myImgPath;
    }

    @Override
    public GameObject makeGameObject(GameObjectData god) {
        Double x = god.getX();
        Double y = god.getY();
        String imgPath = god.getImgPath();
        return new Player(x, y, imgPath);
    }

    /**
     * Player() and getFactory() must be implemented by each game object; 
     * they are used for the factory system.
     */
    protected Player() {
        myType = "Player";
    }
    
    public static GameObjectFactory getFactory() {
        return new GameObjectFactory(new Player());
    }
}