package pl.hubert;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * For learning purpose
 */
public class Main {
    private static Lokacje lokacje = new Lokacje();

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new LinkedHashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        // Starting location
        int loc = 1;
        while(true) { // infinite loop
            System.out.println(lokacje.get(loc).getDescription()); // Printing description of starting location
            // if location == 0 then break the loop
            if(loc == 0) {
                break;
            }

            // Reference to get the exits from current location
            Map<String, Integer> exits = lokacje.get(loc).getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", "); // printing current locations available
            }
            System.out.println();

            // Blocking method, to receive the direction input, where you want to go
            String direction = scanner.nextLine().toUpperCase();

            // If the input is more than one character, then it looks for the correct word in vocabulary map, that contains correct words
            if(direction.length() > 1) {
                String[] words = direction.split(" "); // Splitting the input to single words array

                // Looping through map to check for the input
                for(String word: words) {
                    // If map contains input
                    if(vocabulary.containsKey(word)) {
                        // Then direction is equal to value of the key
                        direction = vocabulary.get(word);
                        System.out.println(vocabulary.get(word)); // Testing
                        break;
                    }
                }
            }
            // If exits map contains direction key
            if(exits.containsKey(direction)) {

                // Then loc variable is equal to id from the exits map of the location that we choose to go
                loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}