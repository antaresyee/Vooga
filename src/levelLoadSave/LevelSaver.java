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
    public static void save(List<GameObjectFactory> objectsToSave, String levelName) throws IOException {
        Gson gson = new Gson();
        
        //write objects to file
        FileWriter fw = new FileWriter(levelName + ".json");
        BufferedWriter bw = new BufferedWriter(fw);
        for (GameObjectFactory object : objectsToSave) {
            String gofTypeString = object.getMyName();
            String jsonGofTypeString = gson.toJson(gofTypeString);
            String jsonGof = gson.toJson(object);
            
            bw.write(jsonGofTypeString);
            bw.write("\n");
            bw.write(jsonGof);
            bw.write("\n");
        }
        bw.close();
    }
}
