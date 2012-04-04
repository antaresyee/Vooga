package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;

public class PowerUpSprite extends Sprites {
	int powerUpCount=2001;
	private final String imagePath = "resources/powerup.png";

	
	public PowerUpSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int newID() {
		powerUpCount++;
		return powerUpCount;
	}
	@Override
	public GameObjectData makeGameObject(Sprite spr) {
		// TODO Auto-generated method stub
		return null;
	}
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/powerup.png";
		private final int startX = 330;
		private final int startY = 629;
		
		@Override
		public boolean isType(int id) {
			return 2000<id &&id<3001;
		}

		@Override
		public Sprites makeSprite() {
			// TODO Auto-generated method stub
			return new PowerUpSprite(imagePath, startX, startY);
		}




		
	}


}
