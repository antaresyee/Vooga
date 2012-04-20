package gameObjects.boss;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;

import java.awt.Graphics2D;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BossState extends GameObject {
	private List<BossWeakPoint> myWeakPoints;
	private List<BossPart> myBossParts;
	private SpriteGroup mySpriteGroup;
	private boolean isDead;
	private String jsonString;

	public BossState() {
		this(new ArrayList<BossWeakPoint>());
	}

	public BossState(List<BossWeakPoint> bwps) {
		mySpriteGroup = new SpriteGroup("BossWeakPoints");
		isDead = false;
		setParts(new ArrayList<BossPart>());
		setWeakPoints(bwps);
	}

	public BossState(double x, double y, String imgPath) {
		setLocation(x, y);
		myImgPath = imgPath;
	}
	
	public void setParts(List<BossPart> bps){
		myBossParts = bps;
//		System.out.println(myBossParts.size());
	}

	public void setWeakPoints(List<BossWeakPoint> bwps) {
		myWeakPoints = bwps;
		for (BossWeakPoint bwp : bwps) {
			mySpriteGroup.add(bwp);
		}
	}

	public void setImagePath(String path) {
		myImgPath = path;
	}

	public void addWeakPoint(BossWeakPoint bwp) {
		myWeakPoints.add(bwp);
		mySpriteGroup.add(bwp);
	}

	public SpriteGroup getSpriteGroup() {
		return mySpriteGroup;
	}

	public List<BossWeakPoint> getWeakPoints() {
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

	public GameObjectFactory getFactory() {
		return new GameObjectFactory(new BossState());
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
		for (BossWeakPoint bwp : myWeakPoints) {
			if (!bwp.isHit())
				bwp.update(elapsedTime);
		}
		for (BossPart bp : myBossParts){
			bp.update(elapsedTime);
		}
		checkDead();
	}

	public void render(Graphics2D pen) {
		super.render(pen);
		for (BossWeakPoint bwp : myWeakPoints) {
			bwp.render(pen);
		}
		for (BossPart bp : myBossParts){
			bp.render(pen);
		}
	}

	public void checkDead() {
		if (!isDead && myWeakPoints.size() != 0) {
			for (BossWeakPoint bwp : myWeakPoints) {
				if (!bwp.isHit())
					return;
			}
			isDead = true;
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void save(String prefix) {
		Gson gson = new Gson();
		jsonString = prefix
				+ gson.toJson(new BossState(getX(), getY(), myImgPath)) + "\n";
		// System.out.println("Boss state");
		for (BossWeakPoint bwp : myWeakPoints) {
			// System.out.println("checking weakpoints");
			if (!bwp.hasParent())
				jsonString += bwp.getJson(prefix + "-");
		}
	}

	public String getJson(String prefix) {
		save(prefix);
		return jsonString;
	}

	public int load(Game game, List<String> list, int index, String prefix) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		List<BossWeakPoint> bsl = new ArrayList<BossWeakPoint>();
		List<BossPart> bps = new ArrayList<BossPart>();
		Type godClass = new TypeToken<BossWeakPoint>() {
		}.getType(); // tell json parser the object's type
		while (index < list.size()) {
			String jsonGod = list.get(index);
			if (jsonGod.startsWith(prefix)) {
				if(jsonGod.startsWith(prefix+"*")){
					BossPart bp = gson.fromJson(jsonGod.substring(prefix.length()+1), new TypeToken<BossPart>(){}.getType());
					bp.setImage(game.getImage(bp.getImgPath()));
					bps.add(bp);
//					System.out.println(bps.size());
//					System.out.println(jsonGod);
					index++;
					continue;
				}
				BossWeakPoint parsedGod = gson.fromJson(
						jsonGod.substring(prefix.length()), godClass);
				bsl.add(parsedGod);
				index = parsedGod.load(list, index+1, this, prefix + '-');
				
			} else {
				setParts(bps);
				setWeakPoints(bsl);
				return index;
			}
		}
		setParts(bps);
		setWeakPoints(bsl);
		return index;
		// while (scanner.useDelimiter("\n").hasNext()) {
		// String jsonGod = scanner.useDelimiter("\n").next();
		// if(jsonGod.startsWith(prefix)){
		// BossWeakPoint parsedGod =
		// gson.fromJson(jsonGod.substring(prefix.length()), godClass);
		// bsl.add(parsedGod);
		// parsedGod.load(scanner, this, prefix+'-');
		// }
		// else{
		// break;
		// }
		// }
	}
}
