import java.util.Comparator;

/** Comparator used for determining which team is better based off a point system called "rankPoints" */
public class BetterTeamComparable implements Comparator<Team> {

	@Override
	public int compare(Team team1, Team team2) {
		if(team1.rankPoints > team2.rankPoints) {
			return -1;
		} else if (team1.rankPoints < team2.rankPoints) {
			return 1;
		} else {
			return 0;
		}
	}

}
