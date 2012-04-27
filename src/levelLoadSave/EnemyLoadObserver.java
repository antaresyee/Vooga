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

import com.golden.gamedev.object.SpriteGroup;

import gameObjects.Enemy;
import gameObjects.GameObject;
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
        
        Projectile p = new DamagingProjectile("resources/enemy.png",myEnemyProjectileGroup,1);
        ShotPattern s = new SinglePattern(-3);
        Weapon w = new UnlimitedGun(300,p,s);
        Enemy e = (Enemy) go;
        e.addWeapon(w);

        
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
