/*
 * Class representing each space on the board. 
 * It also hold the spaces that are on the
 * top, bottom, left, and right of a space.
 * This class is used by the Board class and BoardUI class
 * to traverse the barricade and to setup the spaces on
 * the Board.
 */
public class Space implements java.io.Serializable
{
	private int x, y;//Coordinates of a space on the board
	
	//Variables to save spaces to the top,bottom,left,and right of a space.
    private Space up;
    private Space down;
    private Space right;
    private Space left;
    //A generic object of GamePiece class.
    private GamePiece content;

 //Constructor method to initialise a space with its coordinates.  
    public Space(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    }
    
    public Space()
    {
    }

//Getter method to return Y coordinate of a Space.    
	public int getY() {
		return y;
	}


//Getter method to return the X coordinate of a space.
	public int getX() {
		return x;
	}

//Setter method to update the X coordinate of a space.	
	public void setX(int x) {
		this.x = x;
	}

//Setter method to update the Y coordinate of a space.	
	public void setY(int y) {
		this.y = y;
	}

//Setter method to update the space above.
	public void setUp(Space upSpace){
       this.up = upSpace;
    }
   
//Setter method to update the space below.	
    public void setDown(Space downSpace){       
       this.down = downSpace;        
    }
 
//Setter methods to update the space to the right.    
    public void setRight(Space rightSpace){
        this.right = rightSpace;
    }
    
//Setter method to update the space to the left.    
    public void setLeft(Space leftSpace){
        this.left = leftSpace;
    }
    
//Setter method to update the GamePiece object.    
    public void setContent(GamePiece gamepiece){
        this.content = gamepiece;
    }

//Setter method to mark a space as empty.    
    public void setEmpty(){
        this.content = null;
    }
    
//Getter method to return the space to the left.
    public Space getLeft(){
        return this.left;
    }
 
//Getter method to return the space to the right.    
    public Space getRight(){
        return this.right;
    }
    
 //Getter method to return the space above.
    public Space getUp(){
        return this.up;
    }
    
//Getter method to return the space below.
    public Space getDown(){
        return this.down;
    }
    
//Getter method to return the GamePiece object occupying a space.    
    public GamePiece getContent(){
        return this.content;
    }
    
}
