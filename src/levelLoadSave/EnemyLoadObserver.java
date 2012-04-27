package levelLoadSave;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import weapons.DamagingProjectile;
import weapons.Projectile;
import weapons.ScatterPattern;
import weapons.ShotPattern;
import weapons.SidePattern;
import weapons.SinglePattern;
import weapons.UnlimitedGun;
import weapons.Weapon;

import com.golden.gamedev.object.SpriteGroup;

import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.Player;
import levelLoadSave.LoadObserver;

public class EnemyLoadObserver extends LoadObserver {

    SpriteGroup mySpriteGroup;
    SpriteGroup myEnemyProjectileGroup;
    
    public EnemyLoadObserver(SpriteGroup spriteGroup, SpriteGroup enemyProjectileGroup) {
        super.myType = "Enemy";
        mySpriteGroup = spriteGroup;
        myEnemyProjectileGroup = enemyProjectileGroup;
    }
    
    @Override
    public void objectLoaded(GameObject go) {
        mySpriteGroup.add(go);
        
        Projectile p = new DamagingProjectile("resources/fire.png",myEnemyProjectileGroup,1);
        
        ShotPattern s1 = new SinglePattern(1);
        ShotPattern s2 = new ScatterPattern(5, -1, 5);
        ShotPattern s3 = new SidePattern(-1, 1);
        Weapon w1 = new UnlimitedGun(300,p,s1);
        Weapon w2 = new UnlimitedGun(300, p ,s2);
        Weapon w3 = new UnlimitedGun(300, p ,s3);
        Enemy e = (Enemy) go;
        e.addWeapon(w1);
        e.addWeapon(w2);
        e.addWeapon(w3);
        e.setWeapon(0);

        
        //set sprite image
        try {
            File imgFile = new File(go.getImgPath());
            BufferedImage img = ImageIO.read(imgFile);
            go.setImage(img);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }

}
