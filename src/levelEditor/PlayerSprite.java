package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;



public class PlayerSprite extends Sprites {
	int playerCount=3001;
	public PlayerSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int newID() {
		playerCount++;
		return playerCount;
	}
	@Override
	public GameObjectData makeGameObject(Sprite spr) {
		// TODO Auto-generated method stub
		return null;
	}
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/ship.png";
		private final int startX = 30;
		private final int startY = 629;
		
		@Override
		public boolean isType(int id) {
			return id>3000;
		}

		@Override
		public Sprites makeSprite() {
			// TODO Auto-generated method stub
			return new PlayerSprite(imagePath, startX, startY);
		}




		
	}


}

