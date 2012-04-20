package weapons;

import gameObjects.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class AoeProjectile extends Projectile{
	PlayField myPlayField;
	int myRange;

	AoeProjectile( String imgPath, SpriteGroup g, PlayField playfield, int range) {
		super(imgPath, g);
		myPlayField = playfield;
		myRange = range;
	}

	@Override
	public void actionOnCollision(GameObject hit) {
		for(SpriteGroup sg : myPlayField.getGroups()){
			for(Sprite s : sg.getSprites()){
				if(Math.abs(s.getX() - hit.getX()) <= myRange && Math.abs(s.getY() - hit.getY())<= myRange) {
					aoeAction(s);
				}
			}
		}
		
	}
	
	public abstract void aoeAction(Sprite s);

	

	

}
