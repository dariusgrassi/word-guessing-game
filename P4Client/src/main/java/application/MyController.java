package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MyController implements Initializable {

    GuessInfo game = new GuessInfo();
    ClientThread clientConnection;

    @FXML
    private VBox firstBox;

    @FXML
    private VBox secondBox;

    @FXML
    private BorderPane thirdBox;

    @FXML
    private Button cat1, cat2, cat3;

    @FXML
    private Button a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;

    @FXML
    private TextField portNum, username;

    private ListView<String> listItems;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void connectToServer(ActionEvent e) throws IOException{
        // check that both textfields were filled
        String portN = portNum.getText();
        int port = Integer.parseInt( portN);
        String userName = username.getText();


        clientConnection = new ClientThread( data-> {
            Platform.runLater(()-> {
                //listItems.getItems().add(data.toString());
                
                game = clientConnection.game;

                //TODO: logic for handling updates from server, like guess responses
            });
        });




        // set the username
        // connect to server

        //clientConnection.run( port);

        // go to game menu
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameMenu.fxml"));
        Parent secondBox = loader.load(); //load view into parent
        secondBox.getStylesheets().add("/style/gmStyle.css");//set style


        firstBox.getScene().setRoot( secondBox);//update scene graph


    }

    public void cat1Clicked(ActionEvent e) throws IOException{
        // disable the button that was clicked
        cat1.setDisable( true);
        game.catChosen.add(1);

        // go to the game screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style


        secondBox.getScene().setRoot( thirdBox);//update scene graph

    }

    public void cat2Clicked(ActionEvent e) throws IOException{
        // disable the button that was clicked
        cat2.setDisable(true);
        game.catChosen.add(2);

        // Changing Screen to Game Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style
        secondBox.getScene().setRoot( thirdBox);//update scene graph

    }

    public void cat3Clicked(ActionEvent e) throws IOException{
        // disable the button that was clicked
        cat3.setDisable(true);
        game.catChosen.add(3);
        // Changing Screen to Game Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style
        secondBox.getScene().setRoot( thirdBox);//update scene graph

    }

    public void letterClicked(ActionEvent e) {
        ((Button)e.getSource()).setDisable(true);       // Disable character button
        ((Button)e.getSource()).setStyle("-fx-background-color:Black;");
        String val = ((Button)e.getSource()).getText(); // Get the name of button
        game.guessedLetter = val.charAt(0);             // set the name to guessed letter
        
        // if the person guesses wrong, then it returns to the main menu
        if( !game.correct) {

            game.remainingGuess -= 1;
            //TODO: Got to add functionality for counting how many categories are wrong
            // when the remaining guesses reaches 0 the game ends
        }

    }


}
