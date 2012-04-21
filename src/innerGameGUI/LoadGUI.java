package innerGameGUI;

import gameObjects.GameObject;

import java.io.FileNotFoundException;
import java.util.List;

import levelLoadSave.LevelLoader;
import levelLoadSave.LoadObserver;

import com.golden.gamedev.Game;
/**
 * Another example of using TabGUI
 * To create an interface to load games
 * 
 * @author Kaitlyn
 *
 */
public class LoadGUI extends TabGUI{
	
	private List<LoadObserver> myLoadObservers;
	private LevelLoader myLL;

	public LoadGUI(Game game, List<GameObject> gos,
			List<TabGUI> neighbors, List<LoadObserver> lo, int x, int y) {
		super(game, gos, "Load", neighbors, x, y);
		myLoadObservers = lo;
		myLL = new LevelLoader(lo);
		myMessage = "Load this file?";
//		if(gos != null)
//		{
//			for(GameObject go : gos)
//			{
//				go.setImage(game.getImage(go.getImgPath()));
//			}
//		}
//		setList(gos);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Example of implementing selectionEvent() method from TabGUI to do further stuff
	 * In this case, print the location
	 * 
	 * (non-Javadoc)
	 * @see innerGameGUI.TabGUI#selectionEvent()
	 */
	@Override
	public void selectionEvent()
	{
//		myLL.loadLevelData(((FileObject)myCurrentObject).getFilePath());
		try {
			System.out.println(myLL.serializeLoad(((FileObject)myCurrentObject).getFilePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
