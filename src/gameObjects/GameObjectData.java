package gameObjects;

import java.util.TreeMap;

/**
 * 
 * @author antaresyee
 *
 */

public class GameObjectData {
    
    private TreeMap<String, String> myData;
    
    public GameObjectData() {
        myData = new TreeMap<String, String>();
    }
    
    
    //SETTERS
    public void setType(String type) {
        myData.put("type", type);
    }
    
    public void setX(String x) {
        myData.put("x", x);
    }
    
    public void setY(String y) {
        myData.put("y", y);
    }
    
    public void setImgPath(String imgPath) {
        myData.put("imgPath", imgPath);
    }
    
    public void setMovement(String movement) {
        myData.put("movement", movement);
    }
    
    
    //GETTERS
    public String getType() {
        return myData.get("type");
    }
    
    public double getX() {
        return new Double(myData.get("x"));
    }
    
    public double getY() {
        return new Double(myData.get("y"));
    }
    
    public String getImgPath() {
        return myData.get("imgPath");
    }
    
    public Movement getMovement() {
        return null;
    }
    
    //ASK HEWNER:
    //problem 1: makeGameObject() must be abstract static in GameObject, which you can't have....
    //alternative is instantiate a GameObject first (like Barrier), but then this is just like having 
    //a factory...ALSO, you will need factory for isMyType() anyhow when deciding what type of object the
    //GameObjectData is.
    //If you keep makeGameObject() in GameObjectData, you will need a different makeGameObject for each
    //subclass.  OR: you can create a broken version of GameObject, then call brokenGameObject.makeGameObject()
    //problem 2: each GameObject must still have isMyType() and makeObject(), 
    //which is still parallel hierarchy.  
    //Problem 3: you will have a million getters/setters, which is also a problem--cant create new 
    //setter methods to fix new fields being added.

    
    
    
    //Problem 4: What if GUI doesn't store right fields in GameObjectData--there will be weird bugs trying to 
    //re-instantiate GameObject from GameObjectData
    
    //HOW IT WORKS
    //GUI makes GameObjectData instance, sets type, x, y, movement, imgPath. GUI calls save(List<GameObjectData>)
    //to load, call LevelLoader.load(.json), call makeGameObject() on each GameObjectData.
   
    
    
}
