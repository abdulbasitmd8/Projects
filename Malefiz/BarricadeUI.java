/*
 * Class representing a Barricade on the Game Board.
 * Each barricade is implemented with a JButton.
 * Every object of BarricadeUI class takes an object of Barricade class as parameter.
 */
import java.awt.Color;
import javax.swing.*;

public class BarricadeUI extends JButton {
	private final Barricade barricade;
	private SpaceUI spaceUI;

//Constructor method that takes object of Barricade class as parameter.	
	public BarricadeUI(Barricade barricade, ImageIcon icon){
		this.barricade = barricade;
		this.setBackground(Color.BLACK);
		this.setIcon(icon);
		this.setDisabledIcon(icon);
		this.setBorder(null);
	}
	
//Getter method to return the space occupied by a barricade on the game board.
	public SpaceUI getSpaceUI() {
		return spaceUI;
	}
	
//Setter method to update the space on the game board for a barricade.
	public void setSpaceUI(SpaceUI spaceUI) {
		this.spaceUI = spaceUI;
		if (spaceUI != null) {
			spaceUI.add(this);
		}
	}

//Getter method to return an object of class Barricade.	
	public Barricade getBarricade() {
		return barricade;
	}
	
/*
 * Move method to displace a barricade to a given space on the game board.
 * The SpaceUI object is passed as parameter.
 */
	public void move(SpaceUI spaceUI) {
        SpaceUI temp = getSpaceUI();
		getSpaceUI().remove(this);
		temp.getSpace().setContent(null);
		setSpaceUI(spaceUI);
		spaceUI.setBarricadeUI(this);
		
		getBarricade().move(getSpaceUI().getSpace());
		
		
	}
}
