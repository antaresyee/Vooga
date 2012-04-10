package levelLoadSave;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.TopDownDemo;
import gameObjects.GameObject;
import gameObjects.Player;

import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author antaresyee
 *
 */

public class PlayerLoadObserver extends LoadObserver {
    SpriteGroup myPlayerGroup;
    TopDownDemo myGame;

    public PlayerLoadObserver(SpriteGroup playerGroup, TopDownDemo game) {
        super.myType = "Player";
        myPlayerGroup = playerGroup;
        myGame = game;
    }

    @Override
    public void objectLoaded(GameObject go) {
        myPlayerGroup.add(go);
        myGame.setPlayer((Player) go);

        //set sprite image
        try {
            File imgSrc = new File(go.getImgPath());
            BufferedImage sourceImage = ImageIO.read(imgSrc);
            go.setImage(sourceImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}
