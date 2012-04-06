package gameObjects.boss;

import java.util.List;

public class BossTransition {
	private List<BossState> myBSList;
	
	public BossTransition(List<BossState> bsList)
	{
		myBSList=bsList;
	}
}
