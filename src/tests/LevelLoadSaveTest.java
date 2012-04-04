package tests;

import gameObjects.GameObjectData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import levelLoadSave.LevelLoader;
import levelLoadSave.LevelSaver;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelLoadSaveTest {
    
    LevelLoader myLevelLoader;
    List<GameObjectData> myObjectsToSave;
    
    @Before
    public void setUp() throws Exception {
        myLevelLoader = new LevelLoader();
        myObjectsToSave = new ArrayList<GameObjectData>();
        
        GameObjectData barrierData = new GameObjectData("barrier");
        barrierData.setX(1.5);
        barrierData.setY(2.0);
        myObjectsToSave.add(barrierData);
        
        GameObjectData playerData = new GameObjectData("player");
        playerData.setX(3.5);
        playerData.setY(4.0);
        myObjectsToSave.add(playerData);
    }
    
    @Test
    public void testLoadSave() throws IOException {
        LevelSaver.save(myObjectsToSave, "testLevel");
        List<GameObjectData> parsedObjects = myLevelLoader.load("testLevel.json");
        
        GameObjectData barrierData = new GameObjectData("barrier");
        barrierData.setX(1.5);
        barrierData.setY(2.0);
        assertEquals(parsedObjects.get(0), barrierData);
        
        GameObjectData playerData = new GameObjectData("player");
        playerData.setX(3.5);
        playerData.setY(4.0);
        assertEquals(parsedObjects.get(1), playerData);
        
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
}
