package gameObjects.boss;

import java.util.ArrayList;
import java.util.List;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Boss extends GameObject {
	
	List<BossState> myStates;
	BossState currentState;
	BossTransition transition;
	
	public Boss(double x, double y, String imgPath)
	{
		setLocation(x,y);
		this.myImgPath=imgPath;
		myStates = new ArrayList<BossState>();
	}
	
	public Boss()
	{
		this.myType = "Boss";
		myStates = new ArrayList<BossState>();
	}
	
	public void addState(BossState bs)
	{
		myStates.add(bs);
	}
	
	public SpriteGroup getSpriteGroup()
	{
		return currentState.getSpriteGroup();
	}
	
	@Override
	public String getImgPath() {
		return this.myImgPath;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		// TODO Auto-generated method stub
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		return new Boss(x, y, imgPath);
	}
	
	public static GameObjectFactory getFactory()
	{
		return new GameObjectFactory(new Boss());
	}
	
}
