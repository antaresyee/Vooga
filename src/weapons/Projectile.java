package weapons;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

/** does not create functional Projectile class.
 * instead creates dummy class, to create usable sprite, call createProjectile function (first call setSpriteGroup).
 * 
 * @author ntc2
 *
 */


public abstract class Projectile extends GameObject{
	
	SpriteGroup myGroup;
	double myXSpeed;
	double myYSpeed;
	
	
	Projectile( String imgPath, SpriteGroup g){
		
		myImgPath = imgPath;
		myGroup = g;
		myType = "Projectile";
		
	}
	
	

	@Override
	public String getImgPath() {
		return myImgPath;
	}
	
	@Override
	public void update(long elapsedTime){
		this.moveY(myYSpeed);
		this.moveX(myXSpeed);
	}
	
	public void setPosition(double x, double y){
		myX = x;
		myY = y;
		setLocation(myX,myY);
	}
	
	public void setSpeed(double xspeed, double yspeed){
		myXSpeed = xspeed;
		myYSpeed = yspeed;
	}
	
	public abstract void createProjectile(double xpos, double ypos, double xspeed, double yspeed);
	
	public abstract void actionOnCollision(GameObject hit);
	
	public void removeProjectile(){
		this.myGroup.remove(this);
	}
	

}
