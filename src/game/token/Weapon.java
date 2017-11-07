package game.token;

import game.Card;

public class Weapon implements Card {
    private int[] position = new int[2];
    private String name;

    public Weapon(int x, int y, String n){
        position[0] = x;
        position[1] = y;
        name = n;
    }

    public String getType(){
        return "Weapon";
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
