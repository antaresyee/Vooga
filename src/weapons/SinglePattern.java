package weapons;

public class SinglePattern extends ShotPattern{
	int mySpeed;
	
	SinglePattern(int speed){
		mySpeed = speed;
	}

	@Override
	void fire(Projectile p, double xpos, double ypos) {
		p.createProjectile(xpos, ypos, 0, mySpeed);
	}

}
