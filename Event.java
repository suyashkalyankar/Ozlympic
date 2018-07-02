package gameClasses;
/**
 * 
 * @author Ashish Bhardwaj
 *
 */
public interface Event {
	
	public String getCount();         //counting number of games
	public String getEventName();       //fetching the name of Event
	public int getminTimer();         //fetching minimum time for event
	public int getmaxTimer();         //fetching maximum time for event

}
