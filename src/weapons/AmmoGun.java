package weapons;

import com.golden.gamedev.object.SpriteGroup;

public class AmmoGun extends Gun{
	int myAmmo;

	public AmmoGun(int rate, Projectile p, SpriteGroup g) {
		super(rate, p, g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionOnFire(double xpos,double ypos) {
		if(myAmmo > 0){
			myAmmo--;
			myProjectile.createProjectile(xpos, ypos);
		}
		
	}
	
	public void addAmmo(int addamount){
		myAmmo += addamount;
	}

	

}
