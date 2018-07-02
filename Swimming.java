package gameClasses;
import java.text.DecimalFormat;
/**
 * 
 * @author ASHISH
 *
 */
public class Swimming extends AbstractEvent{
	 static int minTime=100;          //minimum time for event Swimming
	 static int maxTime=200;          //maximum time for event Swimming
	 static int count=0;               //counting number of times event played
		/*
		 * method to pass minimum and maximum time of event to parent class
	    */
	 public Swimming() {
		super(minTime,maxTime);
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
	 * @return String S to get name cycling
	 */
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return "swimming";
	}
	 
	 
}
