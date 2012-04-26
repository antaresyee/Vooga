package weapons;

import java.io.Serializable;

public abstract class ShotPattern implements Serializable{
	abstract void fire(Projectile p,double xpos,double ypos);
}
