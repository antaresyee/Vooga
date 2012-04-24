package levelLoadSave;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.SpriteGroup;

import game.TopDownDemo;
import gameObjects.GameObject;
import gameObjects.Player;

public class HorizontalShipLoadObserver extends LoadObserver{
    SpriteGroup myPlayerGroup;
    TopDownDemo myGame;

    public HorizontalShipLoadObserver(SpriteGroup playerGroup, TopDownDemo game) {
        super.myType = "HorizontalShip";
        myPlayerGroup = playerGroup;
        myGame = game;
    }

    @Override
    public void objectLoaded(GameObject go) {
        myPlayerGroup.add(go);
        System.out.println("entered objectLoaded");
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