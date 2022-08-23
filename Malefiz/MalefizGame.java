import java.io.*;
import java.util.*;

/**
 * A class representing a game of Malefiz, including players, board, and die
 * @author Cassandra Clowe-Coish & Abdul Shaji
 * @date 2021/03/03
 *
 */


public class MalefizGame implements Serializable
{
	static HashMap<String, File> musicMap; //TODO Load in music files
	
	
	private final ArrayList<Player> players; //Arraylist of objects of class Player.

	private Boolean tutorialOn;
	private String music; //TODO this will later have type "File" and string will map to file pointer in static HashMap
	private Board board; //Object of class Board.
	
	//Constructor method. Takes parameters needed to initialise a board.	
	public MalefizGame(HashMap<String, String> nameAndColorHuman, HashMap<String, Difficulty> difficultyAndColorComp, String boardColor, Boolean tutorialOn, String music)
	{
		//create HumanPlayer objects for each human player with appropriate name and color
		players = new ArrayList<Player>(4);
		for (String nameKey : nameAndColorHuman.keySet()) {
			getPlayers().add(new HumanPlayer(nameKey, nameAndColorHuman.get(nameKey)));
		}
		//create ComputerPlayer objects for each computer player with appropriate difficulty and color
		if (difficultyAndColorComp != null){ // if the computer hashmap has entries in it
			for (String colorKey : difficultyAndColorComp.keySet()) {
			getPlayers().add(new ComputerPlayer(difficultyAndColorComp.get(colorKey).toString() + "Computer", colorKey, difficultyAndColorComp.get(colorKey)));
			}
		}
		
		this.board = new Board(getPlayers());

		this.tutorialOn = tutorialOn;
		this.music = music;
	}
	
	
	//Flag for tutorial mode.
	public Boolean getTutorialOn() {
		return tutorialOn;
	}

	//Setter method for updating tutorial mode.
	public void setTutorialOn(Boolean tutorialOn) {
		this.tutorialOn = tutorialOn;
	}

	//Getter method to return music.
	public String getMusic() {
		return music;
	}

	//Setter method to update music.
	public void setMusic(String music) {
		this.music = music;
	}

	//Getter method to return object of class Board. 
	public Board getBoard() {
		return board;
	}

	//Setter method to update object of class Board. 
	public void setBoard(Board board) {
		this.board = board;
	}

	//Getter method that returns an arraylist of objects of class Player.
	public ArrayList<Player> getPlayers() {
		return players;
	}

}
