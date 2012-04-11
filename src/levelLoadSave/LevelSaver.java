package levelLoadSave;
import gameObjects.GameObjectData;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import com.google.gson.Gson;

/**
 * 
 * @author antaresyee
 *
 */


public class LevelSaver {
    public static void serializeSave(List<GameObjectData> objectsToSave, String levelName) {
        try {
            FileOutputStream fos = new FileOutputStream(levelName + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            int numObjects = objectsToSave.size();
            oos.writeInt(numObjects);
            
            for (GameObjectData god : objectsToSave) {
                oos.writeObject(god);
            }
            oos.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void jsonSave(List<GameObjectData> objectsToSave, String levelName) throws IOException {
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
