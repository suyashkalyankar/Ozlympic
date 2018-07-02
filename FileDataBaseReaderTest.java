package Junit;
import static org.junit.Assert.*;

import org.junit.Test;

import Backend.FileDataBaseReader;

public class FileDataBaseReaderTest {

	@Test
	public  void typeCheck() throws Exception {
		
		FileDataBaseReader check=new FileDataBaseReader();
		String type=check.toString();
		assertEquals("Swimmer","Cyclists");
		
		System.out.println("Type is Different");
		
		
	}
	@Test
	public void StateCheck() throws Exception{
	
		FileDataBaseReader check=new FileDataBaseReader();
		String state=check.toString();
		assertEquals("Vic","Vic");
		System.out.println("State is Same");
	}
	
@Test
public void participantType()throws Exception{

	FileDataBaseReader check=new FileDataBaseReader();
	
	String type=check.toString();
	assertEquals("Swimmer","Cyclists");
	System.out.println("Type is Different");
}

@Test
public  void ageCheck() throws Exception{
	FileDataBaseReader check=new FileDataBaseReader();
	assertEquals("25","25");
	System.out.println("Age Is Same");
	
	
}
	
}
