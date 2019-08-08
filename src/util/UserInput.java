package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserInput {

    private static Scanner scanner = new Scanner(System.in);
    public static String getStringInput(){
        return scanner.nextLine();
    }



    public static int getIntInput(){
        String userInput = scanner.nextLine();
        if (!isInteger(userInput)) {
            System.out.println("Input must be integer.");
            return getIntInput();
        }
        return Integer.parseInt(userInput);
    }

    public static int[] getIntArray(){
        String userInput = scanner.nextLine();
        String[] intStrings = userInput.trim().split("\\s+");
        ArrayList<Integer> ints = new ArrayList<>();
        for (String intString : intStrings){
            if (isInteger(intString)){
                ints.add(Integer.parseInt(intString));
            }
        }
        return ints.stream().mapToInt(i -> i).toArray();
    }

    private static boolean isInteger(String s) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i), 10) < 0) return false;
        }
        return true;
    }

    public static boolean getBoolean(){
        String userInput = scanner.nextLine().toLowerCase();
        if (userInput.equals("y"))
            return true;
        else if (userInput.equals("n"))
            return false;
        System.out.println("Incorrect input.");
        return getBoolean();
    }

    public static long findNextSquare(long sq) {
        if ((sq % 1) != 0)
            return -1;
        return ((long) (Math.sqrt(sq) + 1))^2;
    }




















    public  int findIt(int[] a) {
        IntOccurences intOccurences = new IntOccurences();
        for(int i = 0; i < a.length; i++){
            intOccurences.addOccurence(a[i]);
        }
        return intOccurences.getOddNumOfInt();
    }

    public class IntOccurences{
        private HashMap<Integer, Integer> occurences = new HashMap<>();

        public void addOccurence(int i){
            if (occurences.containsKey(i)){
                int newNum = occurences.get(i) + 1;
                occurences.put(i, newNum);
            }
            else{
                occurences.put(i, 1);
            }
        }

        public int getOddNumOfInt(){
            for(int key : occurences.keySet()){
                if ((occurences.get(key) % 2) == 1){
                    return key;
                }
            }
            return -1;
        }
    }









}