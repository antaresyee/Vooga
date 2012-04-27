package gameObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import weapons.Weapon;

import movement.Movement;

/**
 * 
 * @author antaresyee
 *
 */

public class GameObjectData implements Serializable {
    
    /**
     * Used for serialization and versioning
     */
    private static final long serialVersionUID = 1L;
    
    private TreeMap<String, String> myData;
    private Movement myMovement;
    //used ONLY for enemies. testing enemy state framework
    private ArrayList<Movement> myEnemyMovements;
    private String myEnemyConfigFile;
    private Weapon myWeapon;
    
    private ArrayList<String> myDecorations; 
    
    public GameObjectData(String type) {
        myData = new TreeMap<String, String>();
        myData.put("type", type);
    }
    
    public void setDecorations(ArrayList<String> decorations){
    	myDecorations =decorations;
    }
    
    public ArrayList<String> getDecorations(){
    	return myDecorations;
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
    
    public void setWeapon(Weapon w){
        myWeapon = w;
    }
    
    public Weapon getWeapon(){
        return myWeapon;
    }
    
    public void setMovement(Movement movement) {
        myMovement = movement;
    }
    
    public void setEnemyMovements(ArrayList<Movement> movements){
    	myEnemyMovements = movements;
    }
    
    public void setEnemyConfigFile(String s){
    	myEnemyConfigFile = s;
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
        return myMovement;
    }
    
    public ArrayList<Movement> getMovements(){
    	return myEnemyMovements;
    }
    
    public String getEnemyConfigFile(){
    	return myEnemyConfigFile;
    }
    
    
    //JAVA OVERRIDES
    public String toString() {
        if (myMovement != null)
            return "\n" + myData.get("type") + " " + myData.get("imgPath") + " " + myData.get("x") + " " + myData.get("y") + " " + myMovement.toString() + "\n";
        else 
            return "\n" + myData.get("type") + " " + myData.get("imgPath") + " " + myData.get("x") + " " + myData.get("y") + " " + "\n";
    }
    
    public boolean equals(GameObjectData other) {
        if (! myData.equals(other.myData)) return false;
        return true;
    }
    
    public int hashCode() {
        return myData.hashCode();
    }
    
}
