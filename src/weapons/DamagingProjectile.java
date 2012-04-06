package weapons;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.Enemy;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class DamagingProjectile extends Projectile{
	int myDamage;

	DamagingProjectile(int speed, String imgPath, SpriteGroup g, int damage) {
		super(speed, imgPath, g);
		myDamage = damage;
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		if(hit.getType().equals("Enemy")){
			Enemy enemy = (Enemy) hit;
			enemy.sustainDamage(myDamage);
		}
		
	}

	@Override
	public void createProjectile(double x, double y) {
		DamagingProjectile newproj = new DamagingProjectile(mySpeed, myImgPath, myGroup, myDamage);
		newproj.setPosition(x, y);
		myGroup.add(newproj);
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		String path = god.getImgPath();
		Projectile returning = new DamagingProjectile(mySpeed, path, myGroup, myDamage);
		double x = god.getX();
		double y = god.getY();
		returning.setPosition(x, y);
		return returning;
	}

}
