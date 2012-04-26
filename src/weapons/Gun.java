package weapons;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Gun extends Weapon{
	Projectile myProjectile;
	ShotPattern myPattern;

	public Gun(int rate, Projectile p, ShotPattern pattern) {
		super(rate);
		myProjectile = p;
		myPattern = pattern;
		
	}
}
