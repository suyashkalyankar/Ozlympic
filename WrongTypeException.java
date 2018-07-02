package Exception;

/**
 * @author suyashkalyankar
 * 
 */
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class WrongTypeException extends Exception{
	
	public WrongTypeException(String Message){
		System.out.println("Select Athlete  of particular Game ");
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(300);
		window.setMinHeight(100);
		
		Label label = new Label();
		label.setText(Message);
		label.setStyle("-fx-background-color:Yellow");
		Button close = new Button("Close");
		
		close.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout,450,200);
		layout.setStyle("-fx-background-color:Red");
		window.setScene(scene);
		window.showAndWait();
	}

}
