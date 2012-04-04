package levelLoadSave;
import gameObjects.GameObjectData;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

/**
 * 
 * @author antaresyee
 *
 */


public class LevelSaver {
    public static void save(List<GameObjectData> objectsToSave, String levelName) throws IOException {
        //init
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(levelName + ".json");
        BufferedWriter bw = new BufferedWriter(fw);
        
        //write to file
        for (GameObjectData god : objectsToSave) {
            String jsonGod = gson.toJson(god);
            bw.write(jsonGod);
            bw.write("\n");
        }
        bw.close();
    }
}
