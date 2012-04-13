package playerObjects;

import java.util.ArrayList;

import weapons.AmmoGun;
import weapons.Weapon;

import com.golden.gamedev.Game;

import gameObjects.Player;

public class Ship extends Player{

	protected int myHozSpeed; 	
	protected int myHealth; 
	protected ArrayList<Weapon> myWeapons; 
	
	public Ship(double x, double y, String imgPath) {
		super(x, y, imgPath);
		/*
		 * Sets the default speed and health
		 * users can later use the set methods to alter
		 * speed and health
		 */
		
		myHozSpeed = 3; 
		myHealth = 10; 
		
		// default ship will be set with generic AmmoGun
		myWeapons = new ArrayList<Weapon>();
		//myWeapons.add(new AmmoGun(myHealth, null, null)); 
		
	}

	public void setHozSpeed(int s) {
		myHozSpeed = s;
	}
	
	public void setHealth(int h){
		myHealth = h; 
	}
	
	public void move(Game g, int width){
		
		if (this.getX() > 0 && g.keyDown(java.awt.event.KeyEvent.VK_J)){
			this.moveX(-myHozSpeed); 
		}
	
		if (this.getX() < width - this.getWidth() && g.keyDown(java.awt.event.KeyEvent.VK_L)){
			this.moveX(myHozSpeed); 
		}
	}
	
	
	public void addWeapon(Weapon w){
		myWeapons.add(w); 
	}
	
//	public void fire(Game g){
//		if(g.keyDown(java.awt.event.KeyEvent.VK_SPACE)){
//			myWeapons.get(0).fire(arg0, xpos, ypos); 
//		}
//	}
}
