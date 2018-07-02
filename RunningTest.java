package Junit;
import static org.junit.Assert.*;

import org.junit.Test;

import gameClasses.Running;

public class RunningTest {

	@Test
	public void getEventName() {
		Running runTest=new Running();
		assertEquals("Running","Cycling");
		
		System.out.println("Event Type is Same");
		
	}

}
