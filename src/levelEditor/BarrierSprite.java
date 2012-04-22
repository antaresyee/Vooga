package levelEditor;

import java.awt.image.BufferedImage;

import gameObjects.GameObjectData;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
/**
 * 
 * @author Leo Rofe
 *
 */
public class BarrierSprite extends Sprites {
	private final String myType = "Barrier";
	public BarrierSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String askQuestions(Question q, Game g) {
		String str = " ";
		return str;
	}

	@Override
	public String getType() {
		return myType;
	}
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/blueBarrier.png";
		private final int startX = 130;
		private final int startY = 2929;
		private final String myType = "Barrier";


		@Override
		public boolean isType(BufferedImage img, Game g) {
			return img.equals(g.getImage(imagePath));
		}

		@Override
		public Sprites makeSprite() {
			return new BarrierSprite(imagePath, startX, startY);
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}
		

	}


}
