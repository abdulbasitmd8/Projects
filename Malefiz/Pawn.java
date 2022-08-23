/*
 * Class representing Pawns on the Board. 
 * Each pawn has its own color and parent player.
 */
public class Pawn extends GamePiece implements java.io.Serializable {

	private String color;
	private Space homeSpace;
	private Player player;

	//Constructor method. Each pawn has its own color and player.
	public Pawn(String color, Player player) {
		this.color = color;
		this.player = player;
	}

	//Getter method used to return the player that pawn belongs to.
	public Player getPlayer() {
		return player;
	}

	//Setter method to updater the pawn's player
	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	//Setter method to assign homespace to a pawn
	public void setHomeSpace(Space homeSpace) {
		this.homeSpace = homeSpace;
	}
	
	//Getter method to return a pawn's homespace
	public Space getHomeSpace() {
		return homeSpace;
	}

	//Method to return pawn to its own homespace 
	public void returnHome()
	{
		move(getHomeSpace());
	}
}
