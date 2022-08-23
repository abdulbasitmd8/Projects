/**
 * Class representing the place the pawns start in and get sent back to when captured.
 * A PawnHome object places the pawns of its associated player inside of it when instantiated.
 * @author Cassandra Clowe-Coish
 *
 */
public class PawnHome implements java.io.Serializable {
	private final Player player;
	
	private Space[] homespaces;
	
//Constructor method that takes Player Object and Space object as parameters.	
	public PawnHome(Player player, Space upSpace)
	{
		this.player = player;
		
		this.homespaces = new Space[5];
		
		//for loop to set up the attribute of each pawn home space to a place on the main board.
		for (int i=0; i<homespaces.length; i++) {
			homespaces[i] = new Space();
			homespaces[i].setUp(upSpace);
		}
		
		placePawnsInHomes(getPlayer().getPawns());
	}

//Getter method to return the player of a pawn.	
	public Player getPlayer() {
		return player;
	}

//Getter method to return a list of homespaces of a player's pawns.
	public Space[] getHomeSpaces() {
		return homespaces;
	}

//Setter method to update the homespaces of a player's pawns.	
	public void setHomeSpaces(Space[] homespaces) {
		this.homespaces = homespaces;
	}
	
//Method to place a player's pawns in their respective homespaces.
	private void placePawnsInHomes(Pawn[] pawns) {
		for (int i=0; i<5; i++) {
			getHomeSpaces()[i].setContent(pawns[i]);
			pawns[i].setHomeSpace(getHomeSpaces()[i]);
			pawns[i].setSpace(getHomeSpaces()[i]);
		}
	}
	
//Method to return a list of pawns of a player.
	public Pawn[] getPawnsOfPlayer() {
		return getPlayer().getPawns();
	}
}
