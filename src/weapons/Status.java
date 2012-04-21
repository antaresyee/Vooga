package weapons;

import gameObjects.GameObject;

public abstract class Status {
	int duration;
	
	Status(int dur){
		duration = dur;
	}
	
	public void iterate(GameObject holder){
		if(duration > 0){
			applyStatus(holder);
			duration --;
		}
	}
	
	public abstract void applyStatus(GameObject holder);
}
