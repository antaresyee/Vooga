package weapons;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import com.golden.gamedev.object.SpriteGroup;

public class CreationProjectile extends Projectile{
	GameObject myCreated;
	SpriteGroup myCreatedSpriteGroup;

	CreationProjectile( String imgPath, SpriteGroup g, GameObject created, SpriteGroup sg) {
		super(imgPath, g);
		myCreated = created;
		myCreatedSpriteGroup = sg;
		
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		myCreated.setLocation(hit.getX(), hit.getY());
		myCreatedSpriteGroup.add(myCreated);
		removeProjectile();
		
	}

	@Override
	public void createProjectile(double xpos, double ypos, double xspeed, double yspeed) {
		CreationProjectile createdProjectile = new CreationProjectile(myImgPath, myGroup, myCreated, myCreatedSpriteGroup);
		createdProjectile.setPosition(xpos,ypos);
		createdProjectile.setSpeed(xspeed, yspeed);
		myGroup.add(createdProjectile);
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		String path = god.getImgPath();
		Projectile returning = new CreationProjectile(path, myGroup, myCreated, myCreatedSpriteGroup);
		double x = god.getX();
		double y = god.getY();
		returning.setPosition(x, y);
		return returning;
	}

}
