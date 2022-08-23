/*
 * A test class that was used to run the main window of the game containing the board
 * during the testing phase for troubleshooting and preview. 
 */


import java.util.HashMap;

public class MGameUITest {

	public static void main(String[] args) {
		HashMap<String, String> humanTest = new HashMap<>();
		
		humanTest.put("Car", "Car");
		humanTest.put("Celestial Thing", "Celestial Thing");
		humanTest.put("Snake", "Snake");
		humanTest.put("Dart", "Dart");
		
		MalefizGame mgame = new MalefizGame(humanTest, null, "white", false, "track1");
		MalefizGameUI mUI = new MalefizGameUI(mgame);
	}

}
