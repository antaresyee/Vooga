package playerObjects;

import java.util.ArrayList;

import weapons.Weapon;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import gameObjects.Player;

public class HozShip extends Ship{

	protected int myHozSpeed; 	
	
	protected ArrayList<Weapon> myWeapons; 
	
	public HozShip(double x, double y, String imgPath) {
		super(x, y, imgPath);
		/*
		 * Sets the default speed and health
		 * users can later use the set methods to alter
		 * speed and health
		 */
		
		myHozSpeed = 3; 
		
		// default ship will be set with generic AmmoGun
		myWeapons = new ArrayList<Weapon>();
		
	}
	
	public void move(Game g, int width){
		
		if (this.getX() > 0 && g.keyDown(java.awt.event.KeyEvent.VK_J)){
			this.moveX(-myHozSpeed); 
		}
	
		if (this.getX() < width - this.getWidth()  && g.keyDown(java.awt.event.KeyEvent.VK_L)){
			this.moveX(myHozSpeed); 
		}
	}
	
	public void setHozSpeed(int i){
		myHozSpeed = i; 
	}
	

}
