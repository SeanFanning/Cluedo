package game;

import java.util.*;

public class Game {

    public static void main(String[] args){
        int num_players = 6;
        ArrayList<Character> characters = Character.shuffleCharacters(num_players);

        Card[] solution = new Card[3];
        solution = initSolution();

        Player[] players = initPlayers(num_players, characters);
        players[3].notebook.add_note("Test note");
        System.out.print("\n");
        players[3].notebook.read_notebook();

        move_character(3); /* 3 test move */
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
            switch (direction)  {
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

    }

    public static int roll_dice()   {
        Random rand = new Random();
        int dice_num = rand.nextInt(12) + 2;
        return dice_num;
    }

}