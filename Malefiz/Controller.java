/**
 * Class which keeps track of active game. 
 * It does so creating an object of class MalefizGame. 
 * @author Cassandra Clowe-Coish
 * @version 2021/03/03
 */

import java.util.HashMap;
import java.io.*;
public class Controller {
	
	private MalefizGame activeGame; //Object of MalefizGame class.
	
//Constructor method.	
	public Controller()
	{
		
	}

//Getter method to return the activeGame object of MalefizGame class.  
	public MalefizGame getActiveGame() {
		return activeGame;
	}

//Setter method to update the activeGame object of MalefizGame class. 
	public void setActiveGame(MalefizGame activeGame) {
		this.activeGame = activeGame;
	}

	public void createNewGame(HashMap<String, String> nameAndColorHuman, HashMap<String, Difficulty> difficultyAndColorComp, String boardColor, Boolean tutorialOn, String music)
	{
		setActiveGame(new MalefizGame(nameAndColorHuman, difficultyAndColorComp,  boardColor, tutorialOn, music));
	}
	
	//TODO implement functionality for loading a game from disk
	
}

