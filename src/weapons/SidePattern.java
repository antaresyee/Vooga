package weapons;

public class SidePattern extends ShotPattern{
	double forwardspeed;
	double sidespeed;
	
	public SidePattern(double forward, double side){
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
