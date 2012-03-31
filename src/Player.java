import game.GameObject;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;


public class Player extends GameObject {
	
	public Player(double x, double y, BufferedImage image){
		myX = x;
		myY = y;
		myImage = image;
	}

	
	public class PlayerFactory extends GameObject.GameObjectFactory{
		
		public PlayerFactory(double x, double y, BufferedImage image){
			myX = x;
			myY = y;
			myImage = image;
		}

		@Override
		public GameObject makeObject() {
			return new Player(myX, myY, myImage);
		}
		
	}

}
