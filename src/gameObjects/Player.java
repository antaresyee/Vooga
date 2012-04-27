package gameObjects;

import java.util.ArrayList;

import weapons.Weapon;

import com.golden.gamedev.Game;

import levelLoadSave.ForSave;

@ForSave
public class Player extends GameObject {

	
	protected int myHealth; 
	protected ArrayList<Weapon> myWeapons;

    public Player(double x, double y, String imgPath){
        myX = x;
        myY = y;
        myImgPath = imgPath;
        myType = "Player";
        setLocation(myX, myY);
        myWeapons = new ArrayList<Weapon>();
        
        myHealth = 10; 
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

    
    public void reduceHealth(int damage){
		myHealth -= damage; 
	}
	
	public int getHealth(){
		return myHealth; 
	}
	
	public void addWeapon(Weapon w){
		myWeapons.add(w); 
	}
	
	public void fire(Game g, long elapsedTime){
		if(g.keyDown(java.awt.event.KeyEvent.VK_SPACE)){
			myWeapons.get(0).fire(elapsedTime, this.getX(), this.getY()); 
		}
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