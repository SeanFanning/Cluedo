package game.token;

import game.Card;

public class WeaponPawn implements Card {
    private int[] position = new int[2];
    private String name;

    public WeaponPawn(int x, int y, String n){
        position[0] = x;
        position[1] = y;
        name = n;
    }

    public String getType(){
        return "Weapon Pawn";
    }

    public String getName(){
        return name;
    }

    public void setPosition(int x, int y){
        position[0] = x;
        position[1] = y;
    }

    public int[] getPosition(){
        return position;
    }
}
