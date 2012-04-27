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

public abstract class Sprites extends Sprite{

	private String pngPath;
	private int x;
	private int y;

	public Sprites(String pngPath, int x, int y) {
		this.pngPath = pngPath;
		this.x=x;
		this.y=y;
	}

	public String getPath() {
		return pngPath;
	}
	public int getStartX() {
		return x;
	}
	public int getStartY() {
		return y;
	}
	
	public abstract String askQuestions(Question q);
	
	public abstract String getType();
	
	public abstract static class Factory {

		public abstract boolean isType(BufferedImage img, Game g);
		
		public abstract Sprites makeSprite();
		
		public abstract String getType();
		
	}

}
