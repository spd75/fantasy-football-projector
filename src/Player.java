import java.util.Random;

/** Class for creating each individual player */
public class Player {

	private Random rand = new Random();		
	public String playerName;			/** Name of player */
	public Pos position;				/** Position of player - set to enum Post
	   									/**  Can only be one of: QB, RB, WR, TE, D, K */
	public double mean;					/** Average of points player have scored */
	public double std;					/** Standard deviation of points players have scored */
	public int byeWeek;					/** Player bye week */	
	
	public Player(String playerName, Pos position, double mean, double std, int byeWeek) {
		this.playerName = playerName;
		this.position = position;
		this.mean = mean;
		this.std = std;
		this.byeWeek = byeWeek;
	}
	
	/** Generates a random score for the player based off 
	 *  mean and standard deviation */
	public double makeScore() {
		return mean + rand.nextGaussian() * std;
	}
	
	/** ToString method for player */
	public String toString() {
		return playerName + " " + position + " " + mean + " " + std;
	}
	
}

/** Enum used to keep track of player positions */
enum Pos {

	QB, RB, WR, TE, K, DST
	
}
