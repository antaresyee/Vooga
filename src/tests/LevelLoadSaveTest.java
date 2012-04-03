package tests;

import gameObjects.Barrier;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

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
    List<GameObjectFactory> myObjectsToSave;
    
    @Before
    public void setUp() throws Exception {
        myLevelLoader = new LevelLoader();
        
        myObjectsToSave = new ArrayList<GameObjectFactory>();
        myObjectsToSave.add(new Barrier.BarrierFactory(1.5, 2.0, null));
        myObjectsToSave.add(new Player.PlayerFactory(3.0, 2.0, null));
        
    }
    
    @Test
    public void testLoadSave() throws IOException {
        LevelSaver.save(myObjectsToSave, "testLevel");
        List<GameObjectFactory> parsedObjects = myLevelLoader.load("testLevel.json");
        
        assertEquals(parsedObjects.get(0), new Barrier.BarrierFactory(1.5, 2.0, null));
        assertEquals(parsedObjects.get(1), new Player.PlayerFactory(3.0, 2.0, null));
        
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
}
