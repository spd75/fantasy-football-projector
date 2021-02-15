import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;

/**	Class Reads txt file and creates a team from it. */
public abstract class DataRead {	
	
	/** Reads files from parameters and creates a team
	 *  Team is created using two linked lists, one for starters, another for bench players
	 *  @param nameFile - file with each player's name
	 *  @param dataFile - file with each player's data
	 *  @throws IOException when failed to read one of the files */
	public static Team createTeam(String nameFile, String dataFile) throws IOException{
		Reader r1 = new BufferedReader(new FileReader(nameFile));
		StreamTokenizer readNames = new StreamTokenizer(r1);
		
		Reader r2 = new BufferedReader(new FileReader(dataFile));
		StreamTokenizer readData = new StreamTokenizer(r2);
		
		LinkedList<Player> starters = new LinkedList();
		LinkedList<Player> bench = new LinkedList();
	
		// Loop works to create starters
		// Variable kickerFound switches loop and stops when kicker is added to list
		boolean kickerFound = false;
		while(!kickerFound) {
			Player p;
			String playerName = "";
			Pos playerPos;
			int playerByeWeek;
			double playerMean;
			double playerStd;
			
			readNames.nextToken();
			checkType(readNames, 34, playerName);
			playerName = readNames.sval;
			
			readData.nextToken();
			checkType(readData, -3, playerName);
			playerPos = getPosition(readData.sval);
			if(playerPos == Pos.K) {
				kickerFound = true;
			}
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerByeWeek = (int) readData.nval;
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerMean = readData.nval;
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerStd = readData.nval;
	
			p = new Player(playerName, playerPos, playerMean, playerStd, playerByeWeek);
			starters.add(p);
		}
		
		// Loop works to create bench players
		while(kickerFound) {
			Player p;
			String playerName = "";
			Pos playerPos;
			int playerByeWeek;
			double playerMean;
			double playerStd;
			
			readNames.nextToken();
			if (readNames.ttype == -1) {
				break;
			}
			checkType(readNames, 34, playerName);
			playerName = readNames.sval;
			
			readData.nextToken();
			checkType(readData, -3, playerName);
			playerPos = getPosition(readData.sval);
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerByeWeek = (int) readData.nval;
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerMean = readData.nval;
			
			readData.nextToken();
			checkType(readData, -2, playerName);
			playerStd = readData.nval;
	
			p = new Player(playerName, playerPos, playerMean, playerStd, playerByeWeek);
			bench.add(p);
		}
		
		return new Team(starters, bench);
	}
	
	/** Because positions are expressed as strings in the txt file,
	 *  getPosition works to process this string and return it as the enum Pos
	 *  @param positionString - the string processed in the txt file
	 */
	private static Pos getPosition(String positionString) {
		switch(positionString) {
		case "QB":
			return Pos.QB;
		case "RB":
			return Pos.RB;
		case "WR":
			return Pos.WR;
		case "TE":
			return Pos.TE;
		case "D":
			return Pos.DST;
		case "K":
			return Pos.K;
		default:
			return Pos.K;
		}
	}
	
	
	/** Helper method checks to make sure the txt file is correctly formatted to read
	 *  @throws FileNotFoundException when the txt file isn't formatted correctly */
	private static void checkType(StreamTokenizer reader, int errorValue, String name) throws FileNotFoundException {
		if(reader.ttype != errorValue) {
			System.out.println("Error with file formatting on player: " + name);
			throw new FileNotFoundException("Error with file formatting.");
		}
	}
	
	
	
}
