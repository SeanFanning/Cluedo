package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

// enum class that represents all players
public enum Character implements Card{
    MISS_SCARLET,
    PROFESSOR_PLUM,
    MRS_PEACOCK,
    REVEREND,
    COL_MUSTARD,
    MRS_WHITE;

    private static final List<Character> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public String getType() {
        return "Character";
    }

    public static Character getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    } // return random character card

    public static ArrayList<Character> shuffleCharacters(int x){ // shuffle characters
        ArrayList<Character> characters = new ArrayList<>();
        for(int i=0; i<x; i++){
            characters.add(VALUES.get(i));
        }

        Collections.shuffle(characters);

        return characters;
    }
}
