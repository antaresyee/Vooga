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
        myAllFactories.add(new Barrier.BarrierFactory(0,0,null));
        myAllFactories.add(new Player.PlayerFactory(0, 0, null));
    }
    
    /**
     * load json file with GameObjectFactory objects delimited by newlines
     */
    public List<GameObjectFactory> load(String filename) throws FileNotFoundException {
        List<GameObjectFactory> parsedObjects = new ArrayList<GameObjectFactory>();
        
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(filename));
        //Type classType = new TypeToken<Class<?>>(){}.getType();
        Type strType = new TypeToken<String>(){}.getType();
        
        while (scanner.useDelimiter("\n").hasNext()) {       
            
            String jsonTypeString = scanner.useDelimiter("\n").next();
            //String jsonClassString = scanner.useDelimiter("\n").next();
            String jsonObjectString = scanner.useDelimiter("\n").next();
            
            //Class<?> parsedObjectClass = gson.fromJson(jsonClassString, classType);
            //Type objectClassType = parsedObjectClass.getGenericSuperclass();
            String parsedObjectType = gson.fromJson(jsonTypeString, strType);
            
            for (GameObjectFactory f : myAllFactories) {
                if (f.isMyObject(parsedObjectType)) {
                    Type objClass = f.getClass(); //get Type from Class<?>
                    GameObjectFactory parsedObject = gson.fromJson(jsonObjectString, objClass);
                    parsedObjects.add(parsedObject);
                }
                
            }
            
            //Type objType = new TypeToken<String>(){}.getType();
            //GameObjectFactory parsedObject = gson.fromJson(jsonObjectString, objectClassType);
            
            //if (parsedObjectType.isMyObject(parsedObjectType))
            
            //parsedObjects.add(parsedObject);
        }
        
        return parsedObjects;
    }
    
    public static void main(String[] args) throws IOException {
        LevelSaver ls = new LevelSaver();
        LevelLoader ll = new LevelLoader();
        
        List<GameObjectFactory> objectsToSave = new ArrayList<GameObjectFactory>();
        objectsToSave.add(new Barrier.BarrierFactory(1.5, 2.0, null));
        objectsToSave.add(new Player.PlayerFactory(3.0, 2.0, null));
        
        ls.save(objectsToSave);
        System.out.println(ll.load("savedLevel.json"));
        
    }
}
