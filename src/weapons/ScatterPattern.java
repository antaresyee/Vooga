package weapons;

public class ScatterPattern extends ShotPattern{
	int numshots;
	int forwardspeed;
	double widthblast;
	
	public ScatterPattern(int shots, int speed, double width){
		numshots = shots;
		forwardspeed = speed;
		widthblast = width;
	}

	@Override
	void fire(Projectile p, double xpos, double ypos) {
		for(int i = 0; i< numshots; i++){
			double xspeed = (i * (widthblast / numshots))-widthblast/2;
			p.createProjectile(xpos, ypos, xspeed, forwardspeed);
		}
		
	}

}
