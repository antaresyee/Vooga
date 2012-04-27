package gameObjects.boss;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import weapons.Projectile;
import weapons.ShotPattern;
import weapons.Weapon;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BossState extends GameObject {
	private List<List<Integer>> movement;
	private List<BossWeakPoint> myWeakPoints;
	private List<BossPart> myBossParts;
	private List<WeaponInfo> myWeaponInfo;
	private List<Weapon> myWeapons;
	private SpriteGroup mySpriteGroup, myProjectiles;
	private boolean isDead;
	private long start;
	private String jsonString;
	private int index;
	
	public BossState() {
		this(new ArrayList<BossWeakPoint>());
	}

	public BossState(List<BossWeakPoint> bwps) {
		mySpriteGroup = new SpriteGroup("BossWeakPoints");
		start = 0;
		index = 0;
		isDead = false;
		setParts(new ArrayList<BossPart>());
		setWeakPoints(bwps);
	}

	public BossState(double x, double y, String imgPath) {
		setLocation(x, y);
		start = 0;
		index = 0;
		myImgPath = imgPath;
	}
	
	public List<WeaponInfo> getWeaponInfo(){
		return myWeaponInfo;
	}
	
	public void setMovement(List<List<Integer>> list){
		movement = list;
	}
	
	public void setProjectiles(SpriteGroup sp){
		myProjectiles = sp;
	}
	
	public void setParts(List<BossPart> bps){
		myBossParts = bps;
//		System.out.println(myBossParts.size());
	}
	
	public void setWeaponInfo(List<WeaponInfo> wi){
		myWeaponInfo = wi;
	}
	
	public void setWeapons(List<Weapon> w){
		myWeapons = w;
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
		for (BossWeakPoint bwp : myWeakPoints) {
			if (!bwp.isHit())
				bwp.update(elapsedTime);
		}
		for (BossPart bp : myBossParts){
			bp.update(elapsedTime);
		}
		for (Weapon w : myWeapons){
			w.fire(elapsedTime, w.getX()+getX(), w.getY()+getY());
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

	public int load(Game game, List<String> list, int index, String prefix) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
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
					bp.setRelXY((int)bp.getX(), (int)bp.getY());
					List<WeaponInfo> wi = bp.getWeaponInfo();
					List<Weapon> w = new ArrayList<Weapon>();
					for(WeaponInfo winfo : wi){
						Class<?> projectile = Class.forName("weapons." + winfo.getProjectile());
						Class<?>[] typeList = {String.class, SpriteGroup.class, int.class};
						Constructor<?> c = projectile.getDeclaredConstructor(typeList);
						Object[] objs = {winfo.getProjImgPath(), myProjectiles, 1};
						Projectile proj = (Projectile) c.newInstance(objs);
						Class<?> pattern = Class.forName("weapons."+ winfo.getPattern());
						Class<?>[] typeList2 = {int.class};
						c = pattern.getDeclaredConstructor(typeList2);
						Object[] objs2 = {winfo.getSpeed()};
						ShotPattern pat = (ShotPattern) c.newInstance(objs2);
						Class<?> weapon = Class.forName("weapons." + winfo.getWeaponName());
						Class<?>[] typeList3 = {int.class, Projectile.class, ShotPattern.class};
						c = weapon.getDeclaredConstructor(typeList3);
						Object[] objs3 = {winfo.getFireRate(), proj, pat};
						Weapon weap = (Weapon) c.newInstance(objs3);
						weap.setLocation(winfo.getX(), winfo.getY());
						w.add(weap);
					}
					bp.setWeapons(w);
					bps.add(bp);
//					System.out.println(bps.size());
//					System.out.println(jsonGod);
					index++;
					continue;
				}
				BossWeakPoint parsedGod = gson.fromJson(
						jsonGod.substring(prefix.length()), godClass);
				parsedGod.setImage(ImageUtil.resize(ImageIO.read(new File(parsedGod.getImgPath())),5,5));
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
