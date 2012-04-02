package levelLoadSave;

import gameObjects.Barrier;
import gameObjects.GameObject;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LevelEditorLoader {
	List<GameObject> myObjects;
    
    public LevelEditorLoader() {
    }
    
    /**
     * load json file with GameObjectFactory objects delimited by newlines
     */
    public List<GameObject> load(String filename) throws FileNotFoundException {
//        List<GameObject> parsedObjects = new ArrayList<GameObject>();
        
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(filename));
        Type classType = new TypeToken<List<GameObject>>(){}.getType();
        String wholeFile = scanner.useDelimiter("\\A").next();
        ArrayList<GameObject> gos = gson.fromJson(wholeFile, classType);
//        Type strType = new TypeToken<String>(){}.getType();
        
//        while (scanner.useDelimiter("\n").hasNext()) {       
//            
////            String jsonTypeString = scanner.useDelimiter("\n").next();
////            String jsonClassString = scanner.useDelimiter("\n").next();
//            String jsonObjectString = scanner.useDelimiter("\n").next();
//            parsedObjects.add((GameObject) gson.fromJson(jsonObjectString, classType));
//            //Class<?> parsedObjectClass = gson.fromJson(jsonClassString, classType);
//            //Type objectClassType = parsedObjectClass.getGenericSuperclass();
////            String parsedObjectType = gson.fromJson(jsonTypeString, strType);
//            
////            for (GameObjectFactory f : myAllFactories) {
////                if (f.isMyObject(parsedObjectType)) {
////                    Type objClass = f.getClass(); //get Type from Class<?>
////                    GameObjectFactory parsedObject = gson.fromJson(jsonObjectString, objClass);
////                    parsedObjects.add(parsedObject);
////                }
////                
////            }
//            
//            //Type objType = new TypeToken<String>(){}.getType();
//            //GameObjectFactory parsedObject = gson.fromJson(jsonObjectString, objectClassType);
//            
//            //if (parsedObjectType.isMyObject(parsedObjectType))
//            
//            //parsedObjects.add(parsedObject);
//        }
        
        return gos;
    }
}
