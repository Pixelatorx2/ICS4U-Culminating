package shooter.level;

import shooter.entities.Player;
import shooter.entities.Wall;
import shooter.entities.WeaponType;

public class LevelManager {

    private static Player player;
    private static Level currentLevel;
    
    public static void nextLevel() {
        //reset bullet id and clear the shot list
    }
    
    public static void init(int diff) {
        player = new Player(100, 100, 100, WeaponType.PISTOL, 50, 150, 0);
        Wall[] w = {new Wall(400, 410, 70, 50)};
        currentLevel = new Level(w, 60, 600, 600, diff);
    }
    
    public static Player getPlayer() {
        return player;
    }
    
    public static Level getCurrentLevel() {
        return currentLevel;
    }
}
