package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller implements Initializable{

    @FXML private Button start;

    private GuessInfo game = new GuessInfo();
    private Server serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    //Event listener for start server button being pressed
    public void startServer(ActionEvent e) throws IOException{

        //Switches scene to main server scene
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/scene2.fxml"));
        Stage primaryStage = (Stage) start.getScene().getWindow();

        primaryStage.setTitle("Word Guessing Game! (Server)");

        Scene s2 = new Scene(parent, 750,500);
        s2.getStylesheets().add("/styles/style.css");

        primaryStage.setScene(s2);
        primaryStage.show();
    }
}
