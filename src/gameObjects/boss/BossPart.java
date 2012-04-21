package gameObjects.boss;

import java.awt.Graphics2D;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

public class BossPart extends GameObject {

	private String myImagePath;
	private BossState myBS;
	private List<List<Integer>> movement;
	private long start;
	private int index;

	public BossPart() {
		movement = new ArrayList<List<Integer>>();
		setBossState(new BossState());
		start = 0;
		index = 0;
	}
	
	public void setBossState(BossState bs){
		myBS = bs;
	}
	
	public void setMovement(List<List<Integer>> list){
		movement = list;
	}
	
	public void setImagePath(String path){
		myImagePath = path;
	}

	public void update(long elapsedTime) {
		start += elapsedTime;
		if (movement != null && movement.get(index).size() > 2) {
			if (start / 100.0 > movement.get(index).get(0)) {
				start = 0;
				index++;
				index %= movement.size();
			}
			moveX(movement.get(index).get(1));
			moveY(movement.get(index).get(2));
		}
		super.update(elapsedTime);
	}
	
//	public void render(Graphics2D pen){
//		super.render(pen);
//	}

	@Override
	public String getImgPath() {
		// TODO Auto-generated method stub
		return myImagePath;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		// TODO Auto-generated method stub
		return null;
	}
}
