package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

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
    private List<Card> cards = new ArrayList<Card>();


    public String getType() {
        return "Character";
    }

    public static Character getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static ArrayList<Character> shuffleCharacters(int x){
        ArrayList<Character> characters = new ArrayList<>();
        for(int i=0; i<x; i++){
            characters.add(VALUES.get(i));
        }

        Collections.shuffle(characters);

        return characters;
    }
}
