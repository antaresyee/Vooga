package gameObjects;

public class GameObjectFactory {

    private GameObject myGameObject;
    
    public GameObjectFactory(GameObject go) {
        myGameObject = go;
    }
    
    public boolean isMyObject(GameObjectData god) {
        return myGameObject.isMyObject(god);
    }

    public GameObject makeGameObject(GameObjectData god) {
        return myGameObject.makeGameObject(god);
    }

}