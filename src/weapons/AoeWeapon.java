package weapons;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class AoeWeapon extends Weapon{
	PlayField myPlayField;
	int myRange;

	public AoeWeapon(int rate, PlayField playfield, int range) {
		super(rate);
		myPlayField = playfield;
		myRange = range;
	}

	@Override
	public void actionOnFire(double xpos, double ypos) {
		for(SpriteGroup sg : myPlayField.getGroups()){
			for(Sprite s : sg.getSprites()){
				if(Math.abs(s.getX() - xpos) <= myRange && Math.abs(s.getY() - ypos)<= myRange) {
					actionOnHit(s);
				}
			}
		}
		
	}
	
	public abstract void actionOnHit(Sprite s);
	
	
}
