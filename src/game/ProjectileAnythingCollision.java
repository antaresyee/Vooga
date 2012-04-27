package game;

import gameObjects.GameObject;
import weapons.Projectile;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class ProjectileAnythingCollision extends CollisionGroup{

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		((Projectile) arg0).actionOnCollision((GameObject)arg1);	
	}

}
