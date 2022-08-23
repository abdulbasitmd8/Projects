/*
 * A class representing a Computer Player. Computer Player are implemented using 
 * the Player class. The name and chosen pawn image of the computer player are passed as parameters.
 */

//ComputerPlayer inherits Player
public class ComputerPlayer extends Player {
	

	private Difficulty difficulty; //ComputerPlayer's own object for difficulty chosen

	//constructor method that takes in the parameters below to initiate it 
	public ComputerPlayer(String name, String color, Difficulty difficulty) {
		super(name, color); //variable is taking in name, color and is stored using the object from parent's class player
		this.difficulty = difficulty;
	}
	
}
