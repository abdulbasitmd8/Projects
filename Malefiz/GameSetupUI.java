import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A class representing the game setup UI screen for Malefiz, 
 * including selected player amounts, pawn styles, AI difficulty, player names, and rules.
 * @author Dylan Hum & Sharif Sircar
 * @date 2021/03/20
 *
 */


public class GameSetupUI extends JFrame implements ActionListener, FocusListener {
	
	private ControllerUI root;
    
    private JPanel amountsPanel, settingsPanel, settingsButtonPanel, rulesPanel, bottomButtonPanel;
    private JLabel amtHumanPlayersLabel, amtCompPlayersLabel, rulesLabel;
    private JLabel[] playerLabels = {new JLabel(" "), new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4")};
    private JButton startGameButton, cancelButton, changeGameSettingsButton, applyChangesButton, cancelChangesButton;
    private JTextField p1NameField, p2NameField, p3NameField, p4NameField;
    private JTextField[] nameFields;
    private String[] pawnStyles = {"Car", "Green Cat", "Snake", "Dart", "Celestial Thing", "Potato"};
    private String[] difficulties = {"Easy", "Hard"};
    private String[] tempCPUNames = {"CPU1", "CPU2", "CPU3", "CPU4"};
    private String [] defaultPlayerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
    // Integer array templates to hold the right selections for combo boxes.
    private Integer[] humanPlayerAmount = {0, 1, 2, 3, 4};
    private Integer[] compPlayerAmount = {0, 1, 2, 3, 4};
    private Integer[] maxPlayers0 = {0};
    private Integer[] maxPlayers1 = {0, 1};
    private Integer[] maxPlayers2 = {0, 1, 2};
    private Integer[] maxPlayers3 = {0, 1, 2, 3};
    private JComboBox<String> p1PawnStyles, p2PawnStyles, p3PawnStyles, p4PawnStyles, 
                                c1DifficultyBox, c2DifficultyBox, c3DifficultyBox, c4DifficultyBox;
    private JComboBox<Integer> amtHumanPlayers, amtCompPlayers;
    private int humanPlayers = 0, compPlayers = 0;
    private JComboBox<String>[] stylesComboBoxes;
    private JComboBox<String>[] difficultyComboBoxes;
    private DefaultComboBoxModel<Integer>[] restrictionHumanModels;
    private DefaultComboBoxModel<Integer>[] restrictionCompModels;
    private HashMap<String, String>[] listHumanPlayers;
    private HashMap<String, Difficulty>[] listCompPlayers;
    private HashMap<String, String> nameAndStyleHuman;
    private HashMap<String, Difficulty> difficultyAndStyleComp;
    private String p1Name, p2Name, p3Name, p4Name, 
                    p1PawnStyle, p2PawnStyle, p3PawnStyle, p4PawnStyle,
                    c1TempDifficulty, c2TempDifficulty, c3TempDifficulty, c4TempDifficulty;
    private Difficulty c1Difficulty, c2Difficulty, c3Difficulty, c4Difficulty;
    private String boardColor = "White";
    private String currentMusicTrack = "Track1";
    private Boolean tutorialOn = false;
    // rules text in html form.
    private String rulesText = "<html>\n" +
                    "     Rules of Malefiz:\n" +
                    "<ol>\n" +
                    "<li>Each Player gets 5 distinct pawns to move on the game board.\n" +
                    "<li>A random player is chosen to go first.\n" +
                    "<li>In order to take a turn, you roll a die and move one of your pawns that number of spaces you rolled without going backwards.\n" +
                    "<li>You can pass pawns but not barricades, but if you land on another player's pawn or barricade, you take that player's pawn as yours or destroy the barricade.\n" +
                    "<li>To win, you must have one of your pawns land on the uppermost space.\n" +
                    "<li>Have fun!" +
                    "</ol>\n" +
                    "Please press the Apply Changes Button before clicking Start Malefiz";

    // All the different ComboBoxModel objects for the program to distinguish between each box through actionPerformed
    private DefaultComboBoxModel<Integer> maxHumanPlayers0Model;
    private DefaultComboBoxModel<Integer> maxHumanPlayers1Model;
    private DefaultComboBoxModel<Integer> maxHumanPlayers2Model;
    private DefaultComboBoxModel<Integer> maxHumanPlayers3Model;
    private DefaultComboBoxModel<Integer> maxCompPlayers0Model;
    private DefaultComboBoxModel<Integer> maxCompPlayers1Model;
    private DefaultComboBoxModel<Integer> maxCompPlayers2Model;
    private DefaultComboBoxModel<Integer> maxCompPlayers3Model;
    private DefaultComboBoxModel<Integer> defaultHumanPlayersModel;
    private DefaultComboBoxModel<Integer> defaultCompPlayersModel;
    private DefaultComboBoxModel<String> p1StyleModel;
    private DefaultComboBoxModel<String> p2StyleModel;
    private DefaultComboBoxModel<String> p3StyleModel;
    private DefaultComboBoxModel<String> p4StyleModel;
    private DefaultComboBoxModel<String> c1DifficultyModel;
    private DefaultComboBoxModel<String> c2DifficultyModel;
    private DefaultComboBoxModel<String> c3DifficultyModel;
    private DefaultComboBoxModel<String> c4DifficultyModel;


    
    @SuppressWarnings("unchecked")
	public GameSetupUI(ControllerUI root) {
    	
    	this.root = root;
    	
        this.setSize(600, 1000);

        // Setting each ComboBoxModel to a template to create separate objects
        this.p1StyleModel = new DefaultComboBoxModel<>(pawnStyles);
        this.p2StyleModel = new DefaultComboBoxModel<>(pawnStyles);
        this.p3StyleModel = new DefaultComboBoxModel<>(pawnStyles);
        this.p4StyleModel = new DefaultComboBoxModel<>(pawnStyles);
        this.c1DifficultyModel = new DefaultComboBoxModel<>(difficulties);
        this.c2DifficultyModel = new DefaultComboBoxModel<>(difficulties);
        this.c3DifficultyModel = new DefaultComboBoxModel<>(difficulties);
        this.c4DifficultyModel = new DefaultComboBoxModel<>(difficulties);

        // rulesPanel initializer to hold the rules text
        rulesPanel = new JPanel();
        rulesPanel.setBackground(Color.white);
        rulesPanel.setLayout(new GridLayout());

        //amountsPanel initializer to hold player amount settings
        amountsPanel = new JPanel();
        amountsPanel.setBackground(Color.white);
        amountsPanel.setLayout(new GridLayout(0, 4, 5, 15));

        // SettingsPanel initializer to hold all settings
        settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.white);
        settingsPanel.setLayout(new GridLayout(0, 5, 5, 15));

        // initializer to hold the apply changes and cancel changes buttons
        settingsButtonPanel = new JPanel();
        settingsButtonPanel.setBackground(Color.white);
        settingsButtonPanel.setLayout(new GridBagLayout());

        // initializer to hold start malefiz and cancel buttons
        bottomButtonPanel = new JPanel();
		bottomButtonPanel.setBackground(Color.white);
		bottomButtonPanel.setLayout(new GridBagLayout());

        // Adding all labels, buttons, interactable components. To line 290.
        amtHumanPlayersLabel = new JLabel("Number of Human Players: ");
        amountsPanel.add(amtHumanPlayersLabel);
        amtHumanPlayers = new JComboBox<>(humanPlayerAmount);
        amtHumanPlayers.setToolTipText("The amount of human players that will be playing. 2 total players minimum, 4 human players maximum");
        amtHumanPlayers.setBackground(Color.white);
        amtHumanPlayers.addActionListener(this);
        amountsPanel.add(amtHumanPlayers);

        amtCompPlayersLabel = new JLabel("Number of Computer Players: ");
        amountsPanel.add(amtCompPlayersLabel);
        amtCompPlayers = new JComboBox<>(compPlayerAmount);
        amtCompPlayers.setToolTipText("The amount of Computer players that will be playing. 2 total players minimum, 4 computer players maximum");
        amtCompPlayers.setBackground(Color.white);
        amtCompPlayers.addActionListener(this);
        amtCompPlayers.setEnabled(false);
        amountsPanel.add(amtCompPlayers);

        p1PawnStyles = new JComboBox<>();
        p1PawnStyles.setModel(p1StyleModel);
        p1PawnStyles.setToolTipText("Select Pawn Style");
        p1PawnStyles.setBackground(Color.white);
        p1PawnStyles.addActionListener(this);
        p1PawnStyles.setEnabled(false);

        p2PawnStyles = new JComboBox<>();
        p2PawnStyles.setModel(p2StyleModel);
        p2PawnStyles.setToolTipText("Select Pawn Style");
        p2PawnStyles.setBackground(Color.white);
        p2PawnStyles.setSelectedIndex(1);
        p2PawnStyles.addActionListener(this);
        p2PawnStyles.setEnabled(false);

        p3PawnStyles = new JComboBox<>();
        p3PawnStyles.setModel(p3StyleModel);
        p3PawnStyles.setToolTipText("Select Pawn Style");
        p3PawnStyles.setBackground(Color.white);
        p3PawnStyles.setSelectedIndex(2);
        p3PawnStyles.addActionListener(this);
        p3PawnStyles.setEnabled(false);

        p4PawnStyles = new JComboBox<>();
        p4PawnStyles.setModel(p4StyleModel);
        p4PawnStyles.setToolTipText("Select Pawn Style");
        p4PawnStyles.setBackground(Color.white);
        p4PawnStyles.setSelectedIndex(3);
        p4PawnStyles.addActionListener(this);
        p4PawnStyles.setEnabled(false);
        
        c1DifficultyBox = new JComboBox<>();
        c1DifficultyBox.setModel(c1DifficultyModel);
        c1DifficultyBox.setToolTipText("Choose from the selection of 2 difficulties");
        c1DifficultyBox.setBackground(Color.white);
        c1DifficultyBox.addActionListener(this);
        c1DifficultyBox.setEnabled(false);

        c2DifficultyBox = new JComboBox<>();
        c2DifficultyBox.setModel(c2DifficultyModel);
        c2DifficultyBox.setToolTipText("Choose from the selection of 2 difficulties");
        c2DifficultyBox.setBackground(Color.white);
        c2DifficultyBox.addActionListener(this);
        c2DifficultyBox.setEnabled(false);

        c3DifficultyBox = new JComboBox<>();
        c3DifficultyBox.setModel(c3DifficultyModel);
        c3DifficultyBox.setToolTipText("Choose from the selection of 2 difficulties");
        c3DifficultyBox.setBackground(Color.white);
        c3DifficultyBox.addActionListener(this);
        c3DifficultyBox.setEnabled(false);

        c4DifficultyBox = new JComboBox<>();
        c4DifficultyBox.setModel(c4DifficultyModel);
        c4DifficultyBox.setToolTipText("Choose from the selection of 2 difficulties");
        c4DifficultyBox.setBackground(Color.white);
        c4DifficultyBox.addActionListener(this);
        c4DifficultyBox.setEnabled(false);

        p1NameField = new JTextField();
        p1NameField.setToolTipText("Type the name for Player 1");
        p1NameField.setBackground(Color.white);
        p1NameField.addActionListener(this);
        p1NameField.addFocusListener(this);
        p1NameField.setEnabled(false);

        p2NameField = new JTextField();
        p2NameField.setToolTipText("Type the name for Player 2");
        p2NameField.setBackground(Color.white);
        p2NameField.addActionListener(this);
        p2NameField.addFocusListener(this);
        p2NameField.setEnabled(false);

        p3NameField = new JTextField();
        p3NameField.setToolTipText("Type the name for Player 3");
        p3NameField.setBackground(Color.white);
        p3NameField.addActionListener(this);
        p3NameField.addFocusListener(this);
        p3NameField.setEnabled(false);

        p4NameField = new JTextField();
        p4NameField.setToolTipText("Type the name for Player 4");
        p4NameField.setBackground(Color.white);
        p4NameField.addActionListener(this);
        p4NameField.addFocusListener(this);
        p4NameField.setEnabled(false);

        changeGameSettingsButton = new JButton("Change Additional Game Settings");
        changeGameSettingsButton.addActionListener(this);

        // Creating HashMaps for future data processing.
        this.listHumanPlayers = (HashMap<String, String>[]) new HashMap[4];
        this.listCompPlayers = (HashMap<String, Difficulty>[]) new HashMap[4];
        this.stylesComboBoxes = (JComboBox<String>[]) new JComboBox[]{p1PawnStyles, p2PawnStyles, p3PawnStyles, p4PawnStyles};
        this.difficultyComboBoxes = (JComboBox<String>[]) new JComboBox[]{c1DifficultyBox, c2DifficultyBox, c3DifficultyBox, c4DifficultyBox};
        this.nameFields = new JTextField[]{p1NameField, p2NameField, p3NameField, p4NameField};
        this.nameAndStyleHuman = new HashMap<>();
        this.difficultyAndStyleComp = new HashMap<>();
        
        this.defaultHumanPlayersModel = new DefaultComboBoxModel<>(humanPlayerAmount);
        this.defaultCompPlayersModel = new DefaultComboBoxModel<>(compPlayerAmount);
        this.maxHumanPlayers0Model = new DefaultComboBoxModel<>(maxPlayers0);
        this.maxHumanPlayers1Model = new DefaultComboBoxModel<>(maxPlayers1);
        this.maxHumanPlayers2Model = new DefaultComboBoxModel<>(maxPlayers2);
        this.maxHumanPlayers3Model = new DefaultComboBoxModel<>(maxPlayers3);
        this.restrictionHumanModels = (DefaultComboBoxModel<Integer>[]) new DefaultComboBoxModel[]{maxHumanPlayers3Model, maxHumanPlayers2Model, maxHumanPlayers1Model, maxHumanPlayers0Model};
        this.maxCompPlayers0Model = new DefaultComboBoxModel<>(maxPlayers0);
        this.maxCompPlayers1Model = new DefaultComboBoxModel<>(maxPlayers1);
        this.maxCompPlayers2Model = new DefaultComboBoxModel<>(maxPlayers2);
        this.maxCompPlayers3Model = new DefaultComboBoxModel<>(maxPlayers3);
        this.restrictionCompModels = (DefaultComboBoxModel<Integer>[]) new DefaultComboBoxModel[]{maxCompPlayers3Model, maxCompPlayers2Model, maxCompPlayers1Model, maxCompPlayers0Model};

        for (int i = 0; i < playerLabels.length; i++) {
            settingsPanel.add(playerLabels[i]);
        }

        settingsPanel.add(new JLabel("Pawn Styles"));
        for (int i = 0; i < stylesComboBoxes.length; i++) {
            settingsPanel.add(stylesComboBoxes[i]);
        }

        settingsPanel.add(new JLabel("Name (Press Enter to apply)"));
        for (int i = 0; i < nameFields.length; i++) {
            settingsPanel.add(nameFields[i]);
        }

        settingsPanel.add(new JLabel("CPU Difficulty"));
        for (int i = 0; i < difficultyComboBoxes.length; i++) {
            settingsPanel.add(difficultyComboBoxes[i]);
        }

        settingsPanel.add(changeGameSettingsButton);

        applyChangesButton = new JButton("Apply Changes");
        applyChangesButton.addActionListener(this);
        settingsButtonPanel.add(applyChangesButton);

        cancelChangesButton = new JButton("Cancel Changes");
        cancelChangesButton.addActionListener(this);
        settingsButtonPanel.add(cancelChangesButton);

        rulesLabel = new JLabel(rulesText);
        rulesPanel.add(rulesLabel);

        startGameButton = new JButton("Start Malefiz!");
        startGameButton.addActionListener(this);
        bottomButtonPanel.add(startGameButton);

        cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		bottomButtonPanel.add(cancelButton);
        		
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.white);
        getContentPane().add(amountsPanel);
        getContentPane().add(Box.createRigidArea(new Dimension(0, 10)));
    	getContentPane().add(settingsPanel);
        getContentPane().add(settingsButtonPanel);
        getContentPane().add(rulesPanel);
        getContentPane().add(bottomButtonPanel);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // Gets the current ControllerUI instance.
    public ControllerUI getRoot() {
    	return this.root;
    }
    
    public void actionPerformed(ActionEvent actEvent) {
		Object selected = actEvent.getSource();

        // Select human players
        if (selected.equals(amtHumanPlayers)) {
            setHumanPlayersAmts((Integer)amtHumanPlayers.getSelectedItem());
            checkPlayers(humanPlayers, compPlayers);
        }

        // Select computer players
        if (selected.equals(amtCompPlayers)) {
            setCompPlayersAmts((Integer)amtCompPlayers.getSelectedItem());
            checkPlayers(humanPlayers, compPlayers);
        }

        // Selections for player pawn styles
        if (selected.equals(p1PawnStyles)) {
            setPlayerPawnStyle(1, (String)p1PawnStyles.getSelectedItem());
        }

        if (selected.equals(p2PawnStyles)) {
            setPlayerPawnStyle(2, (String)p2PawnStyles.getSelectedItem());
        }

        if (selected.equals(p3PawnStyles)) {
            setPlayerPawnStyle(3, (String)p3PawnStyles.getSelectedItem());
        }

        if (selected.equals(p4PawnStyles)) {
            setPlayerPawnStyle(4, (String)p4PawnStyles.getSelectedItem());
        }

        // Selections for computer difficulties
        if (selected.equals(c1DifficultyBox)) {
            setCPUDifficulty(1, (String)c1DifficultyBox.getSelectedItem());

            if (this.c1TempDifficulty.equals("Easy")) {
                c1Difficulty = Difficulty.EASY;
            }

            if (this.c1TempDifficulty.equals("Hard")) {
                c1Difficulty = Difficulty.HARD;
            }
        }

        if (selected.equals(c2DifficultyBox)) {
            setCPUDifficulty(2, (String)c2DifficultyBox.getSelectedItem());

            if (this.c2TempDifficulty.equals("Easy")) {
                c2Difficulty = Difficulty.EASY;
            }

            if (this.c2TempDifficulty.equals("Hard")) {
                c2Difficulty = Difficulty.HARD;
            }
        }

        if (selected.equals(c3DifficultyBox)) {
            setCPUDifficulty(3, (String)c3DifficultyBox.getSelectedItem());

            if (this.c3TempDifficulty.equals("Easy")) {
                c3Difficulty = Difficulty.EASY;
            }

            if (this.c3TempDifficulty.equals("Hard")) {
                c3Difficulty = Difficulty.HARD;
            }
        }

        if (selected.equals(c4DifficultyBox)) {
            setCPUDifficulty(4, (String)c4DifficultyBox.getSelectedItem());

            if (this.c4TempDifficulty.equals("Easy")) {
                c4Difficulty = Difficulty.EASY;
            }

            if (this.c4TempDifficulty.equals("Hard")) {
                c4Difficulty = Difficulty.HARD;
            }
        }

        // Changing player names
        if (selected.equals(p1NameField)) {
            setPlayerName(1, p1NameField.getText());
        }

        if (selected.equals(p2NameField)) {
            setPlayerName(2, p2NameField.getText());
        }

        if (selected.equals(p3NameField)) {
            setPlayerName(3, p3NameField.getText());
        }

        if (selected.equals(p4NameField)) {
            setPlayerName(4, p4NameField.getText());
        }

        // Creates a new instance of GameSettingsWindow
		if (selected.equals(changeGameSettingsButton)) {
            GameSettingsWindow demo = new GameSettingsWindow(5, 5, this);
        }

        // Applies the changes with hashmaps
        if (selected.equals(applyChangesButton)) {   
            String[] tempPawnStyles = new String[4];
            String[] tempPlayerNames = new String[4];
            Difficulty[] tempDifficulties = new Difficulty[4];
            //booleans to check if any styles chosen or names typed is the same.
            boolean a, b, c, d, e, f, g, h;

            tempPawnStyles = new String[]{this.p1PawnStyle, this.p2PawnStyle, this.p3PawnStyle, this.p4PawnStyle};
            tempPlayerNames = new String[]{this.p1Name, this.p2Name, this.p3Name, this.p4Name};
            tempDifficulties = new Difficulty[]{this.c1Difficulty, this.c2Difficulty, this.c3Difficulty, this.c4Difficulty};

            // Checks for null values which indicates that a value hasn't been altered. This loop changes all unaltered values to a default.
            for (int count = 0; count < 4; count++) {
                if (tempPawnStyles[count] == null) {
                    setPlayerPawnStyle(count + 1, pawnStyles[count]);
                }

                if (tempPlayerNames[count] == null || tempPlayerNames[count].equals("")) {
                    setPlayerName(count + 1, defaultPlayerNames[count]);
                }

                if (tempDifficulties[count] == null) {
                    tempDifficulties[count] = Difficulty.EASY;
                }
            }

            // assigning booleans
            a = (this.p1PawnStyle.equals(this.p2PawnStyle) || this.p1PawnStyle.equals(this.p3PawnStyle) || this.p1PawnStyle.equals(this.p4PawnStyle));
            b = (this.p2PawnStyle.equals(this.p1PawnStyle) || this.p2PawnStyle.equals(this.p3PawnStyle) || this.p2PawnStyle.equals(this.p4PawnStyle));
            c = (this.p3PawnStyle.equals(this.p1PawnStyle) || this.p3PawnStyle.equals(this.p2PawnStyle) || this.p3PawnStyle.equals(this.p4PawnStyle));
            d = (this.p4PawnStyle.equals(this.p1PawnStyle) || this.p4PawnStyle.equals(this.p2PawnStyle) || this.p4PawnStyle.equals(this.p3PawnStyle));
            e = (this.p1Name.equals(this.p2Name) || this.p1Name.equals(this.p3Name) || this.p1PawnStyle.equals(this.p4Name));
            f = (this.p2Name.equals(this.p1Name) || this.p2Name.equals(this.p3Name) || this.p2PawnStyle.equals(this.p4Name));
            g = (this.p3Name.equals(this.p1Name) || this.p3Name.equals(this.p2Name) || this.p3PawnStyle.equals(this.p4Name));
            h = (this.p4Name.equals(this.p1Name) || this.p4Name.equals(this.p2Name) || this.p4PawnStyle.equals(this.p3Name));

            // Check if a player's pawn styles equals another player's pawn style.
            if (a || b || c || d) {
                JOptionPane.showMessageDialog(this, "Two players cannot have the same pawn style selected!");
                return;
            }

            if (e || f || g || h) {
                JOptionPane.showMessageDialog(this, "Two players cannot have the same name!");
                return;
            }

            // makes new string arrays for any changes.
            tempPawnStyles = new String[]{this.p1PawnStyle, this.p2PawnStyle, this.p3PawnStyle, this.p4PawnStyle};
            tempPlayerNames = new String[]{this.p1Name, this.p2Name, this.p3Name, this.p4Name};

            // loop to change textfields to default human player names if left empty.
            for (int humanCount = 0; humanCount < humanPlayers; humanCount++) {
                nameFields[humanCount].setText(tempPlayerNames[humanCount]);
            }

            // int value to keep track of how many humans are iterated.
            int humanIterations = 0;
            // Adds human data to appropriate HashMap element in an array of HashMaps
            for (int i = 0; i < humanPlayers; i++) {
                this.listHumanPlayers[i] = new HashMap<String, String>();
                this.listHumanPlayers[i].put(tempPlayerNames[i], tempPawnStyles[i]);
                humanIterations++;
            }

            // Starts at humanIterations to allow CPU's to be added after humans.
            for (int i = humanIterations; i < humanPlayers + compPlayers; i++) {
                this.listCompPlayers[i - humanIterations] = new HashMap<String, Difficulty>();
                this.listCompPlayers[i - humanIterations].put(tempPawnStyles[i], tempDifficulties[i - humanIterations]);
            }

            // Adds the HashMap element from array of HashMaps to a overall HashMap to hold final data.
            for (int i = 0; i < this.listHumanPlayers.length; i++) {
                if (this.listHumanPlayers[i] != null) {
                    for (String nameKey : listHumanPlayers[i].keySet()) {
                        this.nameAndStyleHuman.put(nameKey, this.listHumanPlayers[i].get(nameKey));
                    }
                }
            }

            // Same for loop in line 518 for computer players.
            for (int i = 0; i < this.listCompPlayers.length; i++) {
                if (this.listCompPlayers[i] != null) {
                    for (String nameKey : listCompPlayers[i].keySet()) {
                        this.difficultyAndStyleComp.put(nameKey, this.listCompPlayers[i].get(nameKey));
                    }
                }
            }

            // Disables all fields to prevent changes after apply changes is pressed.
            for (int i = 0; i < 4; i++) {
                amtHumanPlayers.setEnabled(false);
                amtCompPlayers.setEnabled(false);
                stylesComboBoxes[i].setEnabled(false);
                difficultyComboBoxes[i].setEnabled(false);
                nameFields[i].setEnabled(false);
            }

        }

        if (selected.equals(cancelChangesButton)) {
            // These variables reinitializes the appropriate HashMaps for resetting.
            this.listHumanPlayers = (HashMap<String, String>[]) new HashMap[4];
            this.listCompPlayers = (HashMap<String, Difficulty>[]) new HashMap[4];
            this.nameAndStyleHuman = new HashMap<>();
            this.difficultyAndStyleComp = new HashMap<>();

            // Reinitializes the components to default and enabled to allow more changes.
            amtHumanPlayers.setEnabled(true);
            amtCompPlayers.setEnabled(true);
            amtHumanPlayers = new JComboBox<>(humanPlayerAmount);
            amtCompPlayers = new JComboBox<>(compPlayerAmount);
            amtHumanPlayers.setSelectedIndex(0);
            amtCompPlayers.setSelectedIndex(0);
            for (int i = 0; i < 4; i++) {
                stylesComboBoxes[i].setSelectedIndex(i);
                difficultyComboBoxes[i].setSelectedIndex(0);
                nameFields[i].setText("");
            }
            checkPlayers(humanPlayers, compPlayers);
        }

        if (selected.equals(startGameButton)) {
            boolean styleNulls = (this.p1PawnStyle == null && this.p2PawnStyle == null && this.p3PawnStyle == null && this.p4PawnStyle == null);
            boolean playerLimits = (humanPlayers + compPlayers > 4 || humanPlayers + compPlayers < 2);
            // boolean nameNulls = (this.p1Name == null && this.p2Name == null && this.p3Name == null && this.p4Name == null);
            // boolean difficultyNulls = (this.c1Difficulty == null && this.c2Difficulty == null && this.c3Difficulty == null && this.c4Difficulty == null);
            // Check to see if the player requirement is met
            if (playerLimits) {
                JOptionPane.showMessageDialog(this, "Number of human and computer players must be 2 - 4 players!");
                return;
            }

            // Checks if all pawn styles is null, which means that those pawnstyle strings were not changed from apply changes button.
            if (styleNulls) {
                JOptionPane.showMessageDialog(this, "Apply Changes button must have been pressed before starting Malefiz!");
                return;
            }

            if (!playerLimits && !styleNulls) {
                MalefizGame newGame = new MalefizGame(nameAndStyleHuman, null, "white", false, "track1");
                MalefizGameUI newGameUI = new MalefizGameUI(newGame);
                this.dispose();
            }

            else {
                this.dispose();
            }

        }

        if (selected.equals(cancelButton)) {
        	getRoot().setVisible(true);
            this.dispose();
        }

	}

    // focusgained was necessary because of FocusListener
    public void focusGained(FocusEvent focusEvent) {}

    // Allows for setting the appropriate string variable in the text fields whenever you click off of it instead of only pressing enter.
    public void focusLost(FocusEvent focusEvent) {
        Object inFocus = focusEvent.getSource();

        if (inFocus.equals(p1NameField)) {
            setPlayerName(1, p1NameField.getText());
        }

        if (inFocus.equals(p2NameField)) {
            setPlayerName(2, p2NameField.getText());
        }

        if (inFocus.equals(p3NameField)) {
            setPlayerName(3, p3NameField.getText());
        }

        if (inFocus.equals(p4NameField)) {
            setPlayerName(4, p4NameField.getText());
        }
    }

    // setter for humanplayers amounts
    public void setHumanPlayersAmts (Integer humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    // setter for computer players amounts
    public void setCompPlayersAmts (Integer compPlayers) {
        this.compPlayers = compPlayers;
    }

    // setter for pawn styles of the player called.
    public void setPlayerPawnStyle (int playerNumber, String pawnStyle) {
        if (playerNumber == 1) {
            this.p1PawnStyle = pawnStyle;
        }

        if (playerNumber == 2) {
            this.p2PawnStyle = pawnStyle;
        }

        if (playerNumber == 3) {
            this.p3PawnStyle = pawnStyle;
        }

        if (playerNumber == 4) {
            this.p4PawnStyle = pawnStyle;
        }
    }

    // setter for difficulty of the computer called.
    public void setCPUDifficulty (int cpuNumber, String difficulty) {
        if (cpuNumber == 1) {
            this.c1TempDifficulty = difficulty;
        }

        if (cpuNumber == 2) {
            this.c2TempDifficulty = difficulty;
        }

        if (cpuNumber == 3) {
            this.c3TempDifficulty = difficulty;
        }

        if (cpuNumber == 4) {
            this.c4TempDifficulty = difficulty;
        }

    }

    // setter for name of the human player called.
    public void setPlayerName (int playerNumber, String name) {
        if (playerNumber == 1) {
            this.p1Name = name;
        }

        if (playerNumber == 2) {
            this.p2Name = name;
        }

        if (playerNumber == 3) {
            this.p3Name = name;
        }

        if (playerNumber == 4) {
            this.p4Name = name;
        }
    }

    // setter for the boardcolor variable. used in GameSettingsWindow.java
    public void setBoardColor (String boardColor) {
		this.boardColor = boardColor;
	}

    public String getBoardColor() {
		return this.boardColor;
	}

    // setter for tutorial mode. used in GameSettingsWindow.java
    public void setTutorialStatus (Boolean tutorialOn) {
		this.tutorialOn = tutorialOn;
	}

    public Boolean getTutorialStatus() {
        return this.tutorialOn;
    }

    // setter for music track. used in GameSettingsWindow.java
    public void setMusicTrack (String currentMusicTrack) {
		this.currentMusicTrack = currentMusicTrack;
	}

    public String getMusicTrack () {
        return this.currentMusicTrack;
    }

    // Method to check the condition of how many human players and computer players.
    public void checkPlayers (int humans, int computers) {
        // removes the actionlistener to prevent data value changes as this method changes labels dynamically.
        // when a setSelectedIndex on a JComboBox is called for example, it triggers the actionlistener which would change the value.
        amtHumanPlayers.removeActionListener(this);
        amtCompPlayers.removeActionListener(this);

        // Disables all boxes at the start to allow easy enabling for conditions.
        for (int j = 0; j < 4; j++) {
            stylesComboBoxes[j].setEnabled(false);
            difficultyComboBoxes[j].setEnabled(false);
            nameFields[j].setEnabled(false);
            nameFields[j].setText("");
        }

        // enables pawn styles jcomboboxes depending on the total number of humans and computers
        for (int b = 0; b < humans + computers; b++) {
            stylesComboBoxes[b].setEnabled(true);            
        }

        // If humans is selected to be zero, this makes sure that compplayers combobox always has full selection of 0 - 3 to prevent being stuck
        if (humans == 0) {
            amtCompPlayers.setModel(defaultCompPlayersModel);
            amtCompPlayers.setSelectedItem(computers);
        }

        // Sets the name field of each human player to be blank to allow typing into the field to change name.
        // a small known bug which is that it overwrites a user inputted name in the text field.
        for (int h = 0; h < humans; h++) {
            nameFields[h].setEnabled(true);
            nameFields[h].setText("");
            
            amtCompPlayers.setModel(restrictionCompModels[h]);
            amtCompPlayers.setSelectedItem(computers);
        }

        if (computers == 0) {
            amtHumanPlayers.setModel(defaultHumanPlayersModel);
            amtHumanPlayers.setSelectedItem(humans);
        }

        for (int c = 0; c < computers; c++) {
            difficultyComboBoxes[c + humans].setEnabled(true);
            nameFields[c + humans].setText(tempCPUNames[c]);

            amtHumanPlayers.setModel(restrictionHumanModels[c]);
            amtHumanPlayers.setSelectedItem(humans);
            
        }
        amtCompPlayers.addActionListener(this);
        amtHumanPlayers.addActionListener(this);
    }
}