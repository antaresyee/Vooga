package weapons;

import com.golden.gamedev.object.Timer;

public abstract class Weapon {
	Timer myTimer;
	
	public Weapon(int rate){
		myTimer = new Timer(rate);
	}
	
	public void fire(long arg0, double xpos, double ypos){
		if(myTimer.action(arg0)){
			actionOnFire(xpos, ypos);
		}
	}
	
	public abstract void actionOnFire(double xpos, double ypos);

}
