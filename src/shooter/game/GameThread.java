package shooter.game;

/*
 * Heartbeat of the thread - will try to keep to 40 ticks per second
 */
public class GameThread extends Thread {
    
    
    public static boolean STATUS = true;
    
    public void run() {
        while(STATUS) { 
            
            
            
            
            try {
                Thread.sleep(1000 / 40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
