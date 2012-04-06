package weapons;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Gun extends Weapon{
	Projectile myProjectile;
	SpriteGroup myProjectileGroup;

	public Gun(int rate, Projectile p, SpriteGroup g) {
		super(rate);
		myProjectile = p;
		myProjectileGroup = g;
		
	}
}
