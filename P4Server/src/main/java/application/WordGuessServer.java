package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WordGuessServer extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/MyFXML.fxml"));

			primaryStage.setTitle("Word Guessing Game! (Server)");

			Scene s1 = new Scene(root, 750,500);
			s1.getStylesheets().add("/styles/style.css");
			primaryStage.setScene(s1);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
