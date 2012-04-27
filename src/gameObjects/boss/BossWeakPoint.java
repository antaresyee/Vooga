package gameObjects.boss;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BossWeakPoint extends GameObject{
	private double relX, relY; //x, y relative to boss
	private List<BossWeakPoint> parents, children;
	private BossState myBS;
	private String myImagePath;
	private boolean hit;//extends Sprite, active = vulnerable
	private String jsonString;
	
	public BossWeakPoint(){
		parents = new ArrayList<BossWeakPoint>();
		children = new ArrayList<BossWeakPoint>();
	}
	
	public BossWeakPoint(double x, double y, BossState bs){
		relX = x;
		relY = y;
		setLocation(x,y);
		hit = false;
		myBS = bs;
		parents = new ArrayList<BossWeakPoint>();
		children = new ArrayList<BossWeakPoint>();
	}
	
	public boolean hasParent()
	{
		return parents.size()>0;
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

	@Override
	public String getImgPath() {
		return myImagePath;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		Double x = god.getX();
		Double y = god.getY();
//		String imgPath = god.getImgPath();
		return new BossWeakPoint(x, y, null);
	}
	
	public void save(String prefix)
	{
		Gson gson = new Gson();
//		System.out.println("bwp:"+prefix);
		jsonString = prefix + gson.toJson(new BossWeakPoint(relX, relY, null))+"\n";
		for(BossWeakPoint bwp : children)
		{
			jsonString += bwp.getJson(prefix + "-");
		}
	}
	
	public int load(List<String> list, int index, BossState bs, String prefix) throws IOException
	{
		myBS = bs;
		Gson gson = new Gson();
		List<BossWeakPoint> bsl = new ArrayList<BossWeakPoint>();
		Type godClass = new TypeToken<BossWeakPoint>() {
		}.getType(); // tell json parser the object's type
		while (index < list.size()) {
			String jsonGod = list.get(index);
			if(jsonGod.startsWith(prefix)){
				BossWeakPoint parsedGod = gson.fromJson(jsonGod.substring(prefix.length()), godClass);
				parsedGod.setImage(ImageUtil.resize(ImageIO.read(new File(parsedGod.getImgPath())),5,5));
				bsl.add(parsedGod);
				index = parsedGod.load(list, index+1, myBS, prefix+'-');
			}
			else{
				setChildren(bsl);
				return index;
			}
		}
		setChildren(bsl);
		return index;
	}

	private void setChildren(List<BossWeakPoint> bsl) {
		children = bsl;
		for(BossWeakPoint bwp : children)
		{
			bwp.addParent(this);
		}
	}
	
	public String getJson(String prefix)
	{
		save(prefix);
		return jsonString;
	}
}
