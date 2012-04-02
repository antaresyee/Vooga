package levelLoadSave;

import gameObjects.GameObject;
import gameObjects.GameObjectFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class LevelEditorSaver {
	public static void save(List<GameObject> objectsToSave) throws IOException {
        Gson gson = new Gson();
        
        //write objects to file
        FileWriter fw = new FileWriter("savedLevel.json");
        BufferedWriter bw = new BufferedWriter(fw);
        String jsonString = gson.toJson(objectsToSave);
        bw.write(jsonString);
//        for (GameObject object : objectsToSave) {
////            String objectType = object.getMyName();
//            Class<?> objectClass = object.getClass();
//            String jsonClassString = gson.toJson(objectClass);
////            String jsonTypeString = gson.toJson(objectType);
//            String jsonObjectString = gson.toJson(object);
////            bw.write(jsonTypeString);
//            bw.write(jsonClassString);
//            bw.write("\n");
//            bw.write(jsonObjectString);
//            bw.write("\n");
//        }
        bw.close();
    }
}
