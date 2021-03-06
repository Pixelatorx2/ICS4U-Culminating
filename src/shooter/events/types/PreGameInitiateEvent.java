package shooter.events.types;

import javax.swing.JPanel;

import shooter.events.Event;

public class PreGameInitiateEvent extends Event {
    
    private int diff;
    private JPanel panel;
    private int width, height;
    public PreGameInitiateEvent(int difficulty, JPanel panel, int width, int height) {
        diff = difficulty;
        this.width = width;
        this.height = height;
    }
    
    public int getDifficulty() {
        return this.diff;
    }
    
    public JPanel getPanel() {
        return this.panel;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
