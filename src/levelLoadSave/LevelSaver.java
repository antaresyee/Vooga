package levelLoadSave;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import GameObjects.GameObjectFactory;

import GameObjects.GameObjectFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author antaresyee
 *
 */


public class LevelSaver {
    public void save(List<GameObjectFactory> objectsToSave) throws IOException {
        Gson gson = new Gson();
        
        //write objects to file
        FileWriter fw = new FileWriter("savedLevel.json");
        BufferedWriter bw = new BufferedWriter(fw);
        for (GameObjectFactory object : objectsToSave) {
            String jsonString = gson.toJson(object);
            bw.write(jsonString);
            bw.write("\n");
        }
        bw.close();
    }
}
