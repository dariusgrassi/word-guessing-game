package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MyController implements Initializable {

    @FXML
    private VBox firstBox;

    @FXML
    private VBox secondBox;

    @FXML
    private BorderPane thirdBox;

    @FXML
    private Button cat1, cat2, cat3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void connectToServer(ActionEvent e) throws IOException{
        // check that both textfields were filled

        // set the username
        // connect to server

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

        // go to the game screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style


        secondBox.getScene().setRoot( thirdBox);//update scene graph

    }

    public void cat2Clicked(ActionEvent e) throws IOException{
        // disable the button that was clicked
        cat2.setDisable(true);

        // Changing Screen to Game Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style
        secondBox.getScene().setRoot( thirdBox);//update scene graph

    }

    public void cat3Clicked(ActionEvent e) throws IOException{
        // disable the button that was clicked
        cat3.setDisable(true);
        // Changing Screen to Game Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameDisplay.fxml"));
        Parent thirdBox = loader.load(); //load view into parent
        thirdBox.getStylesheets().add("/style/gdStyle.css");//set style
        secondBox.getScene().setRoot( thirdBox);//update scene graph
    }


}
