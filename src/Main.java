import java.io.IOException;

/** Runner for the program */
public class Main {
	
	static double timesRun = 100;
	
	/** Main runner method*/
	public static void main (String[] args) throws IOException {
		tDB.generateTeams();
		Season s;
		for(int i = 0; i < timesRun; i++) {
			s = new Season();
		}
		
		teamPlayoffPercentage();
		teamPeanantPercentage();
		teamChampionshipPercentage();
	
	}
	
	/** Prints a list of all players and their chances to make the playoffs */
	private static void teamPlayoffPercentage() {
		System.out.println("Odds of making the playoffs 2019 (based off week 11 stats): ");
		System.out.println();
		for(int i = 0; i < tDB.leagueTeams.size(); i++) {
			System.out.println(tDB.leagueTeams.get(i) + " " + (int) (tDB.leagueTeams.get(i).playOffAppearances / timesRun * 100) + "%");
			if((i + 1) % 4 == 0) {
				System.out.println();
			}
		}
	}
	
	/** Prints a list of all players and their chances to make it to the championship */
	private static void teamPeanantPercentage() {
		System.out.println("Odds of making the championship (based of week 11 stats): ");
		System.out.println();
		for(int i = 0; i < tDB.leagueTeams.size(); i++) {
			System.out.println(tDB.leagueTeams.get(i) + " " + (int) (tDB.leagueTeams.get(i).peanants / timesRun * 100) + "%");
			if((i + 1) % 4 == 0) {
				System.out.println();
			}
		}
	}
	
	/** Prints a list of all players and their chances to win the entire league */
	private static void teamChampionshipPercentage() {
		System.out.println("Odds of winning the League (based of week 11 stats): ");
		System.out.println();
		for(int i = 0; i < tDB.leagueTeams.size(); i++) {
			System.out.println(tDB.leagueTeams.get(i) + " " + (int) (tDB.leagueTeams.get(i).championships / timesRun * 100) + "%");
			if((i + 1) % 4 == 0) {
				System.out.println();
			}
		}
	}
	
}
