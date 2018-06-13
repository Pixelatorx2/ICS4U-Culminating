package shooter.menu;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import shooter.GameFrame;
import shooter.Main;
import shooter.events.EventHandler;
import shooter.events.types.PreGameInitiateEvent;

public class ShooterMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel title = new JLabel("SHOOTER GAME (Name TBD)");
	JButton play = new JButton("Play");
	JButton controls = new JButton("Controls");
	JButton quit = new JButton("Quit");
	JPanel buttonsPanel = new JPanel();
	
	JLabel weaponOne = new JLabel("(1) - to use Pistol");
	JLabel weaponTwo  = new JLabel("(2) - to use Rifle");
	JLabel forwards = new JLabel("(W) - to move forward towards cursor");
	JLabel backwards = new JLabel("(S) - to move backward from cursor");
	JLabel left = new JLabel("(A) - to move left of cursor");
	JLabel right = new JLabel("(D) - to move right of cursor");
	JLabel shoot = new JLabel("(SPACEBAR) - to shoot");
	boolean controlsShown = false;
	
	JRadioButton easyDifficulty = new JRadioButton("Easy", true);
	JRadioButton mediumDifficulty = new JRadioButton("Medium", false);
	JRadioButton hardDifficulty = new JRadioButton("Hard", false);
	ButtonGroup difficultiesGroup = new ButtonGroup();
	JButton confirmPlay = new JButton("Go!");
	boolean confirmPlayShown = false;
	
	Font titleFont = new Font("Roboto", Font.ITALIC, 50);
	Font defaultFont = new Font("Roboto", Font.PLAIN, 15);
	
	public ShooterMenu() {	
	
		InitializeGuiDetails();
		
		this.add(title);
		
		this.add(play);
		this.add(controls);
		this.add(quit);
		
		this.add(easyDifficulty);
		this.add(mediumDifficulty);
		this.add(hardDifficulty);
		this.add(confirmPlay);
		
		this.add(weaponOne);
		this.add(weaponTwo);
		this.add(forwards);
		this.add(backwards);
		this.add(left);
		this.add(right);
		this.add(shoot);
		
		this.setLayout(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Play"))
		{
			confirmPlayShown = !confirmPlayShown;
			confirmPlayVisibility();
			repaint();
		}
		
		else if (arg0.getActionCommand().equals("Controls"))
		{
			controlsShown = !controlsShown;
			controlLabelsVisibility();
			repaint();
		}
		
		else if (arg0.getActionCommand().equals("Quit"))
		{
			int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Exit?",JOptionPane.YES_NO_OPTION);
		       
	        if(confirmed == JOptionPane.YES_OPTION)
	        {
	            System.exit(0);
	        }
		}
		
		else if (arg0.getActionCommand().equals("Go"))
		{
			System.out.println("Playing game...");
			//Call game event
			
			int diff = 0;
            if(mediumDifficulty.isSelected()) diff += 1;
            if(hardDifficulty.isSelected()) diff += 2;
            EventHandler.callEvent(new PreGameInitiateEvent(diff, Main.getInstance().gamedisplay, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
		}
	}
	
	private void InitializeGuiDetails() {
		title.setFont(titleFont);
		title.setBounds(GameFrame.width / 3, 30, 1000, 50);
		
		setMainButtons(play, "Play", 0);
		setMainButtons(controls, "Controls", 250);
		setMainButtons(quit, "Quit", 500);
		
		setConfirmPlayPanelDetails(easyDifficulty, 0);
		setConfirmPlayPanelDetails(mediumDifficulty, 40);
		setConfirmPlayPanelDetails(hardDifficulty, 80);
		confirmPlay.addActionListener(this);
		confirmPlay.setActionCommand("Go");
		confirmPlay.setFont(defaultFont);
		confirmPlay.setVisible(confirmPlayShown);
		confirmPlay.setBounds(GameFrame.width / 3, (GameFrame.height / 5) + 120, 140, 50);
		
		setControlLabels(weaponOne, 0);
		setControlLabels(weaponTwo, 20);
		setControlLabels(forwards, 50);
		setControlLabels(backwards, 70);
		setControlLabels(left, 90);
		setControlLabels(right, 110);
		setControlLabels(shoot, 140);
	}
	
	private void setMainButtons(JButton button, String actionCommand, int space) {
		button.setFont(defaultFont);
		button.addActionListener(this);
		button.setActionCommand(actionCommand);		
		
		int startX = (GameFrame.width / 3) + space;
		int startY = GameFrame.height / 10;
		button.setBounds(startX, startY, 160, 70);
	}
	
	private void setConfirmPlayPanelDetails(JRadioButton radioButton, int space) {
		radioButton.setFont(defaultFont);
		difficultiesGroup.add(radioButton);
		radioButton.setVisible(confirmPlayShown);
		
		int startX = GameFrame.width / 3;
		int startY = (GameFrame.height / 5) + space;
		radioButton.setBounds(startX, startY, 200, 20);
	}
	
	private void setControlLabels(JLabel label, int space) {
		label.setFont(defaultFont);
		label.setVisible(controlsShown);
		
		int startX = (GameFrame.width / 3) + 250;
		int startY = (GameFrame.height / 5) + space;
		label.setBounds(startX, startY, 400, 15);
	}
	
	private void confirmPlayVisibility() {
		easyDifficulty.setVisible(confirmPlayShown);
		mediumDifficulty.setVisible(confirmPlayShown);
		hardDifficulty.setVisible(confirmPlayShown);
		confirmPlay.setVisible(confirmPlayShown);
	}
	
	private void controlLabelsVisibility() {
		weaponOne.setVisible(controlsShown);
		weaponTwo.setVisible(controlsShown);
		forwards.setVisible(controlsShown);
		backwards.setVisible(controlsShown);
		left.setVisible(controlsShown);
		right.setVisible(controlsShown);
		shoot.setVisible(controlsShown);
	}
}