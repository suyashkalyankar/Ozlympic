package gameClasses;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Suyash Kalyankar
 *Referee is inheriting Participant
 */
public class Referee extends Participant{

	ArrayList<Athlete> athleteLoadList;
	ArrayList<Athlete> selectedAthlete ;

	ArrayList<Referee> refree;
	
	public Referee(String id, String name, int age, String state) {
		super(id, name, age, state);
	}
/**
 * @description displaying the Game results to user
 */
	
	/**
	 * @description loading the list of Athletes
	 * @param athleteList list of athlete objects
	 */
	public void loadAthletes(List selectedAthlete){
		this.selectedAthlete =  (ArrayList<Athlete>) selectedAthlete;
		
	}

	
}
