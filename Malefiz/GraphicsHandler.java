import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//this class handles the images used in our main game board, for the pawns, Die and and they are stored in 2 hashmaps
public class GraphicsHandler {
	private final HashMap<String, ImageIcon> pawnMap; //pawns are stored with unique names/strings
	private final HashMap<Integer, ImageIcon> dieMap;//the die are stored paired with their respective no./int
	private final HashMap<String, ImageIcon> auxGraphicsMap;

	

	public GraphicsHandler() {
		pawnMap = new HashMap<>();
		dieMap = new HashMap<>();
		auxGraphicsMap = new HashMap<>();
		
		Image dieOne, dieTwo, dieThree, dieFour, dieFive, dieSix, //all the type Image variables used to hold the images
		pawnCar, pawnDart, pawnSnake, pawnCat, pawnPotato, pawnThing,
		barricade,
		finish;
		
		//respective dies being imported from the folder on disk
		try {
			dieOne = ImageIO.read(this.getClass().getResource("Die_Images/dieOne.png"));
			dieOne = dieOne.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			dieTwo = ImageIO.read(this.getClass().getResource("Die_Images/dieTwo.png"));
			dieTwo = dieTwo.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			dieThree = ImageIO.read(this.getClass().getResource("Die_Images/dieThree.png"));
			dieThree = dieThree.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			dieFour = ImageIO.read(this.getClass().getResource("Die_Images/dieFour.png"));
			dieFour = dieFour.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			dieFive = ImageIO.read(this.getClass().getResource("Die_Images/dieFive.png"));
			dieFive = dieFive.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			dieSix = ImageIO.read(this.getClass().getResource("Die_Images/dieSix.png"));
			dieSix = dieSix.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			
			dieMap.put(1, new ImageIcon(dieOne));
			dieMap.put(2, new ImageIcon(dieTwo));
			dieMap.put(3, new ImageIcon(dieThree));
			dieMap.put(4, new ImageIcon(dieFour));
			dieMap.put(5, new ImageIcon(dieFive));
			dieMap.put(6, new ImageIcon(dieSix));
		}
		catch(IOException e) {
			printGraphicsError();
		}
		
		//respective Pawn's being imported from the folder on disk

		try {
			pawnCar = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesCar.jpg"));
			pawnCar = pawnCar.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			pawnThing = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesCelestialThing.jpg"));
			pawnThing = pawnThing.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			pawnSnake = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesSnake.jpg"));
			pawnSnake = pawnSnake.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			pawnDart = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesDart.jpg"));
			pawnDart = pawnDart.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			pawnPotato = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesPotato.jpg"));
			pawnPotato = pawnPotato.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			pawnCat = ImageIO.read(this.getClass().getResource("/Pawn_Images/PawnPiecesGreenCat.jpg"));
			pawnCat = pawnCat.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			
			pawnMap.put("Car", new ImageIcon(pawnCar));
			pawnMap.put("Celestial Thing", new ImageIcon(pawnThing));
			pawnMap.put("Snake", new ImageIcon(pawnSnake));
			pawnMap.put("Dart", new ImageIcon(pawnDart));
			pawnMap.put("Potato", new ImageIcon(pawnPotato));
			pawnMap.put("Green Cat", new ImageIcon(pawnCat));
		}
		catch(IOException e) {
			printGraphicsError();
		}
		
		try {
			barricade = ImageIO.read(this.getClass().getResource("/Pawn_Images/Stop.jpg"));
			barricade = barricade.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			finish = ImageIO.read(this.getClass().getResource("/Pawn_Images/Fin.jpg"));
			finish = finish.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			
			auxGraphicsMap.put("Barricade", new ImageIcon(barricade));
			auxGraphicsMap.put("Finish", new ImageIcon(finish));
		}
		catch(IOException e) {
			printGraphicsError();
		}
		
	}

	public HashMap<String, ImageIcon> getAuxGraphicsMap() {
		return auxGraphicsMap;
	}

	//getters to retrieve the hashmaps 
	public HashMap<String, ImageIcon> getPawnMap() {
		return pawnMap;
	}

	public HashMap<Integer, ImageIcon> getDiemap() {
		return dieMap;
	}
	
	public void printGraphicsError() {
		System.out.println("Failed to load graphics. The image files may have been moved or deleted");
	}
}
