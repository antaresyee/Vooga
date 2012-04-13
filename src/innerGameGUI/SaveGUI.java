package innerGameGUI;

import gameObjects.GameObject;
import gameObjects.GameObjectData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import levelLoadSave.LevelLoader;
import levelLoadSave.LevelSaver;


import com.golden.gamedev.Game;

public class SaveGUI extends TabGUI {
	
	private List<GameObjectData> myGOD;

	public SaveGUI(Game game, List<GameObject> gos, List<GameObjectData> god,
			List<TabGUI> neighbors, int x, int y) {
		super(game, gos, "Save", neighbors, x, y);
		myGOD = god;
		myMessage = "Save to this file?";
//		System.out.println(myMessage);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void selectionEvent(){
//		String filename = "serializeTest";
//		String ext = ".ser";
//		String path = filename;
//		File file = new File(path+ext);
//		int i=2;
//		while(file.exists()){
//			path = filename+i;
//			file = new File(path+ext);
//			i++;
//		}
		LevelSaver.serializeSave(myGOD, ((FileObject)myCurrentObject).getFilePath());
		System.out.println("Saved to " + ((FileObject)myCurrentObject).getFilePath());
		try {
			System.out.println(new LevelLoader(null).serializeLoad(((FileObject)myCurrentObject).getFilePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
