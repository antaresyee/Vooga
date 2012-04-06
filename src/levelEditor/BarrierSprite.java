package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;

public class BarrierSprite extends Sprites {
	int barrierCount=1001;
	private final String imagePath = "resources/black.png";
	
	public BarrierSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int newID() {
		barrierCount++;
		return barrierCount;
	}

	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/black.png";
		private final int startX = 130;
		private final int startY = 629;
		private final String myType = "Barrier";

		@Override
		public boolean isType(int id) {
			return 1000<id && id<2001;
		}

		@Override
		public Sprites makeSprite() {
			// TODO Auto-generated method stub
			return new BarrierSprite(imagePath, startX, startY);
		}


		@Override
		public GameObjectData makeGameObject(Sprite spr) {
			GameObjectData god = new GameObjectData(myType);
			god.setX(spr.getX());
			god.setY(spr.getY());
			god.setImgPath(imagePath);
			return god;
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}

	}


}
