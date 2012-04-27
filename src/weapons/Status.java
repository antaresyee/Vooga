package weapons;

import gameObjects.GameObject;

public abstract class Status {
	int duration;
	String myName;
	
	Status(int dur, String name){
		duration = dur;
		myName = name;
	}
	
	public void iterate(GameObject holder){
		if(duration > 0){
			applyStatus(holder);
			duration --;
		}
	}
	
	public String getName(){
		return myName;
	}
	
	public abstract void applyStatus(GameObject holder);
}
