package Backend;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gameClasses.Athlete;
import gameClasses.Game;
import gameClasses.Referee;

public class DataBaseConnection {

	Connection dbconnection = null;
	Game game;
	static Athlete athlete;
	Referee referee;
	ArrayList<String> data=new ArrayList<String>();
int counter=0;
static ArrayList<Athlete> athleteLoadList = new ArrayList<Athlete>();
 ArrayList<Referee> refereeList = new ArrayList<Referee>();
 
 static DataBaseConnection dbc = null;
public static void main(String args) throws ClassNotFoundException, SQLException{

}
public boolean checkConnection(){
	try{
		Class.forName("org.sqlite.JDBC");
	
	dbconnection = DriverManager
			.getConnection("jdbc:sqlite:/Users/suyashkalyankar/Documents/workspace/AP2/src/Resources/Database.db");
	System.out.println("Opened database successfully");
	return true;
	}
catch(Exception e){
	
	return false;
}
}

	public static ArrayList<Athlete> getAthleteLoadList() {
		System.out.println("Size inside getter is "+athleteLoadList.size());
		return athleteLoadList;
		
	}


	public ArrayList<Referee> getRefereeList() {
		return refereeList;
	}


	public void setRefereeList(ArrayList<Referee> refereeList) {
		this.refereeList = refereeList;
	}


	public static void setAthleteLoadList(ArrayList<Athlete> athleteLoadList) {
		DataBaseConnection.athleteLoadList = athleteLoadList;

	}

	public  DataBaseConnection() {
		Statement stmt = null;
		int insert = 0;
		int create = 0;
		int drop = 0;

		try {
			if(checkConnection()==true){
				stmt = dbconnection.createStatement();
			/*
			 * HAVE TO COMMENT THIS DROP STATEMENT FIRST AND AFTER A RUN UN COMMENT IT
			 * SAME FOR RESULT TABLE
			 */
				
				drop = stmt.executeUpdate("DROP Table ATHLETE");
				 create = stmt.executeUpdate(
				"CREATE TABLE ATHLETE (id varchar(10) NOT NULL, type varchar(10) NOT NULL,name varchar(20) NOT NULL,age INT NOT NULL,state varchar(10) NOT NULL,PRIMARY KEY(id))");
				System.out.println("Table created successfully"); 

				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz1123','official','Derek',21,'WA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz3434','sprinter','Mary',35,'VIC');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz0091','super','Hannah',24,'NSW');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz1234','swimmer','Beck',30,'TAS');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz3100','cyclist','Suyash',24,'SA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz3131','cyclist','Sammy',26,'ACT');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz8768','super','Ed',29,'VIC');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz2678','super','Joshua',23,'TAS');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz2423','swimmer','Ashish',25,'ACT');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz7426','sprinter','Pratap',28,'VIC');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz3567','official','Ankur',28,'VIC');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz4560','official','Lokesh',23,'ACT');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz8989','official','sunil',35,'WA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz1134','sprinter','Brendon',22,'WA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz2456','sprinter','Rick',32,'TAS');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz3789','swimmer','Aleks',24,'QLD');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz4567','cyclist','Yash',23,'NT');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz5324','cyclist','Seby',27,'NT');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz6789','swimmer','Cass',23,'SA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz0022','super','Yogi',25,'SA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz0012','swimmer','Owen',29,'WA');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz0050','swimmer','Gwen',29,'QLD');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz0078','cyclist','Andy',29,'QLD');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES('Oz0034','cyclist','Ali',29,'TAS');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz0089','sprinter','Rahul',28,'VIC');");
				insert = stmt.executeUpdate("INSERT INTO ATHLETE VALUES ('Oz0010','sprinter','Homy',28,'QLD');");

				System.out.println("Records created successfully");
				stmt = dbconnection.createStatement();
				ResultSet execute = stmt.executeQuery("Select * From ATHLETE;");

				while (execute.next()) {
					String id = execute.getString("id");
					String type = execute.getString("type");
					String name = execute.getString("name");
					int age = execute.getInt("age");
					String state = execute.getString("state");

					if(participantType(type)=="athlete"){
						athlete = new Athlete(id, name, age, state, type);
							athleteLoadList.add(athlete);
							
					}else if(participantType(type)=="referee"){
						referee=new Referee(id,name,age,state);
						refereeList.add(referee);
						
					}
				}
				setAthleteLoadList(athleteLoadList);
				setRefereeList(refereeList);
				
			}else{
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		finally {
			try {
				if (dbconnection != null) {
					dbconnection.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	public String readResult() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String buffer=null;
		if(checkConnection()==true){
		
			Statement st = dbconnection.createStatement();
			String sql = ("SELECT * FROM RESULT");
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) { 
			 String id = rs.getString("id"); 
			 String referee1 = rs.getString("referee");
			 String winner1=rs.getString("winner");
			 int time1=rs.getInt("time");
			 String winner2=rs.getString("winner2");
			 int time2=rs.getInt("time2");
			 String winner3=rs.getString("winner3");
			 int time3=rs.getInt("time3");
			
			  buffer=id+" "+referee1+" "+" "+winner1+" "+time1+" "+winner2+" "+time2+" "+winner3+" "+time3+" ";
			 
			}
		}else{
			System.out.println("Check Connection");;
		}
		
    
		dbconnection.close();
		   return buffer;
	}
	public ArrayList<String> getData() {
		return data;
	}
	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	public void writeDataBase(String ID,String referee, String winner,int time,String runnerup,int time2,String secondrunnerup,int time3 ) throws ClassNotFoundException, SQLException{

	Statement stmt = null;
		int insert = 0;
		int create = 0;
		int drop = 0;
		if(checkConnection()==true){
			stmt = dbconnection.createStatement();
			/*
			 * HAVE TO COMMENT THIS DROP STATEMENT FIRST AND AFTER A RUN UN COMMENT IT
			 */
			drop = stmt.executeUpdate("DROP Table RESULT");
			create = stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS RESULT (id varchar(10) NOT NULL, referee varchar(10) NOT NULL,winner varchar(10) NOT NULL,time int NOT NULL,winner2 varchar(10) NOT NULL ,time2 int NOT NULL,winner3 varchar(10) NOT NULL,time3 int NOT NULL)");
			System.out.println("Table created successfully");
			
			
			String sql = "INSERT INTO RESULT(id,referee,winner,time,winner2,time2,winner3,time3) VALUES(?,?,?,?,?,?,?,?)";
			try (Connection conn = DriverManager
					.getConnection("jdbc:sqlite:/Users/suyashkalyankar/Documents/workspace/AP2/src/Resources/Database.db");
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, ID);
	            pstmt.setString(2, referee);
	            pstmt.setString(3, winner);
	            pstmt.setInt(4, time);
	            pstmt.setString(5, runnerup);
	            pstmt.setInt(6, time2);
	            pstmt.setString(7, secondrunnerup);
	            pstmt.setInt(8, time3);
	            pstmt.executeUpdate();
	            System.out.println("SUCCESS");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}else{
			System.out.println("Check Database connection");
		}
		
	}   
	
	
	public static String participantType(String type){
		
		if(type.equals("sprinter") || type.equals("swimmer") || type.equals("cyclist") || type.equals("super")  ){
			return "athlete";
		}
		else if(type.equals("official")){
			return "referee";
		}else{
			System.out.println("Not able to fetch Participant");
			return "none";
		}
	}

	public void loadAthlete(Set<Athlete> athleteLoadList, String gameType) throws Exception, IOException {
		List<Athlete> currentList = new ArrayList<Athlete>(this.athleteLoadList);
		System.out.println("size of currentlist is " + currentList.size());
		System.out.println("gametype passed is " + gameType);
		for (int i = 0; i < currentList.size(); i++) {
			Athlete currentAthlete = (Athlete) currentList.get(i);
			String athleteType = currentAthlete.getAthleteType();
			if (gameType.toLowerCase().equals("swimming")) {

				if (athleteType.equals("swimmer") || athleteType.equals("super")) {

					athleteLoadList.add(currentAthlete);
					System.out.println(currentAthlete.getName());
				}
			}
			if (gameType.toLowerCase().equals("running")) {

				if (athleteType.equals("sprinter") || athleteType.equals("super")) {

					athleteLoadList.add(currentAthlete);
					System.out.println(currentAthlete.getName());
				}
			}
			if (gameType.toLowerCase().equals("cycling")) {

				if (athleteType.equals("cyclist") || athleteType.equals("super")) {

					athleteLoadList.add(currentAthlete);
					System.out.println(currentAthlete.getName());
				}
			}

		}
	}

}
