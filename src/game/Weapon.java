package game;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;


public enum Weapon implements Card{
    LEAD_PIPE,
    REVOLVER,
    KNIFE,
    CANDLESTICK,
    POISON,
    ROPE;

    private static final List<Weapon> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();


    public String getType() {
        return "Weapon";
    }

    public static Weapon getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

