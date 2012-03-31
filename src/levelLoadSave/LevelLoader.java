package levelLoadSave;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import GameObjects.GameObjectFactory;

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
    public List<GameObjectFactory> load(String fileName) throws FileNotFoundException {
        List<GameObjectFactory> parsedObjects = new ArrayList<GameObjectFactory>();
        
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File("savedLevel.json"));
        Type objectType = new TypeToken<GameObjectFactory>(){}.getType();
        
        while (scanner.useDelimiter("\n").hasNext()) {
            String jsonString = scanner.useDelimiter("\n").next();
            GameObjectFactory parsedObject = gson.fromJson(jsonString, objectType);
            parsedObjects.add(parsedObject);
        }
        
        return parsedObjects;
    }
    
    public static void main(String[] args) {
        LevelSaver ls = new LevelSaver();
        LevelLoader ll = new LevelLoader();
        
        List<GameObjectFactory> objectsToSave = new ArrayList<GameObjectFactory>();
        objectsToSave.add(new GameObjects.Barrier.BarrierFactory(1.5, 2.0, null));
    }
}
