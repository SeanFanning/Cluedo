package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// enum class that represents all available rooms
public enum Rooms implements Card{
    KITCHEN,
    BALLROOM,
    LIBRARY,
    CONSERVATORY,
    LOUNGE,
    BILLIARD_ROOM,
    DINING_ROOM,
    STUDY,
    HALL;

    private static final List<Rooms> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();


    public String getType() {
        return "Room";
    }

    public static Rooms getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}