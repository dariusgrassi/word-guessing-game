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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller implements Initializable{

    @FXML
    private Button start;

    @FXML
    private ListView<String> listItems;

    private Stage primaryStage;
    private GuessInfo game;
    private Server serverConnection;

    public Controller() {
        game = new GuessInfo();
        primaryStage = new Stage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    //Event listener for start server button being pressed
    public void startServer(ActionEvent e) throws IOException{

        //Switches scene to main server scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/scene2.fxml"));

            primaryStage.setTitle("Word Guessing Game! (Server)");

            Scene s2 = new Scene(root, 750,500);
            s2.getStylesheets().add("/styles/style.css");
            primaryStage.setScene(s2);
            primaryStage.show();

            //Starts an actual server with a run later
            serverConnection = new Server(data -> {

                //Infinite loop for checking if information has been updated
                Platform.runLater(()->{
                    game = serverConnection.game;

                    listItems.getItems().add(data.toString());
                    listItems.scrollTo(listItems.getItems().size()-1);
                });

            }, 5555);

        } catch(Exception c) {
            c.printStackTrace();
            System.exit(1);
        }


    }
}
