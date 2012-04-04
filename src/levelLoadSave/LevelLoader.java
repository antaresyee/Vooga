package levelLoadSave;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;
import gameObjects.Barrier;
import gameObjects.Player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author antaresyee
 *
 */

public class LevelLoader {
    
    /**
     * load json file with GameObjectFactory objects delimited by newlines
     */
    public List<GameObjectData> load(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(fileName));
        
        List<GameObjectData> parsedObjects = new ArrayList<GameObjectData>();
        Type godClass = new TypeToken<GameObjectData>(){}.getType(); //tell json parser the object's type
        
        while (scanner.useDelimiter("\n").hasNext()) {       
            String jsonGod = scanner.useDelimiter("\n").next();
            GameObjectData parsedGod = gson.fromJson(jsonGod, godClass);
            parsedObjects.add(parsedGod);
        }
        return parsedObjects;
    }
    
    public static void main(String[] args) throws IOException {
        LevelLoader l = new LevelLoader();
        
        List<GameObjectData> objectsToSave = new ArrayList<GameObjectData>();
        
        GameObjectData barrierData = new GameObjectData("barrier");
        barrierData.setX(1.5);
        barrierData.setY(2.0);
        objectsToSave.add(barrierData);
        
        GameObjectData playerData = new GameObjectData("player");
        playerData.setX(3.5);
        playerData.setY(4.0);
        objectsToSave.add(playerData);
       
        LevelSaver.save(objectsToSave, "testLevel");
        System.out.println(l.load("testLevel.json"));
        
    }
}
