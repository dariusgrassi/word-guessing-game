package application;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class Controller2 implements Initializable {

    private Server serverConnection;
    private GuessInfo game;

    @FXML private Label numConnected;
    @FXML private ListView<String> output;
    @FXML private TextArea scoreboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        output.getItems().add("Waiting for clients to connect...");

        //Starts an actual server with a run later
        serverConnection = new Server(data -> {

            //Infinite loop for checking if information has been updated
            Platform.runLater(()->{
                game = serverConnection.game;

                //update label with current number of clients
                numConnected.setText(String.valueOf(serverConnection.clients.size()));

                //update listview with information sent from server callback
                output.getItems().add(data.toString());
                output.scrollTo(output.getItems().size()-1);

                //update scoreboard based on current number of clients
                scoreboard.clear();
                for(Server.ClientThread c : serverConnection.clients){
                    scoreboard.appendText("Client " + c.count + ": " + "0\n");
                }
            });

        }, 5555);
    }



}
