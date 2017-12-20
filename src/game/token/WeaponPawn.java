package game.token;

import game.Card;

public class WeaponPawn implements Card {
    private int[] position = new int[2];
    private char icon;

    public WeaponPawn(int x, int y, char i){
        position[0] = x;
        position[1] = y;
        icon = i;
    }

    public String getType(){
        return "Weapon Pawn";
    }

    public char getIcon(){
        return icon;
    }

    public void setPosition(int x, int y){
        position[0] = x;
        position[1] = y;
    }

    public int[] getPosition(){
        return position;
    }
}
