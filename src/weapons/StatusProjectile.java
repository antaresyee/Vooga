package weapons;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;

import com.golden.gamedev.object.SpriteGroup;

public class StatusProjectile extends Projectile{
	Status myStatus;

	StatusProjectile(String imgPath, SpriteGroup g, Status s) {
		super(imgPath, g);
		myStatus = s;
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		if(hit.getType().equals("Enemy")){
			Enemy enemy = (Enemy) hit;
			enemy.addStatus(myStatus);
		}
		removeProjectile();
		
	}

	@Override
	public void createProjectile(double xpos, double ypos, double xspeed,
			double yspeed) {
		StatusProjectile newproj = new StatusProjectile(myImgPath, myGroup, myStatus);
		newproj.setLocation(xpos, ypos);
		newproj.setSpeed(xspeed, yspeed);
		newproj.setImage(this.getImage());
		myGroup.add(newproj);
		
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		String path = god.getImgPath();
		Projectile returning = new StatusProjectile(path, myGroup, myStatus);
		double x = god.getX();
		double y = god.getY();
		returning.setLocation(x, y);
		return returning;
	}

}
