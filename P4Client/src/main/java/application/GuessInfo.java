package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GuessInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    public int wordLength;
    public int remainingGuess;
    public int correctWords;
    public char guessedLetter;
    int lineOfWord;
    String user;

    public boolean end;
    boolean correct; // Assuming 
    ArrayList<Integer> catChosen = new ArrayList<>();
    

    public GuessInfo(){
    	// Assuming that for remaingingGuess we count upto 6  // decrease it for the display - P
    	// Assuming that you start with 0 correct words
    	remainingGuess=6;
        correctWords=wordLength=0;
        end = false;
    	
    }

}
