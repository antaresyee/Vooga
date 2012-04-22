package gameObjects.boss;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class FireBossCollision extends BasicCollisionGroup{

	public FireBossCollision()
	{
//		pixelPerfectCollision = true;
	}
	@Override
	public void collided(Sprite s1, Sprite s2) {
		// TODO Auto-generated method stub
//		System.out.println(s1);
		((BossWeakPoint)s1).hit();
		s2.setActive(false);
	}
}
