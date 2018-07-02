package gameClasses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Backend.FileDataBaseReader;
/**
 * 
 * @author Ashish Bhardwaj
 * Game is initiating the game , rewarding winner and getting the name of winner
 *
 */
public class Game {
	int firstPrize = 5;
	int secondPrize = 2;
	int thirdPrize = 1;
	final int firstPlace = 0;
	final int secondPlace = 1;
	final int thirdPlace = 3;
	String gameID;
	Event event;
	Referee referee;
	Set<Athlete> athleteLoadList;
	Athlete firstAthlete;
	Athlete secondAthlete;
	Athlete thirdAthlete;
	FileDataBaseReader db1;
	ArrayList<Athlete> selectedAthlete ;
	
	
	public  ArrayList<Athlete> getSelectedAthleteList(){
	return   selectedAthlete;
} 
		
		public Set<Athlete> getAthleteList() {
			return athleteLoadList;
		} 
	/**
	 * Game constructor to create new Game
	 * @param event
	 * @param athleteLoadList
	 * @throws IOException
	 */
		public Game(Event event, Set<Athlete> athleteLoadList) throws IOException{
			this.setEvent(event);
			System.out.println("EVENT IS "+event);
			this.setGameID(event.getEventName()+event.getCount());
			this.setAthleteList(athleteLoadList);
			
		}
	 
	
		public Event getEvent() {
			return event;
		}
	
	public void setSelectedAthleteList(ArrayList<Athlete> selectedAthlete){
		
		this.selectedAthlete=selectedAthlete;
		
	}
		public void setAthleteList(Set<Athlete> athleteLoadList) {
			this.athleteLoadList = athleteLoadList;
			
		}


		public void setEvent(Event event) {
			this.event = event;
		}


		public String getGameID() {
			return gameID;
		}


		public void setGameID(String gameID) {
			this.gameID = gameID;
		}
		
	/**
	 * @description initiating the game	
	 */
		public void initiateGame(){
			System.out.println("SIZE IS "+selectedAthlete.size());
			System.out.println("Game Started ");
			Athlete currentAthlete;
			Iterator iterator = this.selectedAthlete.iterator();
			while(iterator.hasNext()){
				currentAthlete = (Athlete)iterator.next();
				System.out.println("Event in initiate game is "+this.event);
				currentAthlete.compete(this.event.getminTimer(),this.event.getmaxTimer());
			}
			System.out.println("Game has been initiated");
		} 
	
		
		/**
		 * @throws IOException 
		 * @description rewarding the Athletes according to requirement
		 */
		public void rewardWinner() throws IOException{
			 List toSort = new ArrayList(this.selectedAthlete);
			 Collections.sort(toSort);
			 Collections.reverse(toSort);
			 this.firstAthlete = (Athlete)toSort.get(0);
			 this.firstAthlete.setTotalPoints(this.firstAthlete.getTotalPoints()+firstPrize);
			 this.secondAthlete = (Athlete)toSort.get(1);
			 this.secondAthlete.setTotalPoints(this.secondAthlete.getTotalPoints()+secondPrize);
			 this.thirdAthlete = (Athlete)toSort.get(2);
		     this.thirdAthlete.setTotalPoints(this.thirdAthlete.getTotalPoints()+thirdPrize);
			 System.out.println("FIRST ATHLETE IS "+firstAthlete.getName()+" WITH TIME "+firstAthlete.getFinishTimer());
			 System.out.println("Second ATHLETE IS "+secondAthlete.getName()+" WITH TIME "+secondAthlete.getFinishTimer());
			 System.out.println("third ATHLETE IS "+thirdAthlete.getName()+" WITH TIME "+thirdAthlete.getFinishTimer());
			
		 }
		
		public String getWinnerName(){
			return this.firstAthlete.getName();
			
		}
		public String getRunnerUpName(){
			return this.secondAthlete.getName();
		}

	
       public String getSecondRunnerUpName(){
	return this.thirdAthlete.getName();
       }
       public int getWinnerTime(){
			return this.firstAthlete.getFinishTimer();
			
		}
       public int getRunnerUpTime(){
			return this.secondAthlete.getFinishTimer();
		}

		
      public int getSecondRunnerUpTime(){
	return this.thirdAthlete.getFinishTimer();
      }


	    
	
	
	
}