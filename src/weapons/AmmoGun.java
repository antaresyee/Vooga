package weapons;

import com.golden.gamedev.object.SpriteGroup;

public class AmmoGun extends Gun{
	int myAmmo;

	public AmmoGun(int rate, Projectile p, SpriteGroup g, ShotPattern pattern) {
		super(rate, p, g, pattern);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionOnFire(double xpos,double ypos) {
		if(myAmmo > 0){
			myAmmo--;
			myPattern.fire(myProjectile, xpos, ypos);
		}
		
	}
	
	public void addAmmo(int addamount){
		myAmmo += addamount;
	}

	

}
