package game;

import game.token.Pawn;
import game.token.Weapon;

public class Game {
    public static void main(String[] args){
        Pawn p1 = new Pawn(4, 6, "Bob");

        System.out.println(p1.getName());
        System.out.println(p1.getPosition()[0] + " " + p1.getPosition()[1]);

        p1.setPosition(8, 37);
        System.out.println(p1.getPosition()[0] + " " + p1.getPosition()[1]);

        Weapon w1 = new Weapon(7, 1, "Lead Pipe");

        System.out.println(w1.getName());
        System.out.println(w1.getPosition()[0] + " " + w1.getPosition()[1]);

        w1.setPosition(2, 73);
        System.out.println(w1.getPosition()[0] + " " + w1.getPosition()[1]);

    }
}