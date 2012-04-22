package playerObjects;

import java.util.ArrayList;

import com.golden.gamedev.Game;

import weapons.Weapon;
import gameObjects.Player;

public abstract class Ship extends Player{

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
	
	
	public abstract void move(Game g, int width, int height); 
}
