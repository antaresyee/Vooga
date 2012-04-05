package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;



public abstract class Sprites {

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
	
	public abstract void askQuestions();
	
	public abstract int newID();
	
	public abstract GameObjectData makeGameObject(Sprite spr);
	
	public abstract static class Factory {

		public abstract boolean isType(int id);
		
		public abstract Sprites makeSprite();
			

	}

}