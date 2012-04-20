package weapons;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import com.golden.gamedev.object.SpriteGroup;

public class TeleportProjectile extends Projectile{
	GameObject tobeTeleported;

	TeleportProjectile(String imgPath, SpriteGroup g, GameObject obj) {
		super(imgPath, g);
		tobeTeleported = obj;
		
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		tobeTeleported.setX(hit.getX());
		tobeTeleported.setY(hit.getY());
		
	}

	@Override
	public void createProjectile(double xpos, double ypos, double xspeed,
			double yspeed) {
		TeleportProjectile newproj = new TeleportProjectile(myImgPath, myGroup, tobeTeleported);
		newproj.setPosition(xpos, ypos);
		newproj.setSpeed(xspeed, yspeed);
		myGroup.add(newproj);
		
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		String path = god.getImgPath();
		Projectile returning = new TeleportProjectile(path, myGroup, tobeTeleported);
		double x = god.getX();
		double y = god.getY();
		returning.setPosition(x, y);
		return returning;
	}

}
