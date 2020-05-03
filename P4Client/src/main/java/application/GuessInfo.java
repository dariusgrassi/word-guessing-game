package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GuessInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    int wordLength;
    int remainingGuess;
    int correctWords;
    char guessedLetter;

    boolean correct;
    ArrayList<Integer> catChosen = new ArrayList<>();

    GuessInfo(){
    	// Assuming that for remaingingGuess we count upto 6
    	// Assuming that you start with 0 correct words
    	remainingGuess=correctWords=0;
    	
    }

}
