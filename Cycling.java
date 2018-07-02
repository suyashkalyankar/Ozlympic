package gameClasses;
import java.text.DecimalFormat;
/**
 * 
 * @author Ashish Bhardwaj
 *Cycling is inheriting the AbstractEvent class
 */
public class Cycling extends AbstractEvent {
static int minTime=500;                  //maximum time for event cycling
static int maxTime=800;                  //minimum time for event cycling
static int count =0;                     //counting number of times event played

 /*
  * method to pass minimum and maximum time of event to parent class
  */
public Cycling() {
	super(minTime, maxTime);
	// TODO Auto-generated constructor stub
}
/**
 * @description counting number of games
 * @return game counts
 */
public String getCount(){
	DecimalFormat formatter = new DecimalFormat("00");
	String formatted = formatter.format(++count);
	return formatted;
}
/**
 * @description fetching the name of event
 * @return String C to get name cycling
 */
@Override
public String getEventName() {
	// TODO Auto-generated method stub
	return "cycling";
}






}
