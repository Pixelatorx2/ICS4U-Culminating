package shooter.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import shooter.GameFrame;
import shooter.Main;
import shooter.entities.Player;
import shooter.entities.WeaponType;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintHUDListener implements Listener {

    Player player;
    Main main;
    BufferedImage pistolImg;
    BufferedImage pistolAmmoImg;
    BufferedImage rifleImg;
    BufferedImage rifleAmmoImg;

    JButton pistolButton;
    JButton rifleButton;

    int width;
    int height;
    

    public PaintHUDListener() {
        this.main = Main.getInstance();
    }

    @EventListener
    public void onGameStart(PreGameInitiateEvent e) {
        try {
            pistolImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Pistol.png"));
            pistolAmmoImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Pistol_Ammo_Icon.png"));
            rifleImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle.png"));
            rifleAmmoImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle_Ammo_Icon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.player = LevelManager.getPlayer();
        this.width = e.getWidth();
        this.height = e.getHeight();
        
        initializeGUIDetails();
    }

    @EventListener
    public void onRepaint(RepaintEvent e) {
        drawHUD(e.getGraphics());
    }

    public void drawHUD(Graphics2D g2)
    {
    	g2.setFont(new Font("ZombieA", Font.PLAIN, 50));
    	g2.drawString("Level " + LevelManager.getCurrentLevel().getNumber() + " - " + (LevelManager.getCurrentLevel().getTime() - GameThread.time), (GameFrame.width / 2) - 110, 75);
    	
        drawHealthBar(g2);
        drawAmmo(g2);
        drawWeaponButtons(g2);
        paintButtons(g2); 
        //IF ZOMBIE DIES
    }

    private void paintButtons(Graphics2D g2) {
        if(player.getWeapon() == WeaponType.PISTOL) {
            pistolButton.setBackground(Color.GREEN);
            rifleButton.setBackground(Color.WHITE);
        } else {
            pistolButton.setBackground(Color.WHITE);
            rifleButton.setBackground(Color.GREEN);
        }
    }

    public void drawHealthBar(Graphics2D g2) {
       
    	int healthBarX = width / 2;
        int healthBarY = height - 80;

        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(6));
        g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border

        if (player.getHealth() < 100) {
            g2.setPaint(Color.GRAY);
            g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar grey background portion
        }

        g2.setPaint(Color.RED);
        g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, player.getHealth() * 3, 40)); //Health bar filled portion

        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("ZombieA", Font.BOLD, 22));
        g2.drawString(Integer.toString(player.getHealth()), healthBarX - 10, healthBarY + 70); // Health number below health bar
    }

    public void drawAmmo(Graphics2D g2) {
        int ammoX = (width / 2) + 205;
        int ammoY = height - 100;
        BufferedImage ammoType = player.getWeapon().equals(WeaponType.PISTOL) ? pistolAmmoImg : rifleAmmoImg;
        int ammoNum = player.getWeapon().equals(WeaponType.PISTOL) ? player.getPistolAmmo() : player.getRifleAmmo();
        g2.drawImage(ammoType, ammoX, ammoY, Main.getInstance().gamedisplay);
        g2.drawString(Integer.toString(ammoNum), ammoX + 75, ammoY + 50);
    }

    public void drawWeaponButtons(Graphics2D g2) {
        int weaponX = width - 120;
        int weaponY = height - 300;
        pistolButton.setBounds(weaponX, weaponY, 75, 75);
        rifleButton.setBounds(weaponX, weaponY + 105, 75, 75);
        g2.drawString("1", weaponX - 20, weaponY + 20);
        g2.drawString("2", weaponX - 20, weaponY + 125);
    }

	public void initializeGUIDetails()
    {
        pistolButton = new JButton();
        rifleButton = new JButton();
        pistolButton.setFocusable(false);
        rifleButton.setFocusable(false);
        pistolButton.addActionListener((e) -> {
            player.setWeapon(WeaponType.PISTOL);
        });
        rifleButton.addActionListener((e) -> {
            player.setWeapon(WeaponType.RIFLE);
        });
        pistolButton.setActionCommand("Pistol");
        rifleButton.setActionCommand("Rifle");
        pistolButton.setBackground(Color.GREEN);

        pistolButton.setIcon(new ImageIcon(pistolImg));
        rifleButton.setIcon(new ImageIcon(rifleImg));

        main.gamedisplay.add(pistolButton);
        main.gamedisplay.add(rifleButton);
        main.gamedisplay.setLayout(null);
    }

}
