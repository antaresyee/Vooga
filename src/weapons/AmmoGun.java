package weapons;

import java.io.Serializable;

import com.golden.gamedev.object.SpriteGroup;

public class AmmoGun extends Gun implements Serializable{
	int myAmmo;

	public AmmoGun(int rate, Projectile p, ShotPattern pattern) {
		super(rate, p, pattern);
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
