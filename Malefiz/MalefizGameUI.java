import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Main UI for playing the game of Malefiz. This is where the board and die are shown
 * @author Cassandra Clowe-Coish & Abdul Shaji
 *
 */

public class MalefizGameUI extends JFrame implements ActionListener, Serializable {
	
	private MalefizGame mgame;
	private BoardUI boardUI;

	//All the buttons that are added to the side panel below
	private JButton helpButton, saveButton, quitButton, newGame;
	private JPanel sidePanel;

	

	public MalefizGameUI(MalefizGame mgame)
	{
		
		//the default window size, and it's allowed to be resizable if user chooses to do so
		this.setSize(new Dimension(900,725));
		this.setResizable(true);

		this.mgame = mgame;
		
		//lamda action listener for all the buttons, to use the methods in this class or perform an action
		this.newGame = new JButton("New Game");
		newGame.addActionListener(s -> setNewBoard());
		this.helpButton = new JButton("Help");
		helpButton.addActionListener(s -> showHelp());
		this.saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		this.quitButton = new JButton("Quit");
		quitButton.addActionListener(s -> System.exit(0));
		
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(3,1));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2,1));
		
		
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(4,1));
		optionPanel.add(newGame);
		optionPanel.add(helpButton);
		optionPanel.add(saveButton);
		optionPanel.add(quitButton);
		
		
		// add all sub-panels to the side panel
		sidePanel.add(infoPanel);
		sidePanel.add(optionPanel);
		sidePanel.setPreferredSize(new Dimension(100,700));
		
		JLabel titleLabel = new JLabel("Malefiz!");
		infoPanel.add(titleLabel);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
		titleLabel.setForeground(Color.RED);
		
		getContentPane().setLayout(new BorderLayout());
		setBoard();
	}

	
	public void setBoard() {
		this.boardUI = new BoardUI(mgame.getBoard());
		getContentPane().add(getBoardUI(), BorderLayout.CENTER); //add a board to the UI
		getContentPane().add(sidePanel, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	public void setNewBoard() {
		
		this.dispose();
		ControllerUI ctrl = new ControllerUI();
	}
	
	public void showHelp() {
		
		String helpText = 
                "Rules of Malefiz:\n" +
                "\n" +
                "Each Player gets 5 distinct pawns to move on the game board.\n" +
                "A random player is chosen to go first.\n" +
                "In order to take a turn, you roll the die and move one of your pawns by the number of spaces you rolled.\n" +
                "You can pass pawns but you cannot pass barricades except by landing on them,\nbut if you land on another player's pawn, you send that player's pawn to its home space,\nand landing on a barricade allows you to send the barricade to any other empty space\n(besides first row spaces and the uppermost space) to block other players from winning.\n" +
                "To win, you must have one of your pawns land on the uppermost space.\n" +
                "Have fun!" ;
		
		JOptionPane.showMessageDialog(this, helpText);
	}
	
	
	
	public JButton getHelpButton() {
		return helpButton;
	}

	public void setHelpButton(JButton helpButton) {
		this.helpButton = helpButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
	
	public BoardUI getBoardUI() {
		return boardUI;
	}

	public void setBoardUI(BoardUI boardUI) {
		this.boardUI = boardUI;
	}

	
//To let the user know if they are overwriting previous save or if there isn't one currently.
	@Override
	public void actionPerformed(ActionEvent aevt) {
		Object selected = aevt.getSource();

		if (selected.equals(saveButton)) {
			File save = new File("savedgame/savedgame.txt");
			if (save.delete()) {
				saveGameToFile(save);
			}

			else {
				saveGameToFile(save);
			}
		}
	}

	public MalefizGame getMgame() {
		return mgame;
	}

	public void setMgame(MalefizGame mgame) {
		this.mgame = mgame;
	}

	//The method to save the current instance of the game state using serializable 
	public void saveGameToFile (File file) {

		try {
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

			objectStream.writeObject(this.mgame);
			
			objectStream.close();
			fileStream.close();

			JOptionPane.showConfirmDialog(getContentPane(), 
			"Saved game successfully.", 
			"Malefiz Game",   
			JOptionPane.DEFAULT_OPTION);
		}   
		catch (Exception e) {   
			JOptionPane.showConfirmDialog(getContentPane(), 
				e.toString() + "\nFail to save game.",   
				"Malefiz Game", 
				JOptionPane.DEFAULT_OPTION);   
			} 
	}
}
