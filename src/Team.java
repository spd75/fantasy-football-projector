import java.util.LinkedList;

/** Class for creating each individual team */
public class Team {

	public LinkedList<Player> starters = new LinkedList(); 	/** Contains list of 10 starting players */
	public LinkedList<Player> bench = new LinkedList(); 	/** All bench players */
	public String name;										/** Holds team name */
	public int wins = 0; 									/** Keeps track of team wins */
	public int losses = 0;									/** Keeps track of team losses */
	public int pointsScored = 0;							/** Keeps track of points scored */
	public int rankPoints = 0;								/** Theoretical point system used to make rankings easier */
	
	public double playOffAppearances = 0;					/** Number of times this team has gone to the playoffs - whole number */
	public double championships = 0;						/** Number of championships team has won over the seasons - whole number */
	public double peanants = 0;								/** Number of finals appearances made by the team - whole number number */
	public double loserGame = 0;							/** Number of appearances in the loser bowl */
	public double loser = 0;								/** Number of times finished last in the league */
	public int endOfSeasonStandingTotal = 0;				/** A number that represents final season position (i. e. 1 is champion, 
															 * 	2 is runner up, 16 is loser, etc.) 
															 *  Throughout multiple seasons, this number is added, to eventually become
															 *  much larger than 16.  However, this will be divided in the main method to 
															 *  determine the average final position for each team. */
	
	public Team(LinkedList<Player> starters, LinkedList<Player> bench) {
		this.starters = starters;
		this.bench = bench;
		this.name = name;
	}
	
	/** Returns a randomly generated score for the team */
	
	/** Returns a generated a score for a team, based off random generation
	 *  of points from its starting lineup. */
	public double genScore() {
		double finalScore = 0;
		for(Player starter: starters) {
			finalScore += starter.makeScore();
		}
		return finalScore;
	}
	
	/** Sets the record of a team */
	public void setRecord(int wins, int losses, int points) {
		this.wins = wins;
		this.losses = losses;
		this.pointsScored = points;
		updateRankPoints();
	}
	
	/** Game is played against opponent 
	 * 	If won, wins++ and opponent losses++ 
	 *  If lost, losses++ and opponent wins++ 
	 *  Adds points to both team point totals */
	public void playGame(Team opponent) {	
		double teamPoints = genScore();
		double oppPoints = opponent.genScore();
		if(teamPoints >= oppPoints) {
			wins++;
			opponent.losses++;

		} else {
			opponent.wins++;
			losses++;
		}
		pointsScored += (int) teamPoints;
		opponent.pointsScored += (int) oppPoints;
		updateRankPoints();
		opponent.updateRankPoints();
	}
	
	/** Similar to playing a game, however an array of
	 * 	teams is returned, where Array[0] is the winner and
	 *  Array[1] is the loser */
	public Team giveWinner(Team opponent) {
		if(genScore() >= opponent.genScore()) {
			return this;
		} else {
			return opponent;
		}
	}
	
	/** Sets name of the team */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Returns name of team - called if System.out.println() is used */
	public String toString() {
		return name;
	}
	
	/** Adds one to the number of playoff appearances the team has */
	public void addPlayoffAppearance() {
		playOffAppearances++;
	}
	
	/** Each week, this adds to the points*/
	public void updateRankPoints() {
		rankPoints = (wins * 2000) + pointsScored;
	}
	
}
