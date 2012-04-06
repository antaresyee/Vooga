package gameObjects.boss;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.Sprite;

public class BossWeakPoint extends Sprite{
	private int relX, relY; //x, y relative to boss
	private List<BossWeakPoint> parents, children;
	private BossState myBS;
	private boolean hit;//extends Sprite, active = vulnerable
	
	public BossWeakPoint(){
		parents = new ArrayList<BossWeakPoint>();
		children = new ArrayList<BossWeakPoint>();
	}
	
	public BossWeakPoint(int x, int y, BossState bs){
		relX = x;
		relY = y;
		setLocation(x,y);
		hit = false;
		myBS = bs;
		parents = new ArrayList<BossWeakPoint>();
		children = new ArrayList<BossWeakPoint>();
	}
	
	public void addParent(BossWeakPoint bwp)
	{
		parents.add(bwp);
	}
	
	public void addChild(BossWeakPoint bwp)
	{
		children.add(bwp);
	}
	
	public boolean isHit()
	{
		return hit;
	}
	
	public void checkAllParentsHit()
	{
		for(BossWeakPoint bwp : parents)
		{
			if(!bwp.isHit()) return;
		}
		setActive(true);
	}
	
	public void hit()
	{
		hit = true;
		setActive(false);
		for(BossWeakPoint bwp : children)
		{
			if(!bwp.isActive()) bwp.checkAllParentsHit();
		}
	}
	
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		setLocation(relX+myBS.getX(), relY+myBS.getY());
	}
}
