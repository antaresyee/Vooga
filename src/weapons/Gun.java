package weapons;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Gun extends Weapon{
	Projectile myProjectile;
	SpriteGroup myProjectileGroup;
	ShotPattern myPattern;

	public Gun(int rate, Projectile p, SpriteGroup g, ShotPattern pattern) {
		super(rate);
		myProjectile = p;
		myProjectileGroup = g;
		myPattern = pattern;
		
	}
}
