package gameClasses;
/**
 * @author Suyash Kalyankar
 *AbstractEvent class is implementing Event interface
 */
public abstract class AbstractEvent implements Event {
protected int minTime;         //minimum time of event
protected int maxTime;         //maximum time of event

public AbstractEvent(int minTime, int maxTime) {
	
	this.minTime = minTime;
	this.maxTime = maxTime;
}
public int getMinTime(){
	 return minTime;
}
public int getMaxTime(){
	 return maxTime;
}
/**
 * @description overridden method to get minimum time
 * @return int minimum time for event
 */
@Override
public int getminTimer() {
	// TODO Auto-generated method stub
	return minTime;
}
/**
 * @description overridden method to get maximum time
 * @return int returns maximum time for event
 */
@Override
public int getmaxTimer() {
	// TODO Auto-generated method stub
	return maxTime;
}



}
