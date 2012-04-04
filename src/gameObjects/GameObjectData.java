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
    
    public GameObjectData(String type) {
        myData = new TreeMap<String, String>();
        myData.put("type", type);
    }
    
    
    //SETTERS
    public void setX(Double x) {
        myData.put("x", x.toString());
    }
    
    public void setY(Double y) {
        myData.put("y", y.toString());
    }
    
    public void setImgPath(String imgPath) {
        myData.put("imgPath", imgPath);
    }
    
    public void setMovement(Movement movement) {
        myData.put("movement", movement.toString());
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
    
    //JAVA OVERRIDES
    public String toString() {
        return myData.get("type") + " " + myData.get("imgPath") + " " + myData.get("x") + " " + myData.get("y");
    }
    
    public boolean equals(GameObjectData other) {
        if (! myData.equals(other.myData)) return false;
        return true;
    }
    
    public int hashCode() {
        return myData.hashCode();
    }
    
}
