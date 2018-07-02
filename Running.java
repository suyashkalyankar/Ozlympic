package gameClasses;
import java.text.DecimalFormat;
/**
 * 
 * @author Suyash kalyankar
 *Running is inheriting the AbstractEvent
 */
public class Running extends AbstractEvent{
	static int minTime=10;             //minimum time for the event running
	static int maxTime=20;             // maximum time for the event running
	static int count=0;                //counting number of times event played
	/*
	 * method to pass minimum and maximum time of event to parent class
	 */
	 public Running() {
		super(minTime, maxTime);
	 }
	 /**
	  * @description counting number of games
	  * @return game counts
	  */
	@Override
	public String getCount(){
		DecimalFormat formatter = new DecimalFormat("00");
		String formatted = formatter.format(++count);

		return formatted;
	}
	/**
	 * @description fetching the name of event
	 * @return String R to get name cycling
	 */
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return "running";
	}
}
