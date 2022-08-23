/**
 * Subclass of JPanel representing a space on the Malefiz board.
 * The SpaceUI class represents physical spaces on the game
 * board.
 * @author Cassandra Clowe-Coish
 *
 */
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 * Subclass of JPanel representing a space on the Malefiz board
 * @author Cassandra Clowe-Coish
 *
 */
public class SpaceUI extends JButton implements java.io.Serializable{
	
	//Objects of classes Space,BarricadeUI,PawnUI are created for 
	//each space on the game board.

	private Space space;
	private BarricadeUI barricadeUI;
	private PawnUI pawnUI;

//Constructor method. It takes object of class Space as parameter. 	
	public SpaceUI(Space space)
	{
		this.space = space;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(true);
		this.setBackground(Color.white);
		this.setSize(40,40);
		this.setLayout(new FlowLayout());
		
	}

//Getter method to return PawnUI object on a Space.	
	public PawnUI getPawnUI() {
		return pawnUI;
	}

//Setter method to update the PawnUI on a Space.
	public void setPawnUI(PawnUI pawnUI) {
		this.pawnUI = pawnUI;
	}

//Getter method to return the space of a SpaceUI object.	
	public Space getSpace() {
		return space;
	}

//Setter method to update the space of a SpaceUI object.
	public void setSpace(Space space) {
		this.space = space;
	}

//Getter method to return the BarricadeUI of a SpaceUI object
//if a barricade is occupying said space.
	public BarricadeUI getBarricadeUI() {
		return barricadeUI;
	}
	
//Setter method to update the BarricadeUI of a SpaceUI object.	
	public void setBarricadeUI(BarricadeUI barricadeUI) {
		this.barricadeUI = barricadeUI;
	}
	
	
	public GamePiece getContentOfSpace() {
		// get the GamePiece associated with the space object, may be null
		return getSpace().getContent();
	}

}
