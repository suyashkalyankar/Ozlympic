package Exception;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoDatabaseException extends Exception {

	public NoDatabaseException(String Message){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(300);
		window.setMinHeight(100);
		
		Label label = new Label();
		label.setText(Message);
		label.setStyle("-fx-background-color:Yellow");
		Button close = new Button("OK");
		
		close.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
	
		
		Scene scene = new Scene(layout,600,400);
		layout.setStyle("-fx-background-color:Red");
		
		window.setScene(scene);
		window.showAndWait();
		
	}

	}

