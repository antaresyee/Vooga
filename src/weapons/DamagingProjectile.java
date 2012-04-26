package weapons;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.Enemy;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class DamagingProjectile extends Projectile{
	int myDamage;

	public DamagingProjectile(String imgPath, SpriteGroup g, int damage) {
		super(imgPath, g);
		myDamage = damage;
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		if(hit.getType().equals("Enemy")){
			Enemy enemy = (Enemy) hit;
			enemy.sustainDamage(myDamage);
		}
		removeProjectile();
	}

	@Override
	public void createProjectile(double x, double y, double xspeed, double yspeed) {
		DamagingProjectile newproj = new DamagingProjectile(myImgPath, myGroup, myDamage);
		newproj.setBackground(myGroup.getBackground());
		newproj.setLocation(x, y);
		newproj.setSpeed(xspeed, yspeed);
		newproj.setImage(this.getImage());
		myGroup.add(newproj);
		//System.out.println("here1");
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		String path = god.getImgPath();
		Projectile returning = new DamagingProjectile(path, myGroup, myDamage);
		double x = god.getX();
		double y = god.getY();
		returning.setLocation(x, y);
		return returning;
	}

}
