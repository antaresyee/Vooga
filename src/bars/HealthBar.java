package bars;

import playerObjects.HozShip;
import playerObjects.Ship;

public class HealthBar extends Bar{

	int num = 10; 
	
	public HealthBar(Ship myShip) {
		super(myShip);
	}
	
	public void setHealth(int n){
		num = n; 
	}
	
	
	

}
