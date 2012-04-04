package gameObjects;



public class Barrier extends GameObject{
	
    public Barrier(double x, double y, String imgPath){
        myX = x;
        myY = y;
        myImgPath = imgPath;
        myType = "Barrier";
        setLocation(myX,myY);
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
        return new Barrier(x, y, imgPath);
    }
    
    /**
     * Barrier() and getFactory() must be implemented by each game object; 
     * they are used for the factory system.
     */
    private Barrier() {}
    
    public static GameObjectFactory getFactory() {
        return new GameObjectFactory(new Barrier());
    }
    
}