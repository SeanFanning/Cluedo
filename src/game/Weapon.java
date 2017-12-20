package game;
import game.token.WeaponPawn;

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
    private WeaponPawn pawn = new WeaponPawn(1, 1, '*');

    public String getType() {
        return "Weapon";
    }

    public static Weapon getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int[] getPos(){
        return pawn.getPosition();
    }

    public void setPos(int x, int y){
        pawn.setPosition(x, y);
    }

    // We dont actually draw the weapons on the map, so this is probably not necissary
    public char getIcon(){
        return pawn.getIcon();
    }
}

