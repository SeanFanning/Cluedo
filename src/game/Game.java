package game;

import java.util.*;

public class Game {

    // TODO: Make this less of a mess

    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;
    private static MovePlayer movePlayer;

    public static void main(String[] args){

        System.out.println("Welcome to Cludeo! How many people will be playing? [3-6]");
        Scanner sc = new Scanner(System.in);
        num_players = 0;
        int check = 0;
        do {
            if (check == 1) {
                System.out.println("Number must be from 3 to 6");
            }
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number");
                sc.next();
            }
            num_players = sc.nextInt();
            check++;
        } while (num_players <= 2 || num_players > 6);

        characters = Character.shuffleCharacters(num_players);

        Card[] solution;
        solution = initSolution();

        players = initPlayers(num_players, characters);
        dealCards(players,initDeck(solution),num_players);

        players[2].addNote("Test note", "Test");
        System.out.print("\n");
        printNotebook(players[2].getNotes());
        printNotebook(players[2].filterNotes("Info")); //Test filtering the notebook for Info notes

        for (int i = 0; i < num_players; i++) {
            System.out.println("Player " + (i+1) + "'s hand is:\n" + players[i].getCards() + "\n");
            players[i].addNote("Your hand is: " + players[i].getCards(), "Cards");
        }

        movePlayer = new MovePlayer(num_players,players);
        for (Player player : players) {
            takeTurn(player, Arrays.asList(players).indexOf(player));
        }

    }

    private static void printNotebook(List<Note> notes){
        System.out.println("Notebook: ");
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i).getType() + ": " + notes.get(i).getNote());
        }
        System.out.println();
    }


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
            players[x].addNote("Player " + (x + 1), "Info");
        }

        return players;
    }

    // TODO: Move this to a new initialisation class
    public static Card[] initSolution(){
        Card[] solution = new Card[3];
        solution[0] = Character.getRandom();
        solution[1] = Weapon.getRandom();
        solution[2] = Rooms.getRandom();
        System.out.println("Solution is:\nCharacter: " + solution[0] + " Weapon: " + solution[1] + " Room: " + solution[2] + "\n");
        return solution;
    }

    // TODO: Move this to a new initialisation class
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
        int dice_num = (int)(Math.random() * 11 + 2);
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

    public static void takeTurn(Player player, int player_num)   {

        // Testing Hypothesis
        Hypothesis my_hypothesis = new Hypothesis(players,num_players);

        int dice_num = roll_dice();
        player.addNote("Player " + player.getName() + " (" + player.getIcon() + ") rolls a " + dice_num + "!", "Game");
        int num;
        Scanner sc = new Scanner(System.in);
        int i = dice_num;
        while(i > 0) {
            System.out.println(player.getName() + " (uid: " + player.getIcon() + ")");
            System.out.println("You have " + i + " turns left");
            do {
                System.out.println("Please choose an option:\n1: Move\n2: Form a hypothesis\n3: Make an accusation\n4: View Notebook");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number");
                    sc.next();
                }
                num = sc.nextInt();
            } while (num <= 0 || num > 5);
            if (num == 1)   {
                movePlayer.move_character(player);
                i--;
            }
            else if (num == 2)  {
                my_hypothesis.form_hypothesis(player,player_num);
                i--;
            }
            else if (num == 3)  {
                System.out.println("WIP");
            }
            else    {
                for (Note c : player.getNotes()) {
                    System.out.println(c.getNote());
                }
            }
        }
    }

}