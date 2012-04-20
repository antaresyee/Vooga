package weapons;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import gameObjects.Enemy;
import gameObjects.GameObject;

public abstract class ChainStatus extends Status{
	int myRange;
	int originalduration;
	PlayField myPlayField;

	ChainStatus(int dur, int ran) {
		super(dur);
		myRange = ran;
		originalduration =dur;
	}

	@Override
	public void applyStatus(GameObject holder) {
		for(SpriteGroup sg : myPlayField.getGroups()){
			for(Sprite s : sg.getSprites()){
				if(Math.abs(s.getX() - holder.getX()) <= myRange && Math.abs(s.getY() - holder.getY())<= myRange) {
					if(((GameObject) s).getType().equals("Enemy")){
						((Enemy)s).addStatus(makeThis());
					}
				}
			}
		}
		
	}
	
	public abstract ChainStatus makeThis(); 
	
	public abstract void applyInternalStatus(GameObject holder);

}
