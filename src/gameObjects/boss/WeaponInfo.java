package gameObjects.boss;

public class WeaponInfo {
	private int x;
	private int y;
	private int fireRate;
	private int speed;
	private String WeaponName;
	private String Projectile;
	private String Pattern;
	private String ProjImgPath;
	
	public WeaponInfo(int xx, int yy, int fr, int sp, String wn, String p, String pa, String pip){
		x = xx;
		y = yy;
		fireRate = fr;
		speed = sp;
		WeaponName = wn;
		Projectile = p;
		Pattern = pa;
		ProjImgPath = pip;
	}
	
	public String getPattern() {
		return Pattern;
	}
	public void setPattern(String pattern) {
		Pattern = pattern;
	}
	public String getProjectile() {
		return Projectile;
	}
	public void setProjectile(String projectile) {
		Projectile = projectile;
	}
	public String getWeaponName() {
		return WeaponName;
	}
	public void setWeaponName(String weaponName) {
		WeaponName = weaponName;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getFireRate() {
		return fireRate;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public String getProjImgPath() {
		return ProjImgPath;
	}

	public void setProjImgPath(String projImgPath) {
		ProjImgPath = projImgPath;
	}
}
