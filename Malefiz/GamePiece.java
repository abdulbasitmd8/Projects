/**
 * Abstract class representing a generic movable game piece
 * @author Cassandra Clowe Coish
 * @version 2021/03/03
 *
 */
public abstract class GamePiece implements java.io.Serializable {
	
	private Space space;
	private Player player;
	
//Getter method to return the space occupied by a Game Piece.
	public Space getSpace() {
		return space;
	}

//Setter method to update the space occupied by a Game Piece. 
	public void setSpace(Space space) {
		this.space = space;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

//@param space: the space object the pawn should move to 
//move method to move the game piece on a board to a specified space. 
//The space to move to is passed as parameter.
	public void move(Space space) {
		{
			//clear current space and move this piece to the space given
			getSpace().setContent(null);
			setSpace(space);
			space.setContent(this);
		}
	}
}
