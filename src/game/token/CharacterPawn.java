package game.token;

import game.Card;

public class CharacterPawn implements Card {
    //private int x, y;
    private int[] position = new int[2];
    private String name;

    public CharacterPawn(int x, int y, String n){
        position[0] = x;
        position[1] = y;
        name = n;
    }

    public String getType(){
        return "Character Pawn";
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
