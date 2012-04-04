package gameObjects;

import java.util.TreeMap;

import movement.Movement;

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
    
}
