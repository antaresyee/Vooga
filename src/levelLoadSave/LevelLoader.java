package levelLoadSave;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movement.BackForthMovement;

import gameObjects.GameObjectData;
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
    public List<GameObjectData> serializeLoad(String fileName) throws FileNotFoundException {
        List<GameObjectData> parsedObjects = new ArrayList<GameObjectData>();
        
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            int numObjects = ois.readInt();
            
            for (int i=0; i<numObjects; ++i) {
                parsedObjects.add((GameObjectData) ois.readObject());
            }
            ois.close();
        }
        catch (IOException e){
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return parsedObjects;
    }
    
    
    /**
     * load json file with GameObjectFactory objects delimited by newlines
     */
    public List<GameObjectData> jsonLoad(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(fileName));
        
        List<GameObjectData> parsedObjects = new ArrayList<GameObjectData>();
        Type godClass = new TypeToken<GameObjectData>(){}.getType(); //tell json parser the object's type
        
        while (scanner.useDelimiter("\n").hasNext()) {       
            String jsonGod = scanner.useDelimiter("\n").next();
            GameObjectData parsedGod = gson.fromJson(jsonGod, godClass);
            parsedObjects.add(parsedGod);
        }
        return parsedObjects;
    }
    
    public static void main(String[] args) throws IOException {
        LevelLoader l = new LevelLoader();
        
        List<GameObjectData> objectsToSave = new ArrayList<GameObjectData>();
        
        GameObjectData barrierData = new GameObjectData("barrier");
        barrierData.setX(1.5);
        barrierData.setY(2.0);
        barrierData.setImgPath("./resources/triangle.png");
        barrierData.setMovement(new BackForthMovement(2.3, 1, .5));
        objectsToSave.add(barrierData);
        
        GameObjectData playerData = new GameObjectData("player");
        playerData.setX(3.5);
        playerData.setY(4.0);
        playerData.setImgPath("./resources/enemy.png");
        objectsToSave.add(playerData);
        
        GameObjectData playerData2 = new GameObjectData("player");
        playerData2.setX(5.5);
        playerData2.setY(5.0);
        playerData2.setImgPath("./resources/enemy.png");
        objectsToSave.add(playerData2);
       
        //LevelSaver.jsonSave(objectsToSave, "testLevel");
        //System.out.println(l.jsonLoad("testLevel.json"));
        
        LevelSaver.serializeSave(objectsToSave, "serializeTest");
        System.out.println(l.serializeLoad("serializeTest.ser"));
        
    }
}
