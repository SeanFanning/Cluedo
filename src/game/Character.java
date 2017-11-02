package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    }

}
