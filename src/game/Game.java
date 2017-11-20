package game;

import java.util.*;
import board.Map;
import game.token.Slot;

public class Game {

    private static Map board = new Map();
    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;
    private static char[][] map = board.getMap();;


    public static void main(String[] args){

        num_players = 3;
        characters = Character.shuffleCharacters(num_players);

        Card[] solution = new Card[3];
        solution = initSolution();

        players = initPlayers(num_players, characters);
        players[2].addNote("Test note");
        System.out.print("\n");
        printNotebook(players[2].getNotes());


        move_character(players[2]); /* 3 test move */
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
                {1, 3}
        };

        char[] icons = new char[]{
                '€', '£', '$'
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

}