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
	int mySpeed;
	SpriteGroup myGroup;
	
	
	
	Projectile(int speed, String imgPath, SpriteGroup g){
		mySpeed = speed;
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
		this.moveY(mySpeed);
	}
	
	public void setPosition(double x, double y){
		myX = x;
		myY = y;
		setLocation(myX,myY);
	}
	
	public abstract void createProjectile(double x, double y);
	
	public abstract void actionOnCollision(GameObject hit);
	
	public void removeProjectile(){
		this.myGroup.remove(this);
	}
	

}
