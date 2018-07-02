package Junit;
import static org.junit.Assert.*;

import org.junit.Test;

import gameClasses.Referee;

public class RefreeTest1 {

	@Test
	public void getState() throws Exception {
		Referee reftest=new Referee("Oz3213","Ankur",32,"VIC");
		
		String getState=reftest.getState();
		
		assertEquals("VIC","ACT");
		System.out.println("State Is Incorrect");
		
		
		
	}

}
