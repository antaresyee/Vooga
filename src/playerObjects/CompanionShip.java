package playerObjects;

import gameObjects.Player;

import com.golden.gamedev.Game;

public class CompanionShip extends Ship {

	SmallShip comp;
	protected int myHozSpeed;

	public CompanionShip(double x, double y, String imgPath) {
		super(x, y, imgPath);

		myHozSpeed = 3;
	}

	public void setComp() {
		comp = new SmallShip(this.myX - 5, this.myY - 5);

	}

	@Override
	public void move(Game g, int width, int height) {

		comp.move(g, width, height);
		if (this.getX() > 0 && g.keyDown(java.awt.event.KeyEvent.VK_J)) {
			this.moveX(-myHozSpeed);
			comp.moveX(-myHozSpeed);
		}

		if (this.getX() < width - this.getWidth()
				&& g.keyDown(java.awt.event.KeyEvent.VK_L)) {
			this.moveX(myHozSpeed);
			comp.moveX(myHozSpeed);
		}
	}

	public SmallShip getComp() {
		return comp;
	}

}
