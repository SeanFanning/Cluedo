package game;

import java.util.*;

public class Game {

    // TODO: Make this less of a mess

    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;

    public static void main(String[] args){

        num_players = 6;
        characters = Character.shuffleCharacters(num_players);

        Card[] solution = new Card[3];
        solution = initSolution();

        players = initPlayers(num_players, characters);
        dealCards(players,initDeck(solution),num_players);

        players[2].addNote("Test note");
        System.out.print("\n");
        printNotebook(players[2].getNotes());

        for (int i = 0; i < num_players; i++) {
            System.out.println("Player " + (i+1) + "'s hand is:\n" + players[i].getCards() + "\n");
        }

        //move_character(players[2]); /* 3 test move */
        MovePlayer movePlayer = new MovePlayer(num_players,players);
        movePlayer.move_character(roll_dice(),3);

        // Testing Hypothesis
        Hypothesis my_hypothesis = new Hypothesis(players,num_players);
        my_hypothesis.form_hypothesis(3);
    }

    private static void printNotebook(List<String> notes){
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i));
        }
        System.out.println();
    }

    // TODO: Tidy up the brackets
    // TODO: Y-Axis is upsidedown
    // Draw the map on the game screen

    public static Player[] initPlayers(int num_players, ArrayList<Character> characters){
        Player[] players = new Player[num_players];

        int[][] startingPos = new int[][]{
                {1, 1},
                {3, 3},
                {1, 3},
                {1, 2},
                {1, 4},
                {3, 1}
        };

        char[] icons = new char[]{
                '€', '£', '$', '%', '@', '&'
        };

        for(int x=0; x<num_players; x++)    {
            Character character = characters.get(x);

            players[x] = new Player(character.toString(), character, startingPos[x], icons[x]);
            System.out.println("Player " + (x + 1) + " is: " + players[x].getName());

            players[x].initNotebook(players[x].getName());
            players[x].addNote("Player " + (x + 1));
        }

        return players;
    }

    public static Card[] initSolution()    {
        Card[] solution = new Card[3];
        solution[0] = Character.getRandom();
        solution[1] = Weapon.getRandom();
        solution[2] = Rooms.getRandom();
        System.out.println("Solution is:\nCharacter: " + solution[0] + " Weapon: " + solution[1] + " Room: " + solution[2] + "\n");
        return solution;
    }

    public static Card[] initDeck(Card[] solution) {
        Card[] deck = new Card[18];
        Card[] characters = Character.values();
        Card[] weapons = Weapon.values();
        Card[] rooms = Rooms.values();
        int i = 0;
        for (Card c : characters) {
            if (c.equals(solution[0]))    {
                continue;
            }
            deck[i] = c;
            i++;
        }
        for (Card w : weapons)  {
            if (w.equals(solution[1]))    {
                continue;
            }
            deck[i] = w;
            i++;
        }
        for (Card r : rooms)    {
            if (r.equals(solution[2]))    {
                continue;
            }
            deck[i] = r;
            i++;
        }

        return deck;
    }

    public static int roll_dice()   {
        int dice_num = (int)(Math.random() * 12 + 2);
        return dice_num;
    }

    public static void dealCards(Player[] player, Card[] deck, int num_players) {

        ArrayList<Card> shuffled_deck = new ArrayList<>();
        for(Card d : deck){
            shuffled_deck.add(d);
        }
        Collections.shuffle(shuffled_deck);

        int i = 0;
        for (Card sh : shuffled_deck) {
            player[i].addCard(sh);
            i++;
            if (i == num_players) {
                i = 0;
            }
        }
    }

}