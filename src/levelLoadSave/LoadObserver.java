package levelLoadSave;

import gameObjects.GameObject;

/**
 * 
 * @author antaresyee
 *
 */

public abstract class LoadObserver {
    
    protected String myType;
    
    public abstract void objectLoaded(GameObject go);
    
    public boolean isMyObserver(GameObject go) {
        return myType.equals(go.getType());
    }
    
}
