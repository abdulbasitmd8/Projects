import java.io.*;
import java.util.ArrayList;

/**
 * Abstract class representing a generic player of Malefiz
 * @author Cassandra Clowe-Coish
 * @version 2021/02/24
 *
 */
public abstract class Player implements Serializable {
	
	private String name;
	private Pawn[] pawns;

/*
* Constructor method. When an object of class Player is created, 
* the name and chosen pawn image of player are passed as parameters.
*/
	public Player(String name, String color)
	{
		this.name = name;
		this.pawns = new Pawn[5];
		for(int i=0; i<5; i++) {
			getPawns()[i] = new Pawn(color, this);
		}
	}
	
//Getter method to return name of a player.
	public String getName() {
		return name;
	}
	
//Setter method to update name of a player.	
	public void setName(String name) {
		this.name = name;
	}

//Method to return a list of pawns of a player.
	public Pawn[] getPawns() {
		return pawns;
	}

}
