import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * The game of Pontoon. Two players take turns click buttons with numbers on them, the numbers get added to the total with each click. Whoever
 * brings the total to above 21, loses.
 * 
 * @author Abdul Shaji 
 * @version Pontoon 1.0
 */
public class Pontoon extends JFrame implements ActionListener
{
    // instance variables 
    private JFrame frame;
    private JPanel topPanel, bottomPanel;
    private JLabel infoLabel, scoreLabel;
    private JButton playButton;
    private JButton[] buttons;
    private Random rand; 
    private int[] numbers;
    private int score, player, decider;

    /**
     * Constructor for objects of class pontoon. It creates the frame, instructions,
     * score displaying title, new game buttons and all other grid buttons
     */
    public Pontoon()
    {
        frame = new JFrame("Pontoon");
        this.setTitle("Pontoon");
        infoLabel = new JLabel("   Keep the total below 22. Click New Game to Begin!    ");
        scoreLabel = new JLabel("Current: -  ");
        playButton = new JButton("New Game");
        playButton.setFocusable(false);
        playButton.addActionListener(s -> play());
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        
        
        topPanel.setLayout( new BorderLayout()); 
        bottomPanel.setLayout( new GridLayout(5,5));
        
       
        
        topPanel.add(playButton, BorderLayout.WEST);
        topPanel.add(infoLabel, BorderLayout.CENTER);
        topPanel.add(scoreLabel, BorderLayout.EAST);
        
        buttons = new JButton[25];
        for (int i = 0 ; i < 25; i++){
            buttons[i] = new JButton("-");
            int temp = i;
            buttons[i].addActionListener(s -> pushButton(temp));
            bottomPanel.add(buttons[i]);
            buttons[i].setEnabled(false);
            buttons[i].setFocusable(false);
            
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);
        
        this.setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /** 
     * 
     */
    public void play()
    { 
       score = 0; 
       player = 0;
       numbers = new int [25];
       
       
       rand = new Random();
       decider = rand.nextInt(2);
       player = player + decider;
       scoreLabel.setText("Current: " + score + "   ");
       if (decider == 0){
       infoLabel.setText("   Player 1's turn....");
       }
       else {
           infoLabel.setText("   Player 2's turn....");
        }
       for (int i = 0; i < 25; i++){
            
            numbers[i] = rand.nextInt(5) + 1;
            
    
            buttons[i].setText(String.valueOf(numbers[i]));
            buttons[i].setFocusable(false);
            buttons[i].setEnabled(true);
            buttons[i].setBackground(Color.white);
            
        } 
        
     
}
        public void pushButton(int i){
            
        if (player % 2 == 0  ){
            score = score + numbers[i];
            buttons[i].setEnabled(false);
            buttons[i].setBackground(Color.cyan);
            scoreLabel.setText("Current: " + score + "   ");
            infoLabel.setText("   Player 2's turn....");
            
        }
        else {
            score = score + numbers[i];
            buttons[i].setEnabled(false);
            buttons[i].setBackground(Color.orange);
            scoreLabel.setText("Current: " + score + "   ");
            infoLabel.setText("   Player 1's turn....");
            
        }
        player = player + 1;  
        if (score > 21){
           if (player % 2 == 0){
           infoLabel.setText("   Player 1 Wins! Well Done!!");
           for ( int x = 0; x < 25; x++){
               buttons[x].setEnabled(false);
            }
        }
           else {
           infoLabel.setText("   Player 2 Wins! Well Done!!");
           for ( int x = 0; x < 25; x++){
               buttons[x].setEnabled(false);
            }
        
    }
     
}
} 
    // method to override ActionListener interface
    public void actionPerformed(ActionEvent aevt)
	{
         
	}
}
