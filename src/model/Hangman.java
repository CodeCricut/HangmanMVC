package model;

import edu.duke.FileResource;
import util.Alphabet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Hangman {

    private HashSet<String> dictionary;
    private char[] currentGuess;

    private ArrayList<Character> incorrectGuesses = new ArrayList<>();
    private ArrayList<Character> guessedCharacters = new ArrayList<>();

    public void setupDictionary(){
        dictionary = new HashSet<>();
        FileResource fileResource = new FileResource("dictionary/words.txt");
        for (String line : fileResource.lines()){
            line = line.toLowerCase();
            dictionary.add(line);
        }
    }

    public char getMostCommonLetter(){
        ArrayList<Integer> unguessedLetterPositions = new ArrayList<>();
        for (int letterPosition = 0; letterPosition < currentGuess.length; letterPosition++){
            if (currentGuess[letterPosition] == '\u0000'){
                unguessedLetterPositions.add(letterPosition);
            }
        }

        HashMap<Character, Integer> letterOccurrences = new HashMap<>();
        for (String word : dictionary){
            for (int unguessedLetterPosition : unguessedLetterPositions){
                char letterAtUnguessedPosition = word.charAt(unguessedLetterPosition);
                if (! (guessedCharacters.contains(letterAtUnguessedPosition))){
                    Alphabet.addLetterInstance(letterOccurrences, letterAtUnguessedPosition);
                }
            }
        }
        char mostCommonLetter = Alphabet.getMostCommonLetter(letterOccurrences);
        guessedCharacters.add(mostCommonLetter);
        return mostCommonLetter;
    }

    public void addIncorrectLetter(char incorrectLetter){
        incorrectGuesses.add(incorrectLetter);
    }
    public void updateCurrentGuess(char letter, int[] letterPositions){
        for (int letterPos : letterPositions){
            currentGuess[letterPos - 1] = letter;
        }
    }

    public void setWordLength(int wordLength){
        eliminateWordsOfWrongLength(wordLength);
        currentGuess = new char[wordLength];
    }

    private void eliminateWordsOfWrongLength(int wordLength){
        HashSet<String> tempDictionary = new HashSet<>();
        for (String word : dictionary){
            if (word.length() == wordLength){
                tempDictionary.add(word);
            }
        }
        dictionary = tempDictionary;
    }

    public void eliminateWordsWithWrongLetters(){
        HashSet<String> wordsWithCorrectGuessedLetters = getWordsWithCorrectGuessedLetters(dictionary);
        dictionary = wordsWithCorrectGuessedLetters;
        HashSet<String> wordsWithoutIncorrectLetters = getWordsWithoutIncorrectLetters(dictionary);
        dictionary = wordsWithoutIncorrectLetters;
    }

    public HashSet<String> getWordsWithoutIncorrectLetters(HashSet<String> words){
        HashSet<String> wordsWithoutIncorrectLetters = new HashSet<>();
        for (String word : words){
            boolean wordValid = true;
            for (int charPos = 0; charPos < word.length(); charPos++){
                if (incorrectGuesses.contains(word.charAt(charPos)))
                    wordValid = false;
            }
            if (wordValid)
                wordsWithoutIncorrectLetters.add(word);
        }
        return wordsWithoutIncorrectLetters;
    }

    private HashSet<String> getWordsWithCorrectGuessedLetters(HashSet<String> words) {
        HashSet<String> wordsWithCorrectGuessedLetters = new HashSet<>();
        for (String word : words){
            boolean wordValid = true;
            for (int guessedLetterPosition = 0; guessedLetterPosition < currentGuess.length; guessedLetterPosition++){
                if (currentGuess[guessedLetterPosition] != '\u0000'){
                    if (currentGuess[guessedLetterPosition] != word.charAt(guessedLetterPosition))
                        wordValid = false;
                }
            }
            if (wordValid)
                wordsWithCorrectGuessedLetters.add(word);
        }
        return wordsWithCorrectGuessedLetters;
    }

    public boolean arrivedAtAnswer(){
        return (dictionary.size() == 1);
    }

    public String getOnlyWordInDictionary(){
        return dictionary.toString();
    }





}
