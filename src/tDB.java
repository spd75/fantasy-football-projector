import java.io.IOException;
import java.util.ArrayList;

/** Class serves as a database, which creates all teams.
 *  All teams are declared static, 
 *  so this class must ALWAYS be called to reference a team. */
public class tDB {

	/** All team types initiated for the league */
	public static Team sergioTeam;
	public static Team gradyTeam;
	public static Team brianTeam;
	public static Team zachTeam;
	public static Team schnidTeam;
	public static Team buddekeTeam;
	public static Team fordTeam;
	public static Team paulTeam;
	public static Team beckTeam;
	public static Team danielTeam;
	public static Team clayTeam;
	public static Team eoinTeam;
	public static Team perroneTeam;
	public static Team frawleyTeam;
	public static Team kalebTeam;
	public static Team brandtTeam;
	
	public static ArrayList<Team> leagueTeams = new ArrayList<Team>();
	/** Only method of this class that should be referenced anywhere else.
	 * 	Creates teams and sets their record */
	public static void generateTeams() {
		createAllTeams();
		setTeamNames();
	}
	
	/** Creates all the team instances for each team in the league. */
	private static void createAllTeams() {
		// Initiating Sergio's team
		try {
			sergioTeam = DataRead.createTeam("Resources/Sergio/SergioTeamRoster", "Resources/Sergio/SergioTeamData");
		} catch (IOException e) {
			System.out.println("Error making sergioTeam.");
		}
		
		// Initiating Grady's team
		try {
			gradyTeam = DataRead.createTeam("Resources/Grady/GradyTeamRoster", "Resources/Grady/GradyTeamData");
		} catch (IOException e) {
			System.out.println("Error making gradyTeam");
		}
		
		// Initiating Brian's Team
		try {
			brianTeam = DataRead.createTeam("Resources/Brian/BrianTeamRoster", "Resources/Brian/BrianTeamData");
			int x = 5 + 5;
		} catch (IOException e) {
			System.out.println("Error makng brianTeam.");
		}
		
		// Initiating Zach's Team
		try {
			zachTeam = DataRead.createTeam("Resources/Zach/ZachTeamRoster", "Resources/Zach/ZachTeamData");
		} catch (IOException e) {
			System.out.println("Error making zachTeam");
		}
		
		// Initiating Schnid's Team
		try {
			schnidTeam = DataRead.createTeam("Resources/Schnid/SchnidTeamRoster", "Resources/Schnid/SchnidTeamData");
		} catch (IOException e) {
			System.out.println("Error making schnidTeam");
		}
		
		// Initiating Buddeke's Team
		try {
			buddekeTeam = DataRead.createTeam("Resources/Buddeke/BuddekeTeamRoster", "Resources/Buddeke/BuddekeTeamData");
		} catch (IOException e) {
			System.out.println("Error making buddekeTeam");
		}
		
		// Initiating Ford's Team
		try {
			fordTeam = DataRead.createTeam("Resources/Ford/FordTeamRoster", "Resources/Ford/FordTeamData");
		} catch (IOException e) {
			System.out.println("Error making fordTeam");
		}
 		
		
		// Initating Paul's Team
		try {
			paulTeam = DataRead.createTeam("Resources/Paul/PaulTeamRoster", "Resources/Paul/PaulTeamData");
		} catch(IOException e) {
			System.out.println("Error making paulTeam");
		}
		
		// Initiating Beck's Team
		try {
			beckTeam = DataRead.createTeam("Resources/Beck/BeckTeamRoster", "Resources/Beck/BeckTeamData");
		} catch(IOException e) {
			System.out.println("Error making beckTeam");
		}
		
		try {
			danielTeam = DataRead.createTeam("Resources/Daniel/DanielTeamRoster", "Resources/Daniel/DanielTeamData");
		} catch(IOException e) {
			System.out.println("Error making danielTeam");
		}
		
		try {
			clayTeam = DataRead.createTeam("Resources/Clay/ClayTeamRoster", "Resources/Clay/ClayTeamData");
		} catch(IOException e) {
			System.out.println("Error making clayTeam");
		}
		
		try {
			eoinTeam = DataRead.createTeam("Resources/Eoin/EoinTeamRoster", "Resources/Eoin/EoinTeamData");
		} catch(IOException e) {
			System.out.println("Error making eoinTeam");
		}
		
		try {
			perroneTeam = DataRead.createTeam("Resources/Perrone/PerroneTeamRoster", "Resources/Perrone/PerroneTeamData");
		} catch(IOException e) {
			System.out.println("Error making perroneTeam");
		}
		
		try {
			frawleyTeam = DataRead.createTeam("Resources/Frawley/FrawleyTeamRoster", "Resources/Frawley/FrawleyTeamData");
		} catch(IOException e) {
			System.out.println("Error making frawleyTeam");
		}
		
		try {
			kalebTeam = DataRead.createTeam("Resources/Kaleb/KalebTeamRoster", "Resources/Kaleb/KalebTeamData");
		} catch(IOException e) {
			System.out.println("Error making kalebTeam");
		}
		
		try {
			brandtTeam = DataRead.createTeam("Resources/Brandt/BrandtTeamRoster", "Resources/Brandt/BrandtTeamData");
		} catch(IOException e) {
			System.out.println("Error making brandtTeam");
		}
		
		addTeamsToArray();
		
	}
	
	/** Records set to fixed values based off how the league is doing. */
	public static void setTeamRecords() {
		// TODO 
		gradyTeam.setRecord(6, 5, 879);
		brianTeam.setRecord(4, 7, 933);
		zachTeam.setRecord(4, 7, 888);
		schnidTeam.setRecord(3, 8, 941);
		
		buddekeTeam.setRecord(9, 2, 1078);
		paulTeam.setRecord(8, 3, 1030);
		fordTeam.setRecord(7, 4, 983);
		beckTeam.setRecord(3, 8, 782);
		
		danielTeam.setRecord(9, 2, 1128);
		clayTeam.setRecord(5, 6, 932);
		eoinTeam.setRecord(5, 6, 881);
		sergioTeam.setRecord(4, 7, 786);
		
		perroneTeam.setRecord(6, 5, 1020);
		frawleyTeam.setRecord(6, 5, 883);
		kalebTeam.setRecord(5, 6, 891);
		brandtTeam.setRecord(4, 7, 958);
	}
	
	/** Sets team names of all teams */
	private static void setTeamNames() {
		gradyTeam.setName("Grady");
		brianTeam.setName("Brian");
		zachTeam.setName("Zach");
		schnidTeam.setName("Schnid");
		
		buddekeTeam.setName("Buddeke");
		paulTeam.setName("Paul");
		fordTeam.setName("Ford");
		beckTeam.setName("Beck");
		
		danielTeam.setName("Daniel");
		clayTeam.setName("Clay");
		eoinTeam.setName("Eoin");
		sergioTeam.setName("Sergio");
		
		perroneTeam.setName("Perrone");
		frawleyTeam.setName("Frawley");
		kalebTeam.setName("Kaleb");
		brandtTeam.setName("Brandt");
	}
	
	/** Adds players to array list leagueTeams */
	public static void addTeamsToArray() {
		leagueTeams.add(gradyTeam);
		leagueTeams.add(brianTeam);
		leagueTeams.add(zachTeam);
		leagueTeams.add(schnidTeam);
		
		leagueTeams.add(buddekeTeam);
		leagueTeams.add(paulTeam);
		leagueTeams.add(fordTeam);
		leagueTeams.add(beckTeam);
		
		leagueTeams.add(danielTeam);
		leagueTeams.add(clayTeam);
		leagueTeams.add(eoinTeam);
		leagueTeams.add(sergioTeam);
		
		leagueTeams.add(perroneTeam);
		leagueTeams.add(frawleyTeam);
		leagueTeams.add(kalebTeam);
		leagueTeams.add(brandtTeam);
		
	}
	
}
