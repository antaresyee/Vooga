package levelLoadSave;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import states.FullHealthState;
import states.LowHealthState;
import states.State;

import movement.BackForthMovement;
import movement.Movement;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;
import gameObjects.Barrier;
import gameObjects.Player;
import gameObjects.boss.Boss;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author antaresyee
 * 
 */

public class LevelLoader {

	List<LoadObserver> myLoadObservers;

	public LevelLoader(List<LoadObserver> loadObservers) {
		myLoadObservers = loadObservers;
	}

	public void loadLevelData(String fileName) {
		List<GameObjectData> gameObjectDatas;
		try {
			gameObjectDatas = serializeLoad(fileName);
			System.out.println(gameObjectDatas);
			GameObjectFactory playerFactory = Player.getFactory();
			GameObjectFactory barrierFactory = Barrier.getFactory();
			GameObjectFactory enemyFactory = Enemy.getFactory();
			GameObjectFactory bossFactory = Boss.getFactory();

			GameObject loaded = null;
			for (GameObjectData god : gameObjectDatas) {
				if (playerFactory.isMyObject(god)) {
					Player p = (Player) playerFactory.makeGameObject(god);
					loaded = p;
					System.out.println("player made");
				}
				if (barrierFactory.isMyObject(god)) {
					Barrier b = (Barrier) barrierFactory.makeGameObject(god);
					loaded = b;
					System.out.println("barrier made");

				}
				if (enemyFactory.isMyObject(god)) {
					Enemy e = (Enemy) enemyFactory.makeGameObject(god);
					loaded = e;
					System.out.println("enemy made");

				}
				if (bossFactory.isMyObject(god)) {
					Boss b = (Boss) bossFactory.makeGameObject(god);
					loaded = b;
					System.out.println("boss made");
				}
				notifyObservers(loaded);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<GameObjectData> serializeLoad(String fileName)
			throws FileNotFoundException {
		System.out.println("entered serializeLoad");
		List<GameObjectData> parsedObjects = new ArrayList<GameObjectData>();

		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);

			int numObjects = ois.readInt();

			for (int i = 0; i < numObjects; ++i) {
				parsedObjects.add((GameObjectData) ois.readObject());
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return parsedObjects;
	}

	/**
	 * load json file with GameObjectFactory objects delimited by newlines
	 */
	public List<GameObjectData> jsonLoad(String fileName)
			throws FileNotFoundException {
		Gson gson = new Gson();
		Scanner scanner = new Scanner(new File(fileName));

		List<GameObjectData> parsedObjects = new ArrayList<GameObjectData>();
		Type godClass = new TypeToken<GameObjectData>() {
		}.getType(); // tell json parser the object's type

		while (scanner.useDelimiter("\n").hasNext()) {
			String jsonGod = scanner.useDelimiter("\n").next();
			GameObjectData parsedGod = gson.fromJson(jsonGod, godClass);
			parsedObjects.add(parsedGod);
		}
		return parsedObjects;
	}

	public void notifyObservers(GameObject go) {
		for (LoadObserver lo : myLoadObservers) {
			if (lo.isMyObserver(go)) {
				lo.objectLoaded(go);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		LevelLoader l = new LevelLoader(null);

		List<GameObjectData> objectsToSave = new ArrayList<GameObjectData>();

		GameObjectData barrierData = new GameObjectData("Barrier");
		barrierData.setX(200.5);
		barrierData.setY(1000.0);
		barrierData.setImgPath("./resources/triangle.png");
		objectsToSave.add(barrierData);

		GameObjectData playerData = new GameObjectData("Player");
		playerData.setX(3.5);
		playerData.setY(4.0);
		playerData.setImgPath("./resources/enemy.png");
		objectsToSave.add(playerData);

		GameObjectData playerData2 = new GameObjectData("Player");
		playerData2.setX(100.0);
		playerData2.setY(500.0);
		playerData2.setImgPath("./resources/enemy.png");
		objectsToSave.add(playerData2);

		// LevelSaver.jsonSave(objectsToSave, "testLevel");
		// System.out.println(l.jsonLoad("testLevel.json"));

		LevelSaver.serializeSave(objectsToSave, "serializeTest");
		System.out.println(l.serializeLoad("serializeTest.ser"));

	}
}
