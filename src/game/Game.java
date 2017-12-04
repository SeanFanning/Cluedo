package game;

import java.util.*;
import board.Map;
import game.token.Slot;

public class Game {

    // TODO: Make this less of a mess

    private static Map board = new Map();
    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;
    private static char[][] map = board.getMap();;


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

        move_character(players[2]); /* 3 test move */

        // Testing hypothesis
        // form_hypothesis(3);
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
    private static void printMap(){

        char[][] tmp_map = new char[map.length][];
        for(int i=0; i<map.length; i++){
            tmp_map[i] = map[i].clone();
        }

        for(int i=0; i<num_players; i++){
            int x = players[i].getPos()[0];
            int y = players[i].getPos()[1];

            tmp_map[y][x] = players[i].getIcon();
        }

        for(int i=map.length-1; i>=0; i--) {
            System.out.println(Arrays.toString(tmp_map[i]));
        }
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
            players[x].addNote("Player " + (x + 1));
        }

        return players;
    }

    // TODO: Move this to a new initialisation class
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

    // TODO: Move this to a new movement class
    public static void move_character(Player player)  {
        Scanner scanner = new Scanner(System.in);
        int dice_num = roll_dice();

        System.out.println("Player " + player.getName() + " (" + player.getIcon() + ") has rolled the dice!");
        System.out.println("The player has rolled a... " + dice_num + "!");

        for (int i = 0; i < dice_num; i++){
            int[] coordinates = player.getPos();
            int x = coordinates[1];
            int y = coordinates[0];

            printMap();
            Slot room = board.getSlot(player.getPos()[0], player.getPos()[1]);
            System.out.println("You are at " + Arrays.toString(coordinates) + " in " + room.getSlot());
            System.out.println("You have " + (dice_num-i) + " moves left: [W,A,S,D]");

            String direction = scanner.nextLine();
            switch (direction.toUpperCase())  {
                case "A":   if(board.canMove(x, y-1)){
                                System.out.println("You moved West");
                                player.setPos(x, y-1);
                            }else{
                                System.out.println("You cant move there");
                                i--;
                            }
                            break;

                case "W":   if(board.canMove(x+1, y)){
                                System.out.println("You moved North");
                                player.setPos(x+1, y);
                            }else{
                                System.out.println("You cant move there");
                                i--;
                            }
                            break;

                case "S":   if(board.canMove(x-1, y)){
                                System.out.println("You moved South");
                                player.setPos(x-1, y);
                            }else{
                                System.out.println("You cant move there");
                                i--;
                            }
                            break;

                case "D":   if(board.canMove(x, y+1)){
                                System.out.println("You moved East");
                                player.setPos(x, y+1);
                            }else{
                                System.out.println("You cant move there");
                                i--;
                            }
                            break;

                default:    System.out.println("Not a valid input");
                            i--;
                            break;
            }
        }

        System.out.println("Out of moves");
        printMap();
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

    // TODO: Move this to its own class
    private static void form_hypothesis(int player_num) {
        Card room = Rooms.getRandom();
        System.out.println("You are in the " + room);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which character would you like to accuse? [1:6] - \n" + java.util.Arrays.asList(Character.values()));
        String c_value = scanner.nextLine();
        Card character = Character.values()[Integer.parseInt(c_value) - 1];
        System.out.println("You accused " + character + ".");
        System.out.println("Which weapon would you like to pick? [1:6] - \n" + java.util.Arrays.asList(Weapon.values()));
        String w_value = scanner.nextLine();
        Card weapon = Weapon.values()[Integer.parseInt(w_value) - 1];
        System.out.println("You picked the " + weapon + " weapon.");
        System.out.println("I formulated the hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ".");
        players[player_num - 1].addNote("I formulated the hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ",");

        boolean sol_right = true;
        for (int i = (player_num - 2); i >= 0; i--) {
            for (Card c : players[i].getCards()) {
                if (c.equals(weapon)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                } else if (c.equals(character)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                } else if (c.equals(room)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                }
            }
            if (sol_right == false) {
                break;
            }
        }
    }
}