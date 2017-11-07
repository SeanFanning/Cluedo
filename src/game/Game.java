package game;

import java.util.*;

public class Game {

    private Card[] solution;
    private Card[] new_player;

    public Game(int num_players)   {
        this.solution = this.initSolution();
        this.new_player = this.initNewPlayer(num_players);
    }
    public static void main(String[] args){
        int num_players = 6;
        ArrayList<Character> characters = Character.shuffleCharacters(num_players);

//        private Card[] solution = new Card[];
//        solution = initSolution();
        //this.new_player = this.initNewPlayer(num_players);

        Player[] players = initPlayers(num_players, characters);
        players[3].notebook.add_note("Test note");
        players[3].notebook.read_notebook();
    }

    public static Player[] initPlayers(int num_players, ArrayList<Character> characters){
        Player[] players = new Player[num_players];

        for(int x=0; x<num_players; x++)    {
            players[x] = new Player(characters.get(x).toString(), characters.get(x));
            System.out.println("Player " + (x + 1) + " is: " + players[x].character);

            players[x].initNotebook(players[x].character.toString());
            players[x].notebook.add_note("Player " + (x + 1));
        }

        return players;
    }

    public Card[] initNewPlayer(int num_players)   {
        this.new_player = new Card[num_players];

        for(int x=0; x<num_players; x++)    {
            this.new_player[x] = Character.getRandom();
            System.out.println("Player " + (x + 1) + " is: " + this.new_player[x]);
        }

        return this.new_player;
    }

    public Card[] initSolution()    {
        this.solution = new Card[3];
        this.solution[0] = Character.getRandom();
        this.solution[1] = Weapon.getRandom();
        this.solution[2] = Rooms.getRandom();
        System.out.println("Character: " + this.solution[0] + " Weapon: " + this.solution[1] + " Room: " + this.solution[2]);
        return this.solution;
    }
}