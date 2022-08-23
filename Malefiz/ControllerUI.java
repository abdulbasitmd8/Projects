import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


/**
 * UI of the landing page of the application
 * @author Cassandra Clowe-Coish
 * @version 2021/03/03
 * 
 * Home Menu
 * This class has the objects, buttons and methods used for the home menu
 * containing the buttons and Malefiz title. 
 */

//the action listener interface is being implemented for this class for all the buttons
public class ControllerUI extends JFrame implements ActionListener, java.io.Serializable {
	
	private Controller controller;
	
	private JPanel titlePanel;
	private JPanel centerPanel;
	
	//the Malefiz label added to the title panel
	private JLabel titleLabel;
	
	//the buttons added to the panel
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton quitButton;

	public ControllerUI()
	{
		this.controller = new Controller();
		
		
		getContentPane().setLayout(new BorderLayout());
		this.setTitle("Malefiz!");
		
		titlePanel = new JPanel();
		centerPanel = new JPanel();
		
		titleLabel = new JLabel("Malefiz!");
		titleLabel.setForeground(Color.red);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Color.white);
		
		newGameButton = new JButton("New Game");
		newGameButton.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.magenta));
		newGameButton.addActionListener(this);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.magenta));
		loadGameButton.addActionListener(this);
		
		quitButton = new JButton("Quit");
		quitButton.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.magenta));
		quitButton.addActionListener(this);
		
		centerPanel.setLayout(new GridLayout(3,1));
		centerPanel.add(newGameButton);
		centerPanel.add(loadGameButton);
		centerPanel.add(quitButton);
		
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
		this.setSize(300,300);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( true);
        setVisible( true);
		
	}

//the action listener used to close the game and start a new instance
	public void actionPerformed(ActionEvent aevt) {
		Object selected = aevt.getSource();
		
		if (selected.equals(newGameButton)) {
			GameSetupUI setupUI = new GameSetupUI(this);
			this.setVisible(false);
		}

		if (selected.equals(loadGameButton)) {
			File gameFile = new File(("savedgame/savedgame.txt"));
			loadGameFromFile(gameFile);
			this.dispose();

			
		}
		
		else if (selected.equals(quitButton)) {
			this.dispose();
		}
		
	}

	public void loadGameFromFile (File file) {
		try {
			FileInputStream fileStream = new FileInputStream(file);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			MalefizGame savedMGame = (MalefizGame) objectStream.readObject();

			objectStream.close();
			fileStream.close();
			
			JOptionPane.showConfirmDialog(getContentPane(), 
				"Loaded saved game successfully.", 
				"Malefiz Game",   
				JOptionPane.DEFAULT_OPTION); 
			
			MalefizGameUI mUI = new MalefizGameUI(savedMGame);
		} 
		catch (Exception e) {   
			JOptionPane.showConfirmDialog(getContentPane(), 
				e.toString() + "\nFail to load settings.",   
				"Additional Settings", 
				JOptionPane.DEFAULT_OPTION);   
		}
	}
	
}
