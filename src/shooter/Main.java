package shooter;

import java.io.IOException;

import shooter.events.CoreListeners;
import shooter.events.EventHandler;
import shooter.events.types.InitiateEvent;
import shooter.game.GameDisplay;
import shooter.menu.ShooterMenu;


/**
 * 
 * @author Chris Sankey & Mitchell LeLiever 29/5/2018
 *
 */
public class Main {

    static Main instance;
    GameFrame frame;
    ShooterMenu menu;
    GameDisplay game;
    
    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        instance = new Main();
        instance.registerListeners();
        instance.initialize();
    }

    public void initialize() {
        EventHandler.callEvent(new InitiateEvent());
    }

    /**
     * Make sure correct listeners are registered first in terms of priority
     */
    public void registerListeners() {
        EventHandler.registerListener(new InitializeListener());
        
        EventHandler.registerListener(new CoreListeners()); // Must be last
    }

    public GameFrame getFrame() {
        return this.frame;
    }
    
    public ShooterMenu getMenu() {
        return this.menu;
    }
    
    public GameDisplay getDisplay() {
        return this.game;
    }

    public static Main getInstance() {
        return instance;
    }

}
