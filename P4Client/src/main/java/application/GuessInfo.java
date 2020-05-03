package application;

import java.io.Serializable;
import java.util.ArrayList;

public class GuessInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    int wordLength;
    int remainingGuess;
    int correctWords;
    char guessedLetter;
    String message;

    boolean correct;
    ArrayList<Integer> catChosen = new ArrayList<>();


}
