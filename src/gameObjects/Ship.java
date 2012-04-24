package gameObjects;

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
	
	public Ship(double x, double y, String imgPath) {
		super(x, y, imgPath);
		myHealth = 10; 
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
			myWeapons.get(0).fire(elapsedTime, this.myX, this.myY); 
		}
	}
	
	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();	
		return new Ship(x, y, imgPath);

	}

	/**
	 * Ship() and getFactory() must be implemented by each game object; they
	 * are used for the factory system.
	 */
	protected Ship() {
		super(); 
		myType = "Ship";
	}

}
