package levelLoadSave;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.SpriteGroup;

import gameObjects.GameObject;
import levelLoadSave.LoadObserver;
/**
 * 
 *
 */


public class EnemyLoadObserver extends LoadObserver {

    SpriteGroup mySpriteGroup;
    
    public EnemyLoadObserver(SpriteGroup spriteGroup) {
        super.myType = "Enemy";
        mySpriteGroup = spriteGroup;
    }
    
    @Override
    public void objectLoaded(GameObject go) {
        mySpriteGroup.add(go);

        //set sprite image
        try {
            File imgFile = new File(go.getImgPath());
            BufferedImage img = ImageIO.read(imgFile);
            go.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
