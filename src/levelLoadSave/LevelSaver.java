package levelLoadSave;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import GameObjects.GameObjectFactory;

import com.google.gson.Gson;

/**
 * 
 * @author antaresyee
 *
 */


public class LevelSaver {
    
    public void save(List<GameObjectFactory> objectsToSave) {
        Gson gson = new Gson();
        
        for (GameObjectFactory object : objectsToSave) {
            String jsonString = gson.toJson(object);
            FileWriter fw = new FileWriter("savedLevel.json");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(jsonString);
            bw.close();
        }
    }
}
