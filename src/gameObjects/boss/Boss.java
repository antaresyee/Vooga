package gameObjects.boss;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import levelLoadSave.ForSave;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@ForSave
public class Boss extends GameObject {

	private List<BossState> myStates;
	private BossState currentState;
	private BossTransition transition;
	private boolean isDead, transformed;
	private String jsonString;

	public Boss(double x, double y, String imgPath) {
		this.myType = "Boss";
		setLocation(x, y);
		isDead = false;
		transformed = false;
		this.myImgPath = imgPath;
		myStates = new ArrayList<BossState>();
	}

	public Boss() {
		this(0, 0, "");
	}

	public void addState(BossState bs) {
		myStates.add(bs);
		if (currentState == null)
			setCurrent(0);
	}

	public SpriteGroup getSpriteGroup() {
		return currentState.getSpriteGroup();
	}

	public void setCurrent(int index) {
		if (myStates.size() > index) {
			currentState = myStates.get(index);
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void render(Graphics2D pen) {
		currentState.render(pen);
	}

	public void update(long elapsedTime) {
		if(transformed) transformed = false;
		if (currentState.isDead()) {
			int index = myStates.indexOf(currentState);
			if (index < myStates.size() - 1){
				transformed = true;
				currentState = myStates.get(index + 1);
			}else{
				isDead = true;
			}
		}
//		setImage(currentState.getImage());
		currentState.update(elapsedTime);
	}

	public boolean isDead() {
		return isDead;
	}
	
	public boolean transformed()
	{
		return transformed;
	}

	@Override
	public String getImgPath() {
		return this.myImgPath;
	}

	public BossState getCurrent() {
		return currentState;
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		// TODO Auto-generated method stub
		Double x = god.getX();
		Double y = god.getY();
		String imgPath = god.getImgPath();
		return new Boss(x, y, imgPath);
	}

	public static GameObjectFactory getFactory() {
		return new GameObjectFactory(new Boss());
	}

	public void save(String prefix) {
		jsonString = prefix;
		// System.out.println("boss");
		for (BossState bs : myStates) {
			jsonString += bs.getJson(prefix + "-");
		}
	}

	public String getJson(String prefix) {
		save(prefix);
		return jsonString;
	}

	public void load(Game game) {
		try {
			Gson gson = new Gson();
			Scanner scanner = new Scanner(new File("boss.json"));
			List<BossState> bsl = new ArrayList<BossState>();
			Type godClass = new TypeToken<BossState>() {
			}.getType(); // tell json parser the object's type
			List<String> list = new ArrayList<String>();
			while (scanner.useDelimiter("\n").hasNext()) {
				list.add(scanner.useDelimiter("\n").next());
			}
			int index = 0;
			while(index < list.size()){
				String jsonGod = list.get(index);
				if (jsonGod.startsWith("-") && jsonGod.charAt(1) != '-') {
					BossState parsedGod = gson.fromJson(jsonGod.substring(1), godClass);
					parsedGod.setImage(game.getImage(parsedGod.getImgPath()));
//					System.out.println(parsedGod.getImgPath());
					bsl.add(parsedGod);
					index = parsedGod.load(list, index+1, "--");
				}
			}
			myStates = bsl;
//			System.out.println(bsl.size());
			setCurrent(0);
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
		}
	}

}
