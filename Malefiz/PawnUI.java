/*
 * Class implementing the UI representation of Pawns on the game board.
 * Each element of Pawn UI class has its separate pawn object and 
 * spaceUI object representing its space on the game board.
 */
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.JButton;

public class PawnUI extends JButton implements java.io.Serializable {
    
	private Pawn pawn;
	private SpaceUI spaceUI;
	
//Constructor method. Each representation of the pawn on game board has its own pawn object and image icon.
	public PawnUI(Pawn pawn, ImageIcon icon) 
	{
		this.pawn = pawn;
		this.setIcon(icon);
		this.setDisabledIcon(icon);
		this.setSize(new Dimension(50, 50));
		this.setBorder(null);
		
	}

//getter mathod to return the pawn class representation.	
	public Pawn getPawn() {
		return pawn;
	}

//Setter method to update the pawn class representation.
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

//Getter method to return the SpaceUI representation of a pawn.
	public SpaceUI getSpaceUI() {
		return spaceUI;
	}

//Setter method to update the SpaceUI representation of a pawn.	
	public void setSpaceUI(SpaceUI spaceUI) {
		this.spaceUI = spaceUI;
		if (spaceUI != null) {
			spaceUI.add(this);
		}
	}

//Move method to move a pawn to the SpaceUI parameter. 
	public void move(SpaceUI spaceUI) {
		getSpaceUI().remove(this);
		getSpaceUI().setEnabled(true);
		getSpaceUI().setEnabled(false);
		getSpaceUI().setPawnUI(null);
		setSpaceUI(spaceUI);
		spaceUI.setPawnUI(this);
		
		
		getPawn().move(getSpaceUI().getSpace());
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(50,50);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(50,50);
	}
}
