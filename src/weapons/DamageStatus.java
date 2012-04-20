package weapons;

import gameObjects.Enemy;
import gameObjects.GameObject;

public class DamageStatus extends Status{
	int damage;
	DamageStatus(int dur, int dam) {
		super(dur);
		damage = dam;
		
	}

	@Override
	public void applyStatus(GameObject holder) {
		if(holder.getType().equals("Enemy")){
			((Enemy) holder).sustainDamage(damage);
		}
	}

}
