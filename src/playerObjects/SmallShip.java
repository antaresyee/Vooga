package playerObjects;

import com.golden.gamedev.Game;

public class SmallShip extends Ship{

	public SmallShip(double x, double y) {
		super(x, y, "resources/smallShip.png");
		// TODO Auto-generated constructor stub
		 
	}

	@Override
	public void move(Game g, int width, int height) {
		// TODO Auto-generated method stub
		//if (this.getY() > height - this.getHeight())
			this.moveY(-1.3); 
	}

}
