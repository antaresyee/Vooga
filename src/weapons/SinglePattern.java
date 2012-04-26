package weapons;

import java.io.Serializable;

public class SinglePattern extends ShotPattern implements Serializable{
	int mySpeed;
	
	public SinglePattern(int speed){
		mySpeed = speed;
	}

	@Override
	void fire(Projectile p, double xpos, double ypos) {
		//System.out.println("here2");
		p.createProjectile(xpos, ypos, 0, mySpeed);
	}

}
