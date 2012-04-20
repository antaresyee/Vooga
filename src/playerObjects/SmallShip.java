package playerObjects;

import com.golden.gamedev.Game;

public class SmallShip extends Ship{

	public SmallShip(double x, double y, String imgPath) {
		super(x, y, imgPath);
		// TODO Auto-generated constructor stub
		 
	}

	@Override
	public void move(Game g, int width) {
		// TODO Auto-generated method stub
		this.moveY(-5); 
	}

}
