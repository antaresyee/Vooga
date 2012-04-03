package levelLoadSave;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    List<GameObjectFactory> myAllFactories;
    
    public LevelLoader() {
        myAllFactories = new ArrayList<GameObjectFactory>();
        myAllFactories.add(new Barrier.BarrierFactory(0, 0, null));
        myAllFactories.add(new Player.PlayerFactory(0, 0, null));
    }
    
    /**
     * load json file with GameObjectFactory objects delimited by newlines
     */
    public List<GameObjectFactory> load(String fileName) throws FileNotFoundException {
        List<GameObjectFactory> parsedObjects = new ArrayList<GameObjectFactory>();
        
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(fileName));
        
        Type stringClass = new TypeToken<String>(){}.getType(); //tell json parser the object's type
        
        while (scanner.useDelimiter("\n").hasNext()) {       
            
            String jsonGofTypeString = scanner.useDelimiter("\n").next();
            String jsonGof = scanner.useDelimiter("\n").next();
            
            String parsedGofTypeString = gson.fromJson(jsonGofTypeString, stringClass);
            for (GameObjectFactory f : myAllFactories) {
                if (f.isMyObject(parsedGofTypeString)) {
                    Type gofClass = f.getClass(); //tell json parser the object's type
                    GameObjectFactory parsedGof = gson.fromJson(jsonGof, gofClass);
                    parsedObjects.add(parsedGof);
                }
                
            }
        }
        
        return parsedObjects;
    }
    
    public static void main(String[] args) throws IOException {
        LevelLoader ll = new LevelLoader();
        
        List<GameObjectFactory> objectsToSave = new ArrayList<GameObjectFactory>();
        objectsToSave.add(new Barrier.BarrierFactory(1.5, 2.0, null));
        objectsToSave.add(new Player.PlayerFactory(3.0, 2.0, null));
        
        LevelSaver.save(objectsToSave, "testLevel");
        System.out.println(ll.load("testLevel.json"));
        
    }
}
