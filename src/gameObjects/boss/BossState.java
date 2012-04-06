package gameObjects.boss;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.SpriteGroup;

public class BossState extends GameObject{
	private List<BossWeakPoint> myWeakPoints;
	private SpriteGroup mySpriteGroup;
	
	public BossState()
	{
		myWeakPoints = new ArrayList<BossWeakPoint>();
		mySpriteGroup = new SpriteGroup("BossWeakPoints");
	}
	
	public BossState(List<BossWeakPoint> bwps)
	{
		mySpriteGroup = new SpriteGroup("BossWeakPoints");
		setWeakPoints(bwps);
	}
	
	public BossState(double x, double y, String imgPath)
	{
		setLocation(x,y);
		myImgPath = imgPath;
	}
	
	public void setWeakPoints(List<BossWeakPoint> bwps)
	{
		myWeakPoints = bwps;
		for(BossWeakPoint bwp : bwps)
		{
			mySpriteGroup.add(bwp);
		}
	}
	
	public void addWeakPoint(BossWeakPoint bwp)
	{
		myWeakPoints.add(bwp);
		mySpriteGroup.add(bwp);
	}
	
	public SpriteGroup getSpriteGroup()
	{
		return mySpriteGroup;
	}
	
	public List<BossWeakPoint> getWeakPoints()
	{
		return myWeakPoints;
	}

	@Override
	public String getImgPath() {
		// TODO Auto-generated method stub
		return this.myImgPath;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		// TODO Auto-generated method stub
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		return new BossState(x, y, imgPath);
	}
	
	public GameObjectFactory getFactory()
	{
		return new GameObjectFactory(new BossState());
	}
	
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		
	}
}
