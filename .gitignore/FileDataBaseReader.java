package Backend;

/**
 * @author suyashkalyankar
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gameClasses.Athlete;
import gameClasses.Game;
import gameClasses.Referee;

public class FileDataBaseReader {
	

	static File file = new File("/Users/suyashkalyankar/Documents/workspace/AP2/src/Resources/Participants.txt");
	
 ArrayList<Athlete> athleteLoadList = new ArrayList<Athlete>();
ArrayList<Referee> refreeList = new ArrayList<Referee>();
static FileDataBaseReader fdb;
Athlete athlete;
Referee referee;
ArrayList<Game> gameList = new ArrayList<Game>();
Game game;
public static void main(String args[]) throws IOException{
	fdb.getFileDataBaseReader();
}

public static FileDataBaseReader getFileDataBaseReader() throws IOException{
	if(fdb!=null)
		return fdb;
	else
		fdb = new FileDataBaseReader();
	return fdb;
	 
} 
public void setGameList(ArrayList<Game> gameList){
	this.gameList=gameList;
}
public ArrayList<Game> getGameList(){
	return this.gameList;
}
public void setRefereeList(ArrayList<Referee> refreeList){
	this.refreeList=refreeList;
}
public ArrayList<Referee> getRefereeList(){
	System.out.println("referee list size in get is "+refreeList.size());
	return this.refreeList;
}
public void setAthleteLoadList(ArrayList<Athlete> list){
	this.athleteLoadList=list;
}
public ArrayList<Athlete> getAthleteLoadList() throws IOException{
	return this.athleteLoadList;
}
public void loadAthlete(Set<Athlete> athleteLoadList,String gameType) throws Exception, IOException{
	List currentList = new ArrayList(fdb.getFileDataBaseReader().getAthleteLoadList());
	 for(int i=0;i<currentList.size();i++){
	       	Athlete currentAthlete = (Athlete)currentList.get(i);
	       	String athleteType=currentAthlete.getAthleteType();
	       	if(gameType.toLowerCase().equals("swimming")){
	        	
	    		if(athleteType.equals("swimmer") || athleteType.equals("super") || athleteType.equals("Super") || athleteType.equals("Swimmer")){
	    
	    			athleteLoadList.add(currentAthlete);
	    		}
	    	}
	if(gameType.toLowerCase().equals("running")){
	        	
	    		if(athleteType.equals("sprinter") || athleteType.equals("super") || athleteType.equals("Super") || athleteType.equals("Sprinter")){
	    
	    			athleteLoadList.add(currentAthlete);
	    		}
	    	}
	if(gameType.toLowerCase().equals("cycling")){
    	
		if(athleteType.equals("cyclist") || athleteType.equals("super") || athleteType.equals("Super") || athleteType.equals("Cyclist")){

			athleteLoadList.add(currentAthlete);
		}
	}

	 }
}
public FileDataBaseReader() throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    String line = null;
    while((line = br.readLine())!= null){
    	String[] words = line.split("\\s*,\\s*");
    	String id= words[0];
    	System.out.println("id is"+id);
    	String type =words[1];
    	System.out.println("type is"+type);
    	String name=words[2];
    	System.out.println("name is"+name);
    	String state = words[4];
    	System.out.println("state is"+state);
    	int age=Integer.parseInt(words[3]);
    	System.out.println("age is"+age);
    	if(id.isEmpty() || type.isEmpty() ||type.isEmpty() || name.isEmpty() || state.isEmpty() ){
    		System.out.println("Connot add empty values in data");
    	}else{
    	if(validationCheck(type,state,age,name) == true ){
	    	if(participantType(type)=="athlete"){
	    		athlete =new Athlete( id, name, age, state, type);
	    		this.athleteLoadList.add(athlete);
	    		setAthleteLoadList(this.athleteLoadList);
	    	}else if(participantType(type)=="refree"){
	    		referee=new Referee( id,  name,  age,  state);
	    	this.refreeList.add(referee);
	    	}
	    	}else{
		    	System.out.println("Participant not added in List");
		    }

    	}
    }
}

public boolean conditionCheck(String name,String type,String state) throws IOException{
boolean check=false;
	int counter=0;
	List currentList = new ArrayList(fdb.getFileDataBaseReader().getAthleteLoadList());
	for(int i=0;i<currentList.size();i++){
		Athlete currentAthlete = (Athlete) currentList.get(i);
		String aname =currentAthlete.getName();
		String atype=currentAthlete.getAthleteType();
		String astate=currentAthlete.getState();
		if(aname.equals(name) && atype.equals(type) && astate.equals(state)){
			check=false;
		}
		else{
			continue;
	}
	
	}
	return check;
}
 
  

public static boolean typeCheck(String type){
	if(type.equals("official") || type.equals("sprinter") || type.equals("swimmer") || type.equals("cyclist") || type.equals("super") ){
		return true;
	}else if(type.isEmpty()){
		return false;
	}
	else{
		System.out.println("type is not valid");
		return false;
	}
	
}

public static boolean stateCheck(String state){
	if(state.equals("ACT") || state.equals("NSW") || state.equals("NT") || state.equals("QLD") || state.equals("SA") || state.equals("TAS") || state.equals("VIC") || state.equals("WA")){
		return true;
	}else if(state.isEmpty()){
		return false;
	}
	else{
		System.out.println("State is not correct");
		return false;
	}
}

public static boolean ageCheck(int age){
	if (age == (int)age){
		return true;
	}
	else{
		return false;
	}
}

public static String participantType(String type){
	
	if(type.equals("sprinter") || type.equals("swimmer") || type.equals("cyclist") || type.equals("super")  ){
		return "athlete";
	}
	else if(type.equals("official")){
		return "refree";
	}else{
		System.out.println("Not able to fetch Participant");
		return "none";
	}
}

public static boolean validationCheck(String type,String state,int age,String name){
	if(typeCheck(type)==true && stateCheck(state)==true && ageCheck(age)== true){
		return true;
	}else{
		return false;
	}
}


}
