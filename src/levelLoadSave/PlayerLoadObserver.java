package levelLoadSave;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import weapons.DamagingProjectile;
import weapons.Projectile;
import weapons.ShotPattern;
import weapons.SinglePattern;
import weapons.UnlimitedGun;
import weapons.Weapon;

import game.TopDownDemo;
import gameObjects.Enemy;
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
    SpriteGroup myProjectileGroup;
    
    TopDownDemo myGame;

    public PlayerLoadObserver(SpriteGroup playerGroup, SpriteGroup projectileGroup, TopDownDemo game) {
        super.myType = "Player";
        myPlayerGroup = playerGroup;
        myProjectileGroup = projectileGroup;
        myGame = game;
    }

    @Override
    public void objectLoaded(GameObject go) {
        System.out.println("entered player object loaded");
        myPlayerGroup.add(go);
        myGame.setPlayer((Player) go);
        
        Projectile p = new DamagingProjectile("resources/enemy.png",myProjectileGroup,1);
        ShotPattern s = new SinglePattern(-3);
        Weapon w = new UnlimitedGun(300,p,s);
        Enemy e = (Enemy) go;
        e.addWeapon(w);

        //set sprite image
        try {
            File imgSrc = new File(go.getImgPath());
            BufferedImage sourceImage = ImageIO.read(imgSrc);
            go.setImage(sourceImage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        

    }
}
