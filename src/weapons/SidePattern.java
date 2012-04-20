package weapons;

public class SidePattern extends ShotPattern{
	int forwardspeed;
	int sidespeed;
	
	SidePattern(int forward, int side){
		forwardspeed = forward;
		sidespeed = side;
	}

	@Override
	void fire(Projectile p, double xpos, double ypos) {
		p.createProjectile(xpos, ypos, 0, forwardspeed);
		p.createProjectile(xpos, ypos, sidespeed, forwardspeed);
		p.createProjectile(xpos, ypos, -sidespeed, forwardspeed);
		
	}

}
