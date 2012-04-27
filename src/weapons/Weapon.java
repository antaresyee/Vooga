package weapons;

import java.io.Serializable;

import com.golden.gamedev.object.Timer;

public abstract class Weapon implements Serializable{
	Timer myTimer;
	int x, y;
	
	public Weapon(int rate){
		myTimer = new Timer(rate);
	}
	
	public void fire(long arg0, double xpos, double ypos){
		if(myTimer.action(arg0)){
			actionOnFire(xpos, ypos);
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setLocation(int xx, int yy){
		x = xx;
		y = yy;
	}
	
	public abstract void actionOnFire(double xpos, double ypos);

}
