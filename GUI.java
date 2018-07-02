
/**
 * @author Ashish bhardwaj  
 * This class is where all the user interaction is happening
 * Integrated whole code with backend
 * @author Suyash Kalyankar
 * Designed the  GUI of game
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import Backend.DataBaseConnection;
import Backend.FileDataBaseReader;
import Exception.GameFullException;
import Exception.NoDatabaseException;
import Exception.NoRefereeException;
import Exception.TooFewAthleteException;
import Exception.WrongTypeException;
import gameClasses.Athlete;
import gameClasses.Cycling;
import gameClasses.Game;
import gameClasses.Referee;
import gameClasses.Running;
import gameClasses.Swimming;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {

	public Stage firstStage, secondStage, thirdStage, fourthStage, fifthStage, sixthStage, seventhStage;
	public BorderPane pane, pane3;
	public GridPane pane1, pane2;
	public FlowPane pane4, pane5, pane6;
	public MenuBar menuBar;
	public HBox bottom;
	public Menu menu;
	public Menu menu1;
	public MenuItem menuItem, menuItem1, menuItem2;
	public Scene scene, scene1, scene2, scene3, scene4, scene5, scene6;
	public Button btnRun, btnCycle, btnSwim, btnExit1, btnClear, btnBack1, btnRef, btnLoad, btnExit2, btnExit3,
			btnStart, btnResult, btnResultall, btnExit4, btnRefClear;
	File file = new File("/Users/suyashkalyankar/Documents/workspace/AP2/src/Resources/gameResult.txt");
	public Set<Athlete> athleteLoadList = new HashSet<Athlete>();
	public ArrayList<Athlete> all;
	public ArrayList<Referee> allReferee;
	public Game game;
	boolean value = false;
	public ArrayList<Game> gameList = new ArrayList<Game>();
	public Athlete athlete;
	public ArrayList<String> athleteNames = new ArrayList<String>();
	public ArrayList<String> refereeNames = new ArrayList<String>();
	public int counter = 0;
	public ObservableList<String> selected = FXCollections.observableArrayList();
	public ObservableList<String> assignRef = FXCollections.observableArrayList();
	public Object candidates;
	public ArrayList<Athlete> selectedAthlete = new ArrayList<Athlete>();
	public DataBaseConnection db = new DataBaseConnection();
	public FileDataBaseReader fdr;
	public String refName;
	


	/**
	 * Checks DataBase connection If databse available it use that else file
	 * reading
	 * 
	 * @return boolean values
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean data() throws IOException, SQLException {
		if (db.checkConnection() == true) {
			System.out.println("Database connection is ok");
			return true;
		} else {

			System.out.println("Database connection is not connected");
			return false;
		}
	}

	private void setRefName(String assigned) {
		this.refName = assigned;
	}

	private String getRefName() {
		return this.refName;
	}

	public Set<Athlete> getAthleteList() {
		return athleteLoadList;
	}

	public void setGameList(ArrayList<Game> gameList) {
		this.gameList = gameList;
	}

	public ArrayList<Game> getGameList() {
		return this.gameList;
	}

	public void setList(ObservableList<String> selected2) {
		this.selected = selected2;
	}

	public ObservableList<String> getList() {
		return this.selected;
	}

	public ArrayList<Athlete> getAll() {
		return this.all;
	}

	public void setAll(ArrayList<Athlete> all) {
		this.all = all;
	}

	public ArrayList<Referee> getAllReferee() {
		return allReferee;
	}

	public void setAllReferee(ArrayList<Referee> allReferee) {
		this.allReferee = allReferee;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		firstStage = primaryStage;

		/*
		 * Border Pane of primary Stage
		 */
		pane = new BorderPane();
		/*
		 * Menu Bar for the user to know more about game and help
		 */

		menuBar = new MenuBar();
		menu = new Menu("GAME MENU");
		menu1 = new Menu("HELP");
		menuItem = new MenuItem("ABOUT GAME");
		menuItem1 = new MenuItem("RULES");
		menuItem2 = new MenuItem("FAQ");

		menuItem.setOnAction(e -> {
			about();
		});

		menuItem1 = new MenuItem("RULES");
		menuItem1.setOnAction(e -> {
			rule();
		});

		menuItem2 = new MenuItem("FAQ");
		menuItem2.setOnAction(e -> {
			help();
		});

		menu.getItems().addAll(menuItem, menuItem1);
		menu1.getItems().add(menuItem2);
		menuBar.getMenus().addAll(menu, menu1);

		menuBar.setStyle("-fx-background-color:Red; fx-font-weight: bold");

		pane.setTop(menuBar);

		/*
		 * Place Nodes in the Pane
		 */
		pane.setCenter(getCentre());
		pane.setBottom(getBottom());

		scene = new Scene(pane, 800, 500, Color.WHITESMOKE);
		firstStage.setTitle("Ozlympic Game");
		firstStage.setScene(scene);
		firstStage.show();
	}

	/**
	 * 
	 * @return the HBOX pane which consists of Image and Button for selecting
	 *         the game
	 * @throws IOException
	 * @throws URISyntaxException
	 * 
	 */
	public HBox getCentre() throws IOException, URISyntaxException {
		HBox centre = new HBox(30);
		centre.setPadding(new Insets(50, 30, 30, 30));
		centre.setStyle("-fx-background-color:Black");

		Image img = new Image(getClass().getResource("/Resources/running.gif").toURI().toString());
		ImageView imageView1 = new ImageView(img);
		imageView1.setFitHeight(300);
		imageView1.setFitWidth(200);

		Image img2 = new Image(getClass().getResource("/Resources/cyclist.gif").toURI().toString());
		ImageView imageView2 = new ImageView(img2);
		imageView2.setFitHeight(300);
		imageView2.setFitWidth(200);

		Image img3 = new Image(getClass().getResource("/Resources/swimming.gif").toURI().toString());
		ImageView imageView3 = new ImageView(img3);
		imageView3.setFitHeight(300);
		imageView3.setFitWidth(200);

		btnRun = new Button("Running", imageView1);
		btnRun.setStyle("-fx-base: green; fx-font-weight: bold");
		btnRun.setContentDisplay(ContentDisplay.TOP);
		btnRun.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btnCycle = new Button("Cycling", imageView2);
		btnCycle.setStyle("-fx-base: blue; fx-font-weight: bold");
		btnCycle.setContentDisplay(ContentDisplay.TOP);
		btnCycle.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btnSwim = new Button("Swimming", imageView3);

		btnSwim.setStyle("-fx-base: yellow; fx-font-weight: bold");
		btnSwim.setContentDisplay(ContentDisplay.TOP);
		btnSwim.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		centre.getChildren().add(btnRun);
		centre.getChildren().add(btnCycle);
		centre.getChildren().add(btnSwim);
		centre.getChildren().add(imageView1);
		centre.getChildren().add(imageView2);
		centre.getChildren().add(imageView3);
		return centre;

	}

	/**
	 * @oaram SelectGame Action Event - will show result to user
	 * @param SelectGame
	 *            Action Event - Will check number of players in game
	 * @param SelectGame
	 *            Action Event - will clear selected list
	 * @param SelectGame
	 *            Action Event - will show list of athletes after selecting game
	 * @param SelectGame
	 *            Action Event - will also go back to the main first stage after
	 *            clicking back button
	 * @throws Exception
	 * @throws IOException
	 */
	public void ButtonClicked(ActionEvent SelectGame) throws IOException, Exception {
		if (SelectGame.getSource() == btnRun) {
			if (data() == true) {
				db.loadAthlete(athleteLoadList, "running");
			} else {
				try {
					throw new NoDatabaseException(
							"NoDatabaseException : DataBase not Connected : Either comment drop table Line or Click ok to load from file");

				} catch (NoDatabaseException e1) {
					// TODO Auto-generated catch block

				}
				fdr.getFileDataBaseReader().loadAthlete(athleteLoadList, "running");
			}

			System.out.println("size of athleteloadlist while button clicked is " + athleteLoadList.size());
			this.game = new Game(new Running(), athleteLoadList);
			List();
			ListInititate();
			secondStage.showAndWait();
		} else if (SelectGame.getSource() == btnCycle) {
			if (data() == true) {
				db.loadAthlete(athleteLoadList, "cycling");
			} else {
				try {
					throw new NoDatabaseException(
							"NoDatabaseException : DataBase not Connected : Either comment drop table Line or Click ok to load from file");

				} catch (NoDatabaseException e1) {
					// TODO Auto-generated catch block

				}
				fdr.getFileDataBaseReader().loadAthlete(athleteLoadList, "cycling");
			}
			this.game = new Game(new Cycling(), athleteLoadList);
			List();
			ListInititate();
			secondStage.showAndWait();
		} else if (SelectGame.getSource() == btnSwim) {
			if (data() == true) {
				db.loadAthlete(athleteLoadList, "swimming");
			} else {
				try {
					throw new NoDatabaseException(
							"NoDatabaseException : DataBase not Connected : Either comment drop table Line or Click ok to load from file");

				} catch (NoDatabaseException e1) {
					// TODO Auto-generated catch block

				}
				fdr.getFileDataBaseReader().loadAthlete(athleteLoadList, "swimming");
			}
			this.game = new Game(new Swimming(), athleteLoadList);
			List();
			ListInititate();
			secondStage.showAndWait();
		} else if (SelectGame.getSource() == btnClear) {
			this.selected.clear();
			this.athleteNames.clear();
			ListInititate();
		} else if (SelectGame.getSource() == btnRef) {
			if (this.selected.size() < 4) {
				try {
					throw new TooFewAthleteException("TooFewAthleteException : Select Minimum 4 players to start Game");

				} catch (TooFewAthleteException e1) {
					// TODO Auto-generated catch block

				}

			} else if (this.selected.size() > 8) {
				try {
					throw new GameFullException("GameFullException : Cannot Select More than 8 Players");

				} catch (GameFullException e1) {
					// TODO Auto-generated catch block

				}
			} else {
				listRefree();
			}

		} else if (SelectGame.getSource() == btnRefClear) {
			this.refName = null;
			this.assignRef.clear();
		} else if (SelectGame.getSource() == btnResultall) {
			clearField();
			if (data() == true) {
				resultData();
			} else {
				resultAllShow();
			}

		} else if (SelectGame.getSource() == btnResult) {
			clearField();
			resultShow();
		} else if (SelectGame.getSource() == btnBack1) {
			this.selected.clear();
			this.athleteLoadList.clear();
			this.athleteNames.clear();
			this.game = null;
			secondStage.close();
		} else {
			secondStage.close();
		}

	}

	/**
	 * This will clear textbox to show result from all games
	 * 
	 * @throws IOException
	 */
	private void clearField() throws IOException {
		Result();
		VBox Box3 = new VBox();
		TextArea textbox = new TextArea();
		textbox.setPadding(new Insets(10));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(textbox);
		textbox.clear();
		Box3.getChildren().addAll(textbox, scrollPane);
		pane3.setCenter(Box3);
		fourthStage.show();

	}

	/**
	 * this will show result of all games from file to user
	 * 
	 * @throws IOException,SQLException,
	 *             InstantiationException, IllegalAccessException,
	 *             ClassNotFoundException
	 */
	private String showAllResult()
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}

		fileReader.close();
		System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
	}

	/**
	 * 
	 * @return Label for user to Select the game and if user want to quit the
	 *         game click on Exit
	 */

	private HBox getBottom() {
		bottom = new HBox(20);
		bottom.getChildren().add(new Label("SELECT GAME TO RUN"));
		btnExit1 = new Button("Exit");
		btnExit1.setTranslateX(580);
		btnExit1.setOnAction(e -> System.exit(0));
		btnExit1.setStyle("-fx-background-color:yellow");
		bottom.getChildren().add(btnExit1);
		bottom.setStyle("-fx-background-color:CornFlowerBlue");

		return bottom;

	}

	/**
	 * Here this will f result to database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public void writeDataResult() throws ClassNotFoundException, SQLException {

		String referee = this.refName;
		String gameId = this.game.getGameID();
		String winner1 = this.game.getWinnerName();
		int time = this.game.getWinnerTime();
		String winner2 = this.game.getRunnerUpName();
		int time2 = this.game.getRunnerUpTime();
		String winner3 = this.game.getSecondRunnerUpName();
		int time3 = this.game.getSecondRunnerUpTime();

		db.writeDataBase(gameId, referee, winner1, time, winner2, time2, winner3, time3);

	}

	/**
	 * this will write result to file
	 * 
	 * @param gameL
	 *            passing list of game objects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void writeResult(ArrayList<Game> gameL) throws ClassNotFoundException, SQLException {

		System.out.println("inside writeresult method");
		System.out.println("size of gameList" + gameL);
		BufferedWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			FileWriter fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			List temp = new ArrayList(gameL);
			for (int i = 0; i < temp.size(); i++) {
				Game tempG = (Game) temp.get(i);
				System.out.println("Game Results are " + tempG.getGameID());
				System.out.println("  " + tempG.getWinnerName());
				System.out.println(" " + tempG.getWinnerTime());
				bw.newLine();
				bw.write(tempG.getGameID());
				bw.write(" , ");
				bw.write(this.getRefName());
				bw.write(" , ");
				bw.write(dateFormat.format(date));
				bw.write('\n');
				bw.write(tempG.getWinnerName());
				bw.write(" , ");
				bw.write(String.valueOf(tempG.getWinnerTime()));
				bw.write(" , ");
				bw.write(String.valueOf(5));
				bw.write(" , ");
				bw.write('\n');
				bw.write(tempG.getRunnerUpName());
				bw.write(" , ");
				bw.write(String.valueOf(tempG.getRunnerUpTime()));
				bw.write(" , ");
				bw.write(String.valueOf(2));
				bw.write(" , ");
				bw.write('\n');
				bw.write(tempG.getSecondRunnerUpName());
				bw.write(" , ");
				bw.write(String.valueOf(tempG.getSecondRunnerUpTime()));
				bw.write(" , ");
				bw.write(String.valueOf(1));
				bw.write(" , ");
				bw.write('\n');

			}

			System.out.println("File written Successfully");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}
	}

	/**
	 * this will check conditions whether particular athlete is of particular
	 * type or not
	 * 
	 * @param a
	 *            passes string
	 * @return boolean value
	 */
	public boolean conditionCheck(String a) {
		boolean check = false;
		String[] arr = a.split("  ::-> ");
		String name = null;
		String type = null;
		String state = null;
		for (String aa : arr) {

			name = arr[0];
			type = arr[1];
			state = arr[2];
		}

		List currentList = new ArrayList(this.athleteLoadList);
		for (int i = 0; i < this.athleteLoadList.size(); i++) {
			Athlete currentAthlete = (Athlete) currentList.get(i);
			String aname = currentAthlete.getName();
			String atype = currentAthlete.getAthleteType();
			String astate = currentAthlete.getState();
			if (name.contains(aname) && type.contains(atype) && state.contains(astate)) {
				check = true;
			} else {
				continue;
			}
		}
		return check;
	}

	/**
	 * matches and changes string arraylist to list of objects by comparing
	 * their attributes
	 * 
	 * @param selected2
	 *            list of string
	 * @return list of athlete objects
	 */
	public ArrayList<Athlete> getAthleteObjects(ObservableList<String> selected2) {
		List currentList = new ArrayList(this.selected);
		List currentList1 = new ArrayList(this.athleteLoadList);
		for (int i = 0; i < athleteLoadList.size(); i++) {
			Athlete currentAthlete = (Athlete) currentList1.get(i);
			String name = currentAthlete.getName();
			String type = currentAthlete.getAthleteType();
			String state = currentAthlete.getState();
			for (int j = 0; j < selected.size(); j++) {
				String buffer = selected.get(j);
				String aname = buffer.split("  ::-> ")[0];
				String atype = buffer.split("  ::-> ")[1];
				String astate = buffer.split("  ::-> ")[2];
				if (name.equals(aname) && type.equals(atype) && state.equals(astate)) {
					this.selectedAthlete.add(currentAthlete);
				}
			}
		}
		return this.selectedAthlete;

	}

	/**
	 * Shows list of Athletes
	 */
	public void List() {
		pane1 = new GridPane();
		pane1.setPadding(new Insets(5));
		pane1.setStyle("-fx-background-color:Black");
		pane1.setHgap(10);
		pane1.setVgap(10);
		scene1 = new Scene(pane1, 1000, 1000, Color.WHITESMOKE);
		secondStage = new Stage();
		secondStage.setScene(scene1);
		secondStage.initModality(Modality.APPLICATION_MODAL);
		secondStage.setTitle("List Of Athletes");
		btnBack1 = new Button("Back");
		btnClear = new Button("CLEAR");
		btnExit2 = new Button("Exit");
		btnExit2.setStyle("-fx-background-color:Red");
		btnRef = new Button("Refree");
		btnRef.setStyle("-fx-background-color: Green; fx-font-weight: bold;");

		GridPane.setValignment(btnBack1, VPos.BOTTOM);
		GridPane.setValignment(btnExit2, VPos.BOTTOM);
		pane1.add(btnBack1, 0, 8);
		pane1.add(btnClear, 2, 4);
		pane1.add(btnExit2, 3, 8);
		pane1.add(btnRef, 1, 8);
		btnRef.setOnAction((e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));
		btnClear.setOnAction((e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));
		btnBack1.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btnExit2.setOnAction(e -> System.exit(0));
		ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		pane1.getColumnConstraints().addAll(column1, column2, column3);
		Label Participant = new Label("PARTICIPANTS");
		Participant.setStyle("-fx-background-color: yellow; fx-font-weight: bold;");
		GridPane.setHalignment(Participant, HPos.CENTER);
		pane1.add(Participant, 0, 0);

		Label selectedLbl = new Label("SELECTED");
		selectedLbl.setStyle("-fx-background-color: yellow; fx-font-weight: bold;");
		pane1.add(selectedLbl, 2, 0);
		GridPane.setHalignment(selectedLbl, HPos.CENTER);

	}

	/**
	 * Generates list of Athletes from database or file
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public void ListInititate() throws IOException, SQLException {

		if (data() == true) {
			ArrayList<Athlete> all = new ArrayList(db.getAthleteLoadList());
			setAll(all);
		} else {
			ArrayList<Athlete> all = new ArrayList(fdr.getFileDataBaseReader().getAthleteLoadList());
			setAll(all);
		}

		List currentList = new ArrayList(this.all);
		for (int i = 0; i < all.size(); i++) {
			Athlete currentAthlete = (Athlete) currentList.get(i);
			athleteNames.add(currentAthlete.getName() + "  ::-> " + currentAthlete.getAthleteType() + "  ::-> "
					+ currentAthlete.getState());
		}
		ObservableList<String> candidates = FXCollections.observableArrayList(this.athleteNames);
		ListView<String> canListView = new ListView<>(candidates);
		pane1.add(canListView, 0, 1);

		ObservableList<String> selected = FXCollections.observableArrayList();
		ListView<String> ListView = new ListView<>(selected);
		pane1.add(ListView, 2, 1);

		Button sendRightButton = new Button(" > ");
		sendRightButton.setOnAction((ActionEvent event) -> {
			String potential = canListView.getSelectionModel().getSelectedItem();
			if (conditionCheck(potential) == true) {
				System.out.println("Its here inside if potential");
				if (potential != null) {
					canListView.getSelectionModel().clearSelection();
					candidates.remove(potential);
					selected.addAll(potential);
					setList(selected);
				} else {
					System.out.println("THROW EXCEPTION HERE");
				}

			} else {
				// ** WRONG TYPE EXCEPTION HANDLING**
				try {
					throw new WrongTypeException("WrongTypeException : Select the Correct Type of Athlete");
				} catch (WrongTypeException e) {

				}
			}
		});
		Button sendLeftButton = new Button(" < ");
		sendLeftButton.setOnAction((ActionEvent event) -> {
			String s = ListView.getSelectionModel().getSelectedItem();
			if (s != null) {
				ListView.getSelectionModel().clearSelection();
				selected.remove(s);
				candidates.add(s);
			}
		});

		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(sendRightButton, sendLeftButton);

		pane1.add(vbox, 1, 1);
	}

	public void backtofirst(ActionEvent e) {
		if (e.getSource() == btnBack1) {

			firstStage.show();
		}
	}

	/**
	 * will show list of Referees to user
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */

	public void listRefree() throws IOException, SQLException {

		pane2 = new GridPane();
		pane2.setStyle("-fx-background-color:Black");
		pane2.setPadding(new Insets(5));
		scene2 = new Scene(pane2, 1000, 1000);

		thirdStage = new Stage();
		thirdStage.setScene(scene2);
		thirdStage.setTitle("List Of Refree");

		thirdStage.initModality(Modality.APPLICATION_MODAL);
		btnExit3 = new Button("Exit");
		btnExit3.setStyle("-fx-background-color:Red");
		btnStart = new Button("Start");
		btnStart.setStyle("-fx-background-color: Green; fx-font-weight: bold;");
		btnRefClear = new Button("Clear");
		pane2.add(btnExit3, 3, 10);
		pane2.add(btnStart, 1, 12);
		pane2.add(btnRefClear, 2, 3);

		btnExit3.setOnAction(e -> System.exit(0));

		btnStart.setOnAction(e -> {
			try {
				if (this.refName == null) {

					try {
						throw new NoRefereeException("NoRefereeException :Select Refree to Start the Game");

					} catch (NoRefereeException e1) {
						// TODO Auto-generated catch block

					}
				} else {
					getAthleteObjects(selected);
					this.game.setSelectedAthleteList(this.selectedAthlete);
					this.game.initiateGame();
					this.game.rewardWinner();
					this.gameList.add(this.game);
					if (data() == true) {
						writeDataResult();
					} else {
						writeResult(gameList);
					}

					resultShow();

				}
			} catch (IOException | ClassNotFoundException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnRefClear.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		ColumnConstraints column1 = new ColumnConstraints(50, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(100);
		ColumnConstraints column3 = new ColumnConstraints(50, 150, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		pane2.getColumnConstraints().addAll(column1, column2, column3);
		Label Refree = new Label("REFEREE");
		Refree.setStyle("-fx-background-color: yellow; fx-font-weight: bold;");
		GridPane.setHalignment(Refree, HPos.CENTER);
		pane2.add(Refree, 0, 0);

		Label assign = new Label("ASSIGNED");
		assign.setStyle("-fx-background-color: yellow; fx-font-weight: bold;");
		pane2.add(assign, 2, 0);
		if (data() == true) {
			ArrayList<Referee> allReferee = new ArrayList(db.getRefereeList());
			setAllReferee(allReferee);
		} else {
			ArrayList<Referee> allReferee = new ArrayList(fdr.getFileDataBaseReader().getRefereeList());
			setAllReferee(allReferee);
		}

		List currentList = new ArrayList(allReferee);
		for (int i = 0; i < allReferee.size(); i++) {
			Referee currentRef = (Referee) currentList.get(i);
			refereeNames.add(currentRef.getName());
		}
		ObservableList<String> official = FXCollections.observableArrayList(refereeNames);
		ListView<String> Refreelist = new ListView<>(official);
		pane2.add(Refreelist, 0, 1);

		ListView<String> AssignView = new ListView<>(assignRef);
		pane2.add(AssignView, 2, 1);
		Button select = new Button(" > ");
		select.setOnAction((ActionEvent event) -> {
			String assigned = Refreelist.getSelectionModel().getSelectedItem();
			if (assigned != null) {
				Refreelist.getSelectionModel().clearSelection();

				if (checkSize(assignRef) == true) {
					this.assignRef.remove(assigned);
					this.assignRef.add(assigned);
				}
				setRefName(assigned);
			}
		});

		HBox Hbox1 = new HBox(15);
		Hbox1.getChildren().addAll(select);

		pane2.add(Hbox1, 1, 1);

		thirdStage.show();

	}

	/**
	 * checks the size of referee selected list
	 * 
	 * @param assignRef2
	 * @return boolean value
	 */
	public boolean checkSize(ObservableList<String> assignRef2) {
		boolean check = false;
		if (assignRef2.size() < 2 && assignRef2.size() < 0) {
			check = false;
		} else if (assignRef2.size() == 0) {
			check = true;
		}
		return check;
	}

	/**
	 * Show results of game in textbox in GUI
	 * 
	 * @throws IOException
	 */
	public void resultShow() throws IOException {
		Result();
		VBox Box3 = new VBox();
		TextArea textbox = new TextArea();
		textbox.setPadding(new Insets(10));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(textbox);

		textbox.setText("GAME ID  ::  " + this.game.getGameID() + " \n" + " Official Name ::  " + refName + " \n"
				+ " Winner Name ::  " + this.game.getWinnerName() + " \n " + " Winner Time :: " + " "
				+ this.game.getWinnerTime() + " \n " + " RunnerUp Name :: " + this.game.getRunnerUpName() + "\n "
				+ " RunnerUp Time :: " + this.game.getRunnerUpTime() + " \n " + " Second RunnerUp Name :: "
				+ this.game.getSecondRunnerUpName() + " \n " + " Second RunnerUp  :: "
				+ this.game.getSecondRunnerUpTime());
		Box3.getChildren().addAll(textbox, scrollPane);
		pane3.setCenter(Box3);
		fourthStage.show();
	}

	/**
	 * Shows result of all games in GUI
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void resultAllShow()
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Result();
		VBox Box3 = new VBox();
		TextArea textbox = new TextArea();
		textbox.setPadding(new Insets(10));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(textbox);
		textbox.setText(showAllResult());
		Box3.getChildren().addAll(textbox, scrollPane);
		pane3.setCenter(Box3);
		fourthStage.show();
	}

	/**
	 * Shows result of Games in GUI from database
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void resultData()
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Result();
		VBox Box3 = new VBox();
		TextArea textbox = new TextArea();
		textbox.setPadding(new Insets(10));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(textbox);
		textbox.setText(db.readResult());
		Box3.getChildren().addAll(textbox, scrollPane);
		pane3.setCenter(Box3);
		fourthStage.show();
	}

	/**
	 * Tell user about game
	 */
	public void about() {
		pane4 = new FlowPane();
		pane4.setStyle("-fx-background-color:LightBlue");
		fifthStage = new Stage();
		scene4 = new Scene(pane4, 400, 400);
		fifthStage.setTitle("About Game");
		fifthStage.setScene(scene4);
		Text text1 = new Text();
		text1.setText("Ozlympic Game  " + "\n" + "Created By :-  Ashish Bhardwaj & Suyash Kalyankar");
		text1.setStyle("-fx-font-size: 10pt");
		pane4.setAlignment(Pos.CENTER);
		pane4.getChildren().add(text1);
		fifthStage.showAndWait();

	}

	// METHOD FOR RULES
	public void rule() {
		pane5 = new FlowPane();
		sixthStage = new Stage();
		scene5 = new Scene(pane5, 600, 400);
		pane5.setStyle("-fx-background-color:LightBlue");
		sixthStage.setTitle("Game Rules");
		sixthStage.setScene(scene5);
		Text text1 = new Text();
		text1.setText("Rules of the Game :-" + "\n" + "1. Select Game to play" + "\n"
				+ "2. Select atleast 4 players to play the game" + "\n" + "3. Assign Official for a game " + "\n"
				+ "4. Start The Game");
		text1.setStyle("-fx-font-size: 20pt");
		pane5.setAlignment(Pos.CENTER);
		pane5.getChildren().add(text1);
		sixthStage.showAndWait();
	}

	// METHOD FOR HELP(FAQ)
	public void help() {
		pane6 = new FlowPane();
		pane6.setStyle("-fx-background-color:LightBlue");
		seventhStage = new Stage();
		seventhStage.setTitle("FAQ");
		scene6 = new Scene(pane6, 800, 600);
		seventhStage.setScene(scene6);
		Text text1 = new Text();
		text1.setText("FAQ:- " + "\n" + "1. Can user go again back to select game from list of athletes and Official?"
				+ "\n" + "-> Yes User can go back to select Game again" + "\n" + "2. How many Athlete can user select?"
				+ "\n" + "-> User can select upto minimum of 4 Athletes and maximum of 8 Athletes" + "\n"
				+ "3. How Many officials can be assign for a Game?" + "\n"
				+ "-> Only 1 official can be assigned for a game." + "\n" + "4. Where Can I Find gameresult.txt File ?"
				+ " \n" + " -> You can find that file in your Resources folder");
		text1.setStyle("-fx-font-size: 15pt");
		pane6.setAlignment(Pos.CENTER);
		pane6.getChildren().add(text1);
		seventhStage.showAndWait();
	}

	public void Result() throws IOException {

		pane3 = new BorderPane();
		pane3.setStyle("-fx-background-color:Black");
		pane3.setPadding(new Insets(5));
		scene3 = new Scene(pane3, 400, 400);
		fourthStage = new Stage();
		fourthStage.initModality(Modality.APPLICATION_MODAL);
		fourthStage.setScene(scene3);
		fourthStage.setTitle("OZLYMPIC GAME");

		TextField result = new TextField();
		result.setText(refName);
		HBox HBox1 = new HBox(20);
		btnResult = new Button("Result of game");
		btnResultall = new Button("Result of All Games");

		HBox1.getChildren().addAll(btnResult, btnResultall);
		pane3.setTop(HBox1);

		btnResult.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnResultall.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		HBox HBox2 = new HBox();
		btnExit4 = new Button("Exit");
		btnExit4.setStyle("-fx-background-color:Red");
		HBox2.getChildren().addAll(btnExit4);
		pane3.setBottom(HBox2);

		btnExit4.setOnAction(e -> System.exit(0));

	}

}
