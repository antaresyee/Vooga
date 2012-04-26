package weapons;

import com.golden.gamedev.object.SpriteGroup;

public class UnlimitedGun extends Gun{

	public UnlimitedGun(int rate, Projectile p, ShotPattern pattern) {
		super(rate, p,pattern);
		
	}

	@Override
	public void actionOnFire(double xpos,double ypos) {
		myPattern.fire(myProjectile, xpos, ypos);
		
	}

	

}
