/**
 * A subclass of JPanel representing a pawn home UI on the game board.
 * An object of this class takes the backend pawnhome representation as a parameter. 
 * @author Cassandra Clowe-Coish
 *
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class PawnHomeUI extends JPanel implements java.io.Serializable {
	private PawnHome pawnHome;
	private final ArrayList<SpaceUI> spaceUIs;
	
//Constructor method that takes object of PawnHome class as parameter.
	public PawnHomeUI(PawnHome pawnHome)
	{
		this.pawnHome = pawnHome;
		JLabel homeLabel = new JLabel(pawnHome.getPlayer().getName());
		this.setLayout(new GridLayout(2,3));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.spaceUIs = new ArrayList<>();
		
		int count = 0;
		//For loop to add the pawn spaces to the home
		for (Space space : pawnHome.getHomeSpaces()) {
			if (count == 4) {
			    SpaceUI temp = new SpaceUI(new Space());
				this.add(temp);
				temp.setOpaque(false);
				temp.setBorderPainted(false);
				temp.setEnabled(false);
			}
			SpaceUI homespace = new SpaceUI(space);
			
			homespace.setText(".");
			homespace.setBorder(null);
			spaceUIs.add(homespace);
			this.add(homespace);
			count++;
		}
		this.add(homeLabel);
	}
	
//Getter method to return the pawn homespace.
	public PawnHome getPawnHome() {
		return pawnHome;
	}

//Setter method to set a pawn's homespace.	
	public void setPawnHome(PawnHome pawnHome) {
		this.pawnHome = pawnHome;
	}

//Getter method to retur arraylist of SpaceUI representations of pawns in homespace. 	
	public ArrayList<SpaceUI> getSpaceUIs() {
		return spaceUIs;
	}

}
