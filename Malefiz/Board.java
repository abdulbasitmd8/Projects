import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.*;

/**
 * A class representing the board, which contains a 2D array of space objects
 * @author Abdul Shaji & Cassandra Clowe-Coish
 *
 */
public class Board implements Serializable {
	private static final int NUMROWS = 14;
	private static final int NUMCOLS = 17;
	private static final Integer[] upSpaces = {2,6,10,14};
	
	private Die die; //object of die class 
	
	public static int getNumrows() {
		return NUMROWS;
	}
	
	public static int getNumcols() {
		return NUMCOLS;
	}
	
	private Space[][] spaces; //object of Space class
	private final ArrayList<Player> players;
	private ArrayList<PawnHome> homes;
	private ArrayList<Space> tempSpaces;
	
	private transient Iterator<Player> playerIterator;
	private Player currentPlayer; //current player
	
	//Initializes the board
	public Board(ArrayList<Player> players) {
		this.players = players;
		this.spaces = new Space[getNumrows()][NUMCOLS];
		
		int[] fullrows = {0,2,10,12};
		
		for (int i : fullrows) {
			makeGapRow(i,0,1);
		}
		
		for (int i=4; i<10; i+=2) {
			makeGapRow(i, i-2, 1); //rows 4,6,8 follow the pattern chop=i-2, step=1
		}
		
		int[] pattern4rows = {1, 3, 7}; //rows which follow the pattern chop = i-1, step=4
		for (int i : pattern4rows) {
			makeGapRow(i, i-1, 4);
		}
		
		makeGapRow(5, 4, 8);
		makeGapRow(9, 8, 8);
		makeGapRow(11, 0, 16);
		makeGapRow(13, 8, 8);
		
		setSpaceDirections();
		
		//builds the PawnHome for each player
		this.homes = new ArrayList<>(4);
		List<Integer> upSpacesList = Arrays.asList(upSpaces);
		Iterator<Integer> upSpacesIter = upSpacesList.iterator();
		
		for (Player player: players) {
			homes.add(new PawnHome(player, getSpaces()[0][upSpacesIter.next()]));// set the spaces that the pawnhomes link to
		
		this.die = new Die();
			
		}
		
	}
	
	//Getter and setter for the die
	public Die getDie() {
		return die;
	}

	public void setDie(Die die) {
		this.die = die;
	}

	public ArrayList<PawnHome> getHomes() {
		return homes;
	}

	public void setHomes(ArrayList<PawnHome> homes) {
		this.homes = homes;
	}

	public Space[][] getSpaces() {
		return spaces;
	}

	public void setSpaces(Space[][] spaces) {
		this.spaces = spaces;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	//gets the pawn for the particular player
	public Pawn[] getPawnsOfPlayer(Player player) {
		Pawn[] pawns = null;
		for (Player listPlayer : getPlayers()) {
			if (listPlayer.equals(player)){
				pawns = listPlayer.getPawns();
			}
		}
		return pawns;
	}
		
	/**
	 * method to make rows with regular gaps in between and a "chop" at the beginning and end
	 * 
	 */
	private void makeGapRow(int index, int chopped, int step) {
		for (int j=chopped; j<NUMCOLS-chopped; j+=step) {
			getSpaces()[index][j] = new Space(j, index);
			switch(index) {
			case 2:{
				switch(j) {
					case 0,4,8,12,16:
						placeBarricade(index,j);
						break;
				}
				break;
			}
			case 6:{
				switch(j) {
					case 6,10:{
						placeBarricade(index, j);
						break;
					}
				}
				break;
			}
			case 8,9,10,12:{
				switch(j) {
					case 8:{
						placeBarricade(index, j);
						break;
					}
				}
				break;
			}
			}
		}
	}
	
	/**
	 * Sets all the adjacent spaces of each space on the board	
	 */
	private void setSpaceDirections() {
		for (int i=0; i<getNumrows(); i++) {
			for (int j=0; j<NUMCOLS; j++) {
				if (getSpaces()[i][j] != null) {
					if (i!=0) { //if we are in the first row no space has a down
						if (getSpaces()[i-1][j] != null) {
							getSpaces()[i][j].setDown(getSpaces()[i-1][j]); // if the array element in this column in the previous row is not null, set it as the down attribute of this space
						}
					}
					if (i!=13) { // if we are in top row, no space has up
						if (getSpaces()[i+1][j] != null) {
							getSpaces()[i][j].setUp(getSpaces()[i+1][j]); // if the array element in this column in the row above is not null, set it as this spaces up attribute
						}
					}
					if (j!=0) { // if we are in leftmost column, no space has left
						if (getSpaces()[i][j-1] != null) {
							getSpaces()[i][j].setLeft(getSpaces()[i][j-1]); // if array element in this row in the previous column not null, set it as this space's left attribute
						}
					}
					if (j!=16) { // if we are in rightmost column, no space has right.
						if (getSpaces()[i][j+1] != null) {
							getSpaces()[i][j].setRight(getSpaces()[i][j+1]); // if the array element in this row in the next column is not null, set it as this space's right attribute
						}
					}
				}
			}
		}
	}
	
	
	private void placeBarricade(int i, int j) {
		getSpaces()[i][j].setContent(new Barricade());
	}
	
	public Space[] moveCalculate(Space space, int rolledNumber)
	{
	    //arraylist initialized here to have values stored in it from recursiveCalc method
	    tempSpaces = new ArrayList<>();
	    
	    //called to get possible final spaces
	    recursiveCalc(space, rolledNumber, space);
	    
	    //remove any spaces that contain pawns of the current player.
	    Iterator<Space> spaceIt = tempSpaces.iterator();
	    while (spaceIt.hasNext()) {
	    	Space s = spaceIt.next();
	    	if (s.getContent() instanceof Pawn) {
	    		Pawn pawn = (Pawn) s.getContent();
	    		if (pawn.getPlayer().equals(getCurrentPlayer())) {
	    			spaceIt.remove();
	    		}
	    	}
	    }
	    
	    //creating the space array to return in the end
	    Space[] finalSpaces = new Space[tempSpaces.size()];

	    //loop to take all elements from the arraylist and send them all to array to be returned
	    for (int i = 0; i < tempSpaces.size(); i++){
		    finalSpaces[i] = tempSpaces.get(i);
	    }

	    return finalSpaces;

	 }

	/* To recursively find the possible final spaces and store them into the arraylist
	*  parameters are 2 space's and one int value
	*/

	public void recursiveCalc(Space space, int rolled, Space space2)
    {
        //if the method reaches a point where at a given space, the number rolled on the dice is 0, then the space is added to the arraylist
        if (rolled == 0){
            tempSpaces.add(space);
            return;  //ending the recursive call without going any further
        }

        // space2 is also passed, to make sure that the recursive call does not repeat spaces while traversing, and it traverses every direction possible
        if (space.getLeft() != null && space.getLeft() != space2 && (! (space.getLeft().getContent() instanceof Barricade) || (space.getLeft().getContent() instanceof Barricade && rolled == 1))) {
        	recursiveCalc(space.getLeft(), rolled - 1, space);
        }

        if (space.getRight() != null && space.getRight() != space2 && (! (space.getRight().getContent() instanceof Barricade) || (space.getRight().getContent() instanceof Barricade && rolled == 1))) {
            recursiveCalc(space.getRight(), rolled - 1, space);
        }

        if (space.getDown() != null   && space.getDown() != space2 && (! (space.getDown().getContent() instanceof Barricade) || (space.getDown().getContent() instanceof Barricade && rolled == 1))) {
            recursiveCalc(space.getDown(), rolled - 1, space);
        }

        if (space.getUp() != null  && space.getUp() != space2 && (! (space.getUp().getContent() instanceof Barricade) || (space.getUp().getContent() instanceof Barricade && rolled == 1))) {
            recursiveCalc(space.getUp(), rolled - 1, space);
        }

        return; //ending the recursive call
      }

	public int rollDie() {
		return getDie().roll();
	}
	
	public void randomisePlayers() {
		Collections.shuffle(getPlayers());
		resetPlayerIterator();
	}
	
	public void resetPlayerIterator() {
		setPlayerIterator(getPlayers().iterator());
		setCurrentPlayer(getPlayerIterator().next());
	}
	
	public Iterator<Player> getPlayerIterator() {
		return playerIterator;
	}

	public void setPlayerIterator(Iterator<Player> playerIterator) {
		this.playerIterator = playerIterator;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void nextPlayer()
	{
		if (getPlayerIterator().hasNext()) {
			setCurrentPlayer(getPlayerIterator().next());
		}
		else resetPlayerIterator();
	}
}
