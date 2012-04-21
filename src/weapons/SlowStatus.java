package weapons;

import gameObjects.Enemy;
import gameObjects.GameObject;

public class SlowStatus extends Status{
	int slowfactor;
	SlowStatus(int dur, int slow) {
		super(dur);
		slowfactor = slow;
	}
	@Override
	public void applyStatus(GameObject holder) {
		
			if(holder.getType().equals("Enemy")){
				((Enemy) holder).setSpeed(holder.getHorizontalSpeed()/slowfactor, holder.getVerticalSpeed()/slowfactor);
			}
		
	}

}
