/**
 * A class representing the die that players will roll
 * Uses a random number generator
 * @author Cassandra Clowe-Coish
 * @date 2021/02/24
 *
 */

import java.util.Random;
import java.io.*;

public class Die implements Serializable {

	private Random dieRandom;

//Constructor method that sets randomized die value to simulate die being rolled.
	public Die() {
		
		this.dieRandom = new Random();
	}
	
//Method that calculates random die value.
	public Random getDieRandom() {
		return dieRandom;
	}

//Method that returns rolled die value.
	public int roll() {
		return getDieRandom().nextInt(6) + 1;
	}
	
}
