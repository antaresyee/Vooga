package gameObjects;
import java.awt.image.BufferedImage;


import com.golden.gamedev.Game;


public class Player extends GameObject {
	
	public Player(double x, double y, BufferedImage image){
		myX = x;
		myY = y;
		myImage = image;
	}

	
	public static class PlayerFactory extends GameObjectFactory{
		
		private String myName = "player";
		
		public PlayerFactory(double x, double y, BufferedImage image){
			myX = x;
			myY = y;
			myImage = image;
		}

		@Override
		public GameObject makeObject() {
			return new Player(myX, myY, myImage);
		}

		@Override
		public boolean isMyObject(String name) {
			return myName.equals(name);
		}
		
	}

}
