package Junit;


import static org.junit.Assert.*;

import org.junit.Test;

import gameClasses.Athlete;

public class AthleteTest1 {

	@Test
	public void getid() throws Exception {
		
		Athlete test1=new Athlete("Oz1234", "suyash", 25, "swimmer", "ACT");
	String getid=test1.getId();
		
		assertEquals("Oz1234","Oz123");
		
	}

}
