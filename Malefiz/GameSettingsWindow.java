import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import java.io.*;

/**
 * A class representing the additional game settings UI for Malefiz, 
 * including selecting board color, tutorial mode, and music.
 * @author Dylan Hum & Sharif Sircar
 * @date 2021/03/19
 *
 */

public class GameSettingsWindow extends JFrame implements ActionListener, java.io.Serializable {
    
	private JPanel mainPanel, buttonPanel;
	private JLabel appearanceLabel, tutorialLabel, musicLabel;
	private JButton okButton, cancelButton, revertButton, saveButton, loadButton;
	private JCheckBox tutorialCheck;
	private JComboBox<String> appearanceBox, musicBox;
	private String[] stringAppearanceList = {"White", "Striped"};
	private String[] stringMusicList = {"Track1", "Track2", "No Music"};
	private String boardColor, currentMusicTrack; //Change over to game setup ui
	private String appearanceDesc = "This changes the background appearance of the board.";
	private String tutorialDesc = "Tutorial Mode offers tips while the game is playing for each player. Meant for beginners.";
	private String musicDesc = "Changes the background music of the game.";
	private Boolean tutorialOn = false; //Change over to game setup ui
	private int x, y;
	private GameSetupUI currentSetup;

	public GameSettingsWindow(int x, int y, GameSetupUI currentSetup) {
		this.x = x;
		this.y = y;
		this.currentSetup = currentSetup;
		this.setSize(600, 170);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(new GridLayout(3, 1, 0, 10));

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setLayout(new FlowLayout());
		
		//All the stuff on the GUI
		appearanceLabel = new JLabel("Change Appearance: ");
		mainPanel.add(appearanceLabel);
		appearanceBox = new JComboBox<>(stringAppearanceList);
		appearanceBox.setToolTipText(appearanceDesc);
		appearanceBox.setBackground(Color.white);
		appearanceBox.setEnabled(false);
		appearanceBox.addActionListener(this);
		mainPanel.add(appearanceBox);

		tutorialLabel = new JLabel("Toggle Tutorial Mode");
		mainPanel.add(tutorialLabel);
		tutorialCheck = new JCheckBox();
		tutorialCheck.setToolTipText(tutorialDesc);
		tutorialCheck.setBackground(Color.white);
		tutorialCheck.setEnabled(false);
		tutorialCheck.addActionListener(this);
		tutorialCheck.setEnabled(false);
		mainPanel.add(tutorialCheck);

		musicLabel = new JLabel("Change Music: ");
		mainPanel.add(musicLabel);
		musicBox = new JComboBox<>(stringMusicList);
		musicBox.setToolTipText(musicDesc);
		musicBox.setBackground(Color.white);
		musicBox.setEnabled(false);
		musicBox.addActionListener(this);
		mainPanel.add(musicBox);


		//Bottom buttons
		revertButton = new JButton("Revert to Defaults");
		revertButton.addActionListener(this);
		buttonPanel.add(revertButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		buttonPanel.add(okButton);

		saveButton = new JButton("Save Settings");
		saveButton.addActionListener(this);
		buttonPanel.add(saveButton);
		
		loadButton = new JButton("Load Settings");
		loadButton.addActionListener(this);
		buttonPanel.add(loadButton);
	
		getContentPane().setLayout(new BorderLayout());
    	getContentPane().add(mainPanel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
		
	}
	

	public void actionPerformed(ActionEvent actEvent) {
		Object selected = actEvent.getSource();

		if (selected.equals(appearanceBox)) {
			currentSetup.setBoardColor((String)appearanceBox.getSelectedItem());
		}

		if (selected.equals(tutorialCheck)) {
			Boolean checked = tutorialCheck.isSelected();
			toggleTutorial(checked);
			currentSetup.setTutorialStatus(this.tutorialOn);
		}

		if (selected.equals(musicBox)) {
			currentSetup.setMusicTrack((String)musicBox.getSelectedItem());
		}

		// Changes all possible editable values to the state on first creating the instance
		if (selected.equals(revertButton)) {
			appearanceBox.setSelectedIndex(0);
			tutorialCheck.setSelected(false);
			currentSetup.setTutorialStatus(tutorialCheck.isSelected());
			musicBox.setSelectedIndex(0);

		}
		// Changes the values that can be editable back to original state and disposes/closes the GameSettingsWindow instance.
		if (selected.equals(cancelButton)) {
			currentSetup.setBoardColor("White");
			currentSetup.setTutorialStatus(false);
			currentSetup.setMusicTrack("Track1");
			this.dispose();
		}

		if (selected.equals(okButton)) {
			this.dispose();
		}

		if (selected.equals(saveButton)) {
			File settingsToSave = new File("savedsettings/settings.txt");
			settingsToSave.delete();
			saveGameToFile(settingsToSave);
			
		}

		if (selected.equals(loadButton)) {
			File settingsFile = new File("savedsettings/settings.txt");
			loadSettingsFromFile(settingsFile);
			
		}
	}

	public void setBoardColor (String boardColor) {
		this.boardColor = boardColor;
	}
	
	public String getBoardColor () {
		return this.boardColor;
	}
   
	public void toggleTutorial (Boolean tutorialOn) {
		if (tutorialOn == false) {
			this.tutorialOn = false;
		}

		else {
			this.tutorialOn = true;
		}
	}

	public Boolean getTutorialStatus () {
		return this.tutorialOn;
	}

	public void setMusicTrack (String currentMusicTrack) {
		this.currentMusicTrack = currentMusicTrack;
	}

	public String getMusicTrack () {
		return this.currentMusicTrack;
	}

	public void saveGameToFile (File file) {
		
		try {
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

			objectStream.writeObject(currentSetup.getBoardColor());
			objectStream.writeObject(currentSetup.getMusicTrack());
			objectStream.writeObject(this.tutorialOn);

			objectStream.close();
			fileStream.close();

			JOptionPane.showConfirmDialog(getContentPane(), 
            "Save game settings successfully.", 
            "Additional Settings",   
            JOptionPane.DEFAULT_OPTION);   
    } catch (Exception e) {   
        JOptionPane.showConfirmDialog(getContentPane(), 
            e.toString() + "\nFail to save settings.",   
            "Additional Settings", 
            JOptionPane.DEFAULT_OPTION);   
    	}   
	}
	
	public void loadSettingsFromFile (File file) {
		String savedBoardColor;
		String savedMusicTrack;
		Boolean savedTutorialStatus;

		try {
			FileInputStream fileStream = new FileInputStream(file);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			savedBoardColor = (String) objectStream.readObject();
			savedMusicTrack = (String) objectStream.readObject();
			savedTutorialStatus = (Boolean) objectStream.readObject();

			objectStream.close();
			fileStream.close();

			appearanceBox.setSelectedItem(savedBoardColor);
			musicBox.setSelectedItem(savedMusicTrack);
			tutorialCheck.setSelected(savedTutorialStatus);
			currentSetup.setTutorialStatus(tutorialCheck.isSelected());

			JOptionPane.showConfirmDialog(getContentPane(), 
				"Save game settings successfully.", 
				"Additional Settings",   
				JOptionPane.DEFAULT_OPTION);   
		} catch (Exception e) {   
			JOptionPane.showConfirmDialog(getContentPane(), 
				e.toString() + "\nFail to load settings.",   
				"Additional Settings", 
				JOptionPane.DEFAULT_OPTION);   
		}   
	}
}	

		
	

