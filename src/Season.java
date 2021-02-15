import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** Class simulates a season of the league */
public class Season {
	
	/** Instantiating all the teams from the tDB class 
	 * 	Putting all the teams in their division ararys*/
	Team grady = tDB.gradyTeam;
	Team brian = tDB.brianTeam;
	Team zach = tDB.zachTeam;
	Team schnid = tDB.schnidTeam;
	Team[] north = {grady, brian, zach, schnid};
	
	Team buddeke = tDB.buddekeTeam;
	Team ford = tDB.fordTeam;
	Team paul = tDB.paulTeam;
	Team beck = tDB.beckTeam;
	Team[] south = {buddeke, ford, paul, beck};
	
	Team daniel = tDB.danielTeam;
	Team clay = tDB.clayTeam;
	Team eoin = tDB.eoinTeam;
	Team sergio = tDB.sergioTeam;
	Team[] east = {daniel, clay, eoin, sergio};
	
	Team perrone = tDB.perroneTeam;
	Team frawley = tDB.frawleyTeam;
	Team kaleb = tDB.kalebTeam;
	Team brandt = tDB.brandtTeam;
	Team[] west = {perrone, frawley, kaleb, brandt};
	
	Team losingTeam; 												/** Reference variable used for a losing team throughout the class */
	
	Team[][] league = {{grady, brian, zach, schnid}, 				/** League array, dividing all teams into divisions */
			{buddeke, ford, paul, beck}, 							
			{daniel, clay, eoin, sergio}, 
			{perrone, frawley, kaleb, brandt}};

	ArrayList<Team> restOfLeague = new ArrayList<Team>();			/** Array list for the teams that didn't make the playoffs */								
	ArrayList<Team> playOffs = new ArrayList<Team>();				/** Array list for the teams in the playoffs */
	ArrayList<Team> divisionWinners = new ArrayList<Team>();		/** Array list for the division winners */
	ArrayList<Team> nonDivisionWinners = new ArrayList<Team>();		/** Array list for the non division winners */
	
	HashMap<Integer, Team> seededPlayOffs = new HashMap<Integer, Team>();
	HashMap<Integer, Team> seededLoserCon = new HashMap<Integer, Team>();
	
	
	/** Constructor for a single season */
	public Season() {
		setPlayOffs();
		PostSeason s = new PostSeason(seededPlayOffs, seededLoserCon);
	}
	
	
	/** Runs a series of private methods in the class that generates the playoffs 
	 * 	Methods that are called are specified in the JAVADOC with "Part of setPlayOffs()" */
	private void setPlayOffs() {
		tDB.setTeamRecords();
		playWeek12();
		playWeek13();
		addDivisionWinnersToPlayoffs();
		addDivisionLosersToPlayoffs();
		seedPlayOffs();
		seedLoserConsolation();
	}
	
	/** Simulates week 12 with accurate schedules pulled from actual data 
	 *  Part of setPlayOffs() */
	private void playWeek12() {
		paul.playGame(brandt);
		schnid.playGame(perrone);
		frawley.playGame(kaleb);
		sergio.playGame(buddeke);
		clay.playGame(eoin);
		ford.playGame(grady);
		daniel.playGame(zach);
		brian.playGame(beck);	
	}
	
	/** Simulates week 13 with accurate schedules pulled form actual data
	 *  Part of setPlayOffs() */
	private void playWeek13() {
		brandt.playGame(schnid);
		perrone.playGame(frawley);
		kaleb.playGame(sergio);
		buddeke.playGame(clay);
		eoin.playGame(ford);
		grady.playGame(daniel);
		zach.playGame(brian);
		beck.playGame(paul);
	}
	
	/** Adds the divison winners to the playoffs list 
	 * 	Part of setPlayoffs() */
	private void addDivisionWinnersToPlayoffs() {
		Team divisionWinner;
		for(int i = 0; i < league.length; i++) {
			divisionWinner = league[i][0];
			for(int j = 1; j < league[i].length; j++) {
				divisionWinner = checkBetterTeam(divisionWinner, league[i][j]);
				restOfLeague.add(losingTeam);
			}
			divisionWinners.add(divisionWinner);
			divisionWinner.addPlayoffAppearance();
		}
	}
	
	/** Adds the wildcards to the playoffs list 
	 *  Part of setPlayOffs() */
	private void addDivisionLosersToPlayoffs() {
		for(int i = 0; i < 4; i ++) {
			Team bestTeam = restOfLeague.get(0);
			for(int j = 1; j < restOfLeague.size(); j++) {
				bestTeam = checkBetterTeam(bestTeam, restOfLeague.get(j));
			}
			nonDivisionWinners.add(bestTeam);
			restOfLeague.remove(bestTeam);
			bestTeam.addPlayoffAppearance();
		}
	}
	
	/** Seeds the playoffs
	 * 	ArrayList playOffs is put in order based off seeds of playOff teams
	 * 	Seeds are determined by division winners and the rankPoints of each team */
	private void seedPlayOffs() {
		PriorityQueue<Team> playOffRankPoints = new PriorityQueue<Team>(new BetterTeamComparable());
		int i = 1;
		
		for(Team team : divisionWinners) {
			playOffRankPoints.add(team);
		}
		while(!playOffRankPoints.isEmpty()) {
			Team addTeam = playOffRankPoints.poll();
			playOffs.add(addTeam);
			seededPlayOffs.put(i, addTeam);
			i++;
		}
		
		for(Team team : nonDivisionWinners) {
			playOffRankPoints.add(team);
		}
		
		while(!playOffRankPoints.isEmpty()) {
			Team addTeam = playOffRankPoints.poll();
			playOffs.add(addTeam);
			seededPlayOffs.put(i, addTeam);
			i++;
		}
		
	}
	
	/** Seeds the loser consolation (teams not in the playoffs */
	private void seedLoserConsolation() {
		PriorityQueue<Team> loserConsolationRankPoints = new PriorityQueue<Team>(new BetterTeamComparable());
		int i = 9;
		
		while(!restOfLeague.isEmpty()) {
			loserConsolationRankPoints.add(restOfLeague.remove(0));
		}
		while(!loserConsolationRankPoints.isEmpty()) {
			Team addTeam = loserConsolationRankPoints.poll();
			restOfLeague.add(addTeam);
			seededLoserCon.put(i, addTeam);
			i++;
		}
	}
	
	/** Checks to see which team is better 
	 * 	Does this by checking each teams' rank points */
	private Team checkBetterTeam(Team team1, Team team2) {	
		if(team1.rankPoints >= team2.rankPoints) {
			losingTeam = team2;
			return team1;
		} else {
			losingTeam = team1;
			return team2;
		}
	}
	
	/** Contained class created for organization purposes.
	 *  All operations regarding the league's post-season are done here. 
	 *  The class runs the post season this by using 2 Hashmaps of teams
	 *  (one for teams in the playoffs and one for teams not in the playoffs).
	 *  Then, several arrays of seeds are used to determined the progression
	 *  of each round in the playoffs.  Seeds are the keys for the teams in the 
	 *  hashmaps, so this method allows for many operations to be performed in
	 *  constant O(1) time. */
	private class PostSeason {
		HashMap<Integer, Team> playOffsPost;
		HashMap<Integer, Team> loserConsolation;
		
		int[] playOffs = {1, 8, 4, 5, 3, 6, 2, 7};
		int[] winnersOfFirstRound = new int[4];
		int[] losersOfFirstRound = new int[4];
		
		int[] winnersOfSecondRound = new int[2];
		int[] winnersOfLosersOfFirstRound = new int[2];
		int[] losersOfLosersOfFirstRound = new int[2];
		int[] losersOfSecondRound = new int[2];
		
		int[] postBracket = new int[8];
		
		int[] loserConSeeds = {9, 10, 11, 12, 13, 14, 15, 16};
		int[] tempLoserConSeeds = new int[8];
		
		ArrayList<ArrayList<Team>> playOffLists = new ArrayList<ArrayList<Team>>();
		
		
		private PostSeason(HashMap<Integer, Team> playOffs, HashMap<Integer, Team> loserConsolation) {
			this.playOffsPost = playOffs;
			this.loserConsolation = loserConsolation;
			runPlayOffs();
		}
		
		/** Runs a complete playoff for each season
		 *  Calls 3 methods needed to run each round in the playoffs */
		private void runPlayOffs() {
			playOffsFirstRound();
			playOffsSecondRound();
			playOffsFinalRound();
		}
		
		/** Runs the first round in the playoffs.
		 * 	Arrays winnersOfFirstRound and losersOfFirstRound contains
		 *  the seeds for the teams that made it to the second round and the teams 
		 *  that didn't. */
		private void playOffsFirstRound() {
			for(int i = 0; i < playOffsPost.size() - 1; i+= 2) {
				Team team1 = playOffsPost.get(playOffs[i]);
				Team team2 = playOffsPost.get(playOffs[i + 1]);
				Team winner = team1.giveWinner(team2);
				if(winner.equals(team1)) {
					winnersOfFirstRound[i/2] = playOffs[i];
					losersOfFirstRound[i/2] = playOffs[i + 1];
				} else if (winner.equals(team2)) {
					winnersOfFirstRound[i/2] = playOffs[i + 1];
					losersOfFirstRound[i/2] = playOffs[i];
				}
			}
	
		}
		
		/** Runs the second round of the playoffs. 
		 *  Arrays winnersOfSecondRound, losersOfSecondRound, winnersOfLosersOfFirstRound,
		 *  and winnersOfWinnersOfFirstRound all contain the seeds for teams based
		 *  on their advancement throughout the postseason. */
		private void playOffsSecondRound() {
			for(int i = 0; i < winnersOfFirstRound.length - 1; i+= 2) {
				Team teamWinner1 = playOffsPost.get(winnersOfFirstRound[i]);
				Team teamWinner2 = playOffsPost.get(winnersOfFirstRound[i + 1]);
				Team teamLoser1 = playOffsPost.get(losersOfFirstRound[i]);
				Team teamLoser2 = playOffsPost.get(losersOfFirstRound[i + 1]);
				Team winnerWinner = teamWinner1.giveWinner(teamWinner2);
				Team loserWinner = teamLoser1.giveWinner(teamLoser2);
				
				if(winnerWinner.equals(teamWinner1)) {
					winnersOfSecondRound[i/2] = winnersOfFirstRound[i];
					losersOfSecondRound[i/2] = winnersOfFirstRound[i + 1];
				} else if(winnerWinner.equals(teamWinner2)) {
					winnersOfSecondRound[i/2] = winnersOfFirstRound[i + 1];
					losersOfSecondRound[i/2] = winnersOfFirstRound[i];
				}
				
				if(loserWinner.equals(teamLoser1)) {
					winnersOfLosersOfFirstRound[i/2] = losersOfFirstRound[i];
					losersOfLosersOfFirstRound[i/2] = losersOfFirstRound[i + 1];
				} else if(loserWinner.equals(teamLoser2)) {
					winnersOfLosersOfFirstRound[i/2] = losersOfFirstRound[i + 1];
					losersOfLosersOfFirstRound[i/2] = losersOfFirstRound[i];
				}
			}
		}
		
		/** Helper method called in playOffsFinalRound() that creates the final
		 *  matchups of all teams. */
		private void setPostBracket() {
			for(int i = 0; i <= 1; i++) {
				postBracket[i] = winnersOfSecondRound[i];
				postBracket[i + 2] = losersOfSecondRound[i];
				postBracket[i + 4] = winnersOfLosersOfFirstRound[i];
				postBracket[i + 6] = losersOfLosersOfFirstRound[i];
			}
			
		}
		
		/** Runs the final round of the playoffs aka the championship and increments
		 * variables for number of championships won and number of championships played in*/
		private void playOffsFinalRound() {
			setPostBracket();
			for(int i = 0; i < postBracket.length - 1; i += 2) {
				Team team1 = playOffsPost.get(postBracket[i]);
				Team team2 = playOffsPost.get(postBracket[i + 1]);
				Team winner = team1.giveWinner(team2);
				team1.peanants++;
				team2.peanants++;
				winner.championships++;
				if(winner.equals(team2)) {
					swap(postBracket, i, i + 1);
				} 
			}
		}
		
		/** Helper method that swaps seeds for teams in a given array A[] */
		private void swap(int[] A, int pos1, int pos2) {
			int temp = A[pos1];
			A[pos1] = A[pos2];
			A[pos2] = temp;
		}
		
	}

}
