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
    private Button a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z, end;

    @FXML
    private TextField portNum, username;

    private ListView<String> listItems;
    
    @FXML
    private Label lblRemain;


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
              System.out.println("Froze inside runLater");
              game.user = userName;
                //listItems.getItems().add(data.toString());
              clientConnection.send(game);

                //TODO: logic for handling updates from server, like guess responses
            });
        }, port);                

        game.user = userName; // Doesnt override game info
//        clientConnection.game = game;
        System.out.println(userName);
        // set the username
        // connect to server

        clientConnection.start();

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

    public void letterClicked(ActionEvent e) throws IOException {
        ((Button)e.getSource()).setDisable(true);       // Disable character button
        ((Button)e.getSource()).setStyle("-fx-background-color:Black;");
        String val = ((Button)e.getSource()).getText(); // Get the name of button
        game.guessedLetter = val.charAt(0);             // set the name to guessed letter
        
        
        // TODO: Update game to server, Get Server response
        System.out.println("froze at send");
        clientConnection.send(game);
        System.out.println("Made it past send");
//		game = clientConnection.recieve();

        
        System.out.println("User " + game.user + " clicked " + game.guessedLetter);
        

//        System.out.println("Counting down: " +game.remainingGuess);
        
        // if the person guesses wrong, then it returns to the main menu
        if(game.remainingGuess >= 0) {   	
            game.remainingGuess -= 1;
            System.out.println("Counting down: " + game.remainingGuess);
//            lblRemain.setText("Guesses Remaining: " + game.remainingGuess);
             
            
            if(game.correct) {
            	back();
            }
            if(game.remainingGuess == 0) { // Lost a category
            	//TODO: If there is anything client side that has to happen
            	// 	when losing, do here IMPORTANT, THIS SAME QUESTION IS IN BACKACTION90
            	game.remainingGuess = 6;
            	back();
            }
        }
        

    }
    
    
    public void backAction(ActionEvent e) {
    	try {
    		//TODO: If there is anything client side that has to happen
        	// 	when losing, do here IMPORTANT, THIS SAME QUESTION IS IN BACKACTION90 letterclick()
    		game.remainingGuess = 6;
			back();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    public void back() throws IOException {
    	// go to game menu
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameMenu.fxml"));
        Parent secondBox = loader.load(); //load view into parent
        secondBox.getStylesheets().add("/style/gmStyle.css");//set style

        thirdBox.getScene().setRoot( secondBox);//update scene graph	
        
        // Counts the categories and decides if it should be disabled upon return
        int one,two,three;
        one=two=three=0;
        for(int i=0; i < game.catChosen.size(); i++) {
        	switch(game.catChosen.get(i)) {
        	case 1:
        		one += 1;
        		break;
        	case 2:
        		two += 1;
        		break;
        	case 3:
        		three +=1;
        		break;
        	}
        }
        if (three >= 3) {
        	cat3.setDisable(true);
        	cat3.setStyle("-fx-background-color: Black");
        }
        if(two >= 3) {
        	cat2.setDisable(true);
        	cat2.setStyle("-fx-background-color: Black");
        }
        if(one >= 1) {
        	cat1.setDisable(true);
        	cat1.setStyle("-fx-background-color: Black");
        }
    }
    
    // Ends Program
    public void ending(ActionEvent e) throws IOException {
    	// TODO: SCORE SHOULD BE SHOWN
    	clientConnection.ending();
    }


}
