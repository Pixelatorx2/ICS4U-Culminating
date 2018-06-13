package shooter;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public static int width;
    public static int height;
    
    CardLayout lay;
    public GameFrame() {    	
        this.setTitle("Shooter Game");
        lay = new CardLayout();
        this.setLayout(lay);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public CardLayout getLayout() {
    	width = this.getWidth();
    	height = this.getHeight();
        return this.lay;
    }
}
