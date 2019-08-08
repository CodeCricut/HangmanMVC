package util;

import java.util.HashMap;

public class Alphabet {

    public static void addLetterInstance(HashMap<Character, Integer> letterOccurrences, char c){
        if (letterOccurrences.containsKey(c)){
            int newLetterOccurrence = letterOccurrences.get(c) + 1;
            letterOccurrences.put(c, newLetterOccurrence);
        }
        else {
            letterOccurrences.put(c, 1);
        }
    }

    public static char getMostCommonLetter(HashMap<Character, Integer> letterOccurrences){
        char mostCommonLetter = '\0';
        for (char c : letterOccurrences.keySet()){
            if (mostCommonLetter == '\0' ||
            letterOccurrences.get(c) > letterOccurrences.get(mostCommonLetter)){
                mostCommonLetter = c;
            }
        }
        return mostCommonLetter;
    }
}
