package levelLoadSave;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
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
    List<GameObjectFactory> myAllFactories;

    public LevelLoader(List<LoadObserver> loadObservers) {
        myLoadObservers = loadObservers;
        myAllFactories = new ArrayList<GameObjectFactory>();
        addAllFactories();
    }

    /**
     * use reflection to call all getFactory methods and add to myAllFactories
     */
    public void addAllFactories() {
        try {
            File packageDirectory = new File("src/gameObjects");
            String[] packageFiles = packageDirectory.list();
            
            for (int i = 0; i < packageFiles.length; i++) {
                // only get .class files
                if (packageFiles[i].endsWith(".java")) {
                    String className = packageFiles[i].substring(0,
                            packageFiles[i].length() - 5); // remove .class
                                                           // extension
                    Class<?> c = Class.forName("gameObjects." + className);
                    // if class has ForSave annotation, invoke getFactory(), add
                    // to myAllFactories
                    if (c.getAnnotation(ForSave.class) != null) {
                        Method[] methods = c.getMethods();
                        for (Method m : methods) {
                            if (m.getName().equals("getFactory")) {
                                GameObjectFactory gof = (GameObjectFactory) m
                                        .invoke(c);
                                myAllFactories.add(gof);
                            }
                        }
                    }

                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } 
    }

    public void loadLevelData(String fileName) {
        try {
            List<GameObjectData> gameObjectDatas;
            if (fileName.endsWith(".ser")) {
                gameObjectDatas = serializeLoad(fileName);
            }
            else {
                gameObjectDatas = jsonLoad(fileName);
            }

            for (GameObjectData god : gameObjectDatas) {
                for (GameObjectFactory f : myAllFactories) {
                    if (f.isMyObject(god)) {
                        GameObject loadedObject = f.makeGameObject(god);
                        notifyObservers(loadedObject);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<GameObjectData> serializeLoad(String fileName)
            throws FileNotFoundException {
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
