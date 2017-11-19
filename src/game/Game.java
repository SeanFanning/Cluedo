package game;

import java.util.*;
import board.Map;

public class Game {

    private static Map board = new Map();
    private static ArrayList<Character> characters;

    public static void main(String[] args){

        printMap();

        int num_players = 3;
        characters = Character.shuffleCharacters(num_players);

        Card[] solution = new Card[3];
        solution = initSolution();

        Player[] players = initPlayers(num_players, characters);
        players[2].addNote("Test note");
        System.out.print("\n");
        printNotebook(players[2].getNotes());


        move_character(3); /* 3 test move */
    }

    private static void printNotebook(List<String> notes){
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i));
        }
        System.out.println();
    }

    // TODO: Tidy up the brackets
    // TODO: Draw character locations on map
    // Draw the map on the game screen
    private static void printMap(){
        char[][] map = board.getMap();

        int n = map.length;

        for(int i=0; i<n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public static Player[] initPlayers(int num_players, ArrayList<Character> characters){
        Player[] players = new Player[num_players];

        for(int x=0; x<num_players; x++)    {
            players[x] = new Player(characters.get(x).toString(), characters.get(x));
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

    public static void move_character(int player_num)  {
        Scanner scanner = new Scanner(System.in);
        /*Character player = character;*/
        int dice_num = roll_dice();
        System.out.println("Player " + player_num + " has rolled the dice!");
        System.out.println("The player has rolled a... " + dice_num + "!");
        System.out.println("Choose where you would like to move to: [N,E,S,W]");

        for (int x = 0; x < dice_num; x++){
            String direction = scanner.nextLine();
            switch (direction.toUpperCase())  {
                case "N":   System.out.println("You moved North");
                            break;
                case "E":   System.out.println("You moved East");
                            break;
                case "W":   System.out.println("You moved West");
                            break;
                case "S":   System.out.println("You moved South");
                            break;
                default:    System.out.println("Not a valid input");
                            break;
            }
            System.out.println("Choose where you would like to move to next: [N,E,S,W]");
        }

        System.out.println("Out of moves");

    }

    public static int roll_dice()   {
        Random rand = new Random();
        int dice_num = rand.nextInt(12) + 2;
        return dice_num;
    }

}