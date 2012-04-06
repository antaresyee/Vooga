package weapons;

import com.golden.gamedev.object.SpriteGroup;

public class UnlimitedGun extends Gun{

	public UnlimitedGun(int rate, Projectile p, SpriteGroup g) {
		super(rate, p, g);
		
	}

	@Override
	public void actionOnFire(double xpos,double ypos) {
		myProjectile.createProjectile(xpos, ypos);
		
	}

	

}
