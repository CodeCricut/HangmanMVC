package model;

import controller.Controller;
import view.View;

public class Game {
    private static final int MAX_GUESSES = 20;
    private static int numOfGuesses;

    private static boolean gameRunning;
    private static boolean guessCorrect;

    private static Hangman hangmanApp;
    private static Controller controller;

    public static void main(String[] args) {
        gameRunning = true;
        numOfGuesses = 0;
        hangmanApp = new Hangman();
        controller = new Controller(hangmanApp, new View());

        setupGame();
        playGame();
    }

    private static void setupGame(){
        hangmanApp.setupDictionary();
        int wordLength = controller.getWordLength();
        hangmanApp.setWordLength(wordLength);
    }

    private static void playGame() {
        while (gameRunning){
            char mostCommonLetter = hangmanApp.getMostCommonLetter();
            if (controller.hasLetter(mostCommonLetter)){
                int[] letterPositions = controller.getLetterPositions();
                hangmanApp.updateCurrentGuess(mostCommonLetter, letterPositions);
                hangmanApp.eliminateWordsWithWrongLetters();
            }
            else{
                hangmanApp.addIncorrectLetter(mostCommonLetter);
            }
            numOfGuesses ++;
            updateGameState();
        }
        endGame();
    }

    private static void updateGameState(){
        if (numOfGuesses >= MAX_GUESSES){
            gameRunning = false;
        }
        if (hangmanApp.arrivedAtAnswer()){
            controller.guessWord();
            guessCorrect = controller.guessIsCorrect();
            gameRunning = false;
        }
    }

    private static void endGame() {
        if (guessCorrect)
            controller.printCorrectGuessEnding();
        else
            controller.printIncorrectGuessEnding();
    }



}
