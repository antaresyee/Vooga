package levelLoadSave;
import gameObjects.GameObjectFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author antaresyee
 *
 */


public class LevelSaver {
    public static void save(List<GameObjectFactory> objectsToSave) throws IOException {
        Gson gson = new Gson();
        
        //write objects to file
        FileWriter fw = new FileWriter("savedLevel.json");
        BufferedWriter bw = new BufferedWriter(fw);
        for (GameObjectFactory object : objectsToSave) {
            String objectType = object.getMyName();
            //Class<?> objectClass = object.getClass();
            //String jsonClassString = gson.toJson(objectClass);
            String jsonTypeString = gson.toJson(objectType);
            String jsonObjectString = gson.toJson(object);
            bw.write(jsonTypeString);
            //bw.write(jsonClassString);
            bw.write("\n");
            bw.write(jsonObjectString);
            bw.write("\n");
        }
        bw.close();
    }
}
