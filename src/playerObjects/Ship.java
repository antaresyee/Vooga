package playerObjects;

import java.util.ArrayList;

import com.golden.gamedev.Game;

import weapons.Weapon;
import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

public class Ship extends Player {

	protected int myHealth; 
	protected ArrayList<Weapon> myWeapons; 
	protected String myUtility; 
	
	public Ship(double x, double y, String imgPath) {
		super(x, y, imgPath);
		
		// automatically sets health
		myHealth = 10; 
		myUtility = "health"; 
	}
	
	public void setHealth(int h){
		myHealth = h; 
	}
	
	public void addWeapon(Weapon w){
		myWeapons.add(w); 
	}
	
	public void fire(Game g, long elapsedTime){
		if(g.keyDown(java.awt.event.KeyEvent.VK_SPACE)){
			myWeapons.get(0).fire(elapsedTime, this.myX, this.myY); 
		}
	}
	
	public String getMyUtility(){
		return myUtility; 
	}
	
	
	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();	
		String filename = god.getEnemyConfigFile();
		return new Enemy(x, y, imgPath, filename);

	}

	/**
	 * Ship() and getFactory() must be implemented by each game object; they
	 * are used for the factory system.
	 */
	protected Ship() {
		super(); 
		myType = "Ship";
	}

	public void move(Game g, int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
