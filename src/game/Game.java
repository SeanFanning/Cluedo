package game;

import board.Map;
import java.util.*;


//This is the main class where initialisation takes play and the game runs
public class Game {

    // Global variables for our class
    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;
    private static Map map = new Map();

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

        characters = Character.shuffleCharacters(num_players); //giving a character to each player randomly given the number of players playing

        Card[] solution;
        solution = initSolution(); //initialises solution

        players = initPlayers(num_players, characters); // sets up each player
        dealCards(players,initDeck(solution),num_players); // deals cards to each player

       /* players[2].addNote("Test note", "Test",false);
        System.out.print("\n");
        printNotebook(players[2].getNotes());
        printNotebook(players[2].filterNotes("Info")); //Test filtering the notebook for Info notes
        */

       // adds players hand to their notebook
       for (int i = 0; i < num_players; i++) {
            players[i].addNote("Your hand is: " + players[i].getCards(), "Hand",false);
        }

        // takes players turns until solution is found or each player gets solution wrong
        boolean solRight = false;
        List<Player> playersInGame = new ArrayList<Player>();
        while(!solRight) {
            playersInGame.clear();
            for (Player player : players)    { // array that keeps track of how many players are still in game
                if (player.returnGame())    {
                    playersInGame.add(player);
                }
            }
            if (playersInGame.isEmpty())    {
                break;
            }
            for (Player player : playersInGame) { // takes players turn
                System.out.println(player.getName());
                solRight = takeTurn(player, playersInGame.indexOf(player));
                if (solRight)   {
                    break;
                }
                System.out.println("\nNext players turn!\n\n");
            }
        }

    }

    private static void printNotebook(List<Note> notes){
        System.out.println("Notebook: ");
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i).getType() + ": " + notes.get(i).getNote());
        }
        System.out.println();
    }


    // initialises players
    private static Player[] initPlayers(int num_players, ArrayList<Character> characters){
        Player[] players = new Player[num_players];

        int[][] startingPos = new int[][]{
                {1, 7},
                {6, 1},
                {19, 7},
                {19, 16},
                {15, 22},
                {5, 22}
        };

        char[] icons = new char[]{ // player symbols
                '1', '2', '3', '4', '5', '6'
        };

        for(int x=0; x<num_players; x++)    {
            Character character = characters.get(x);

            players[x] = new Player(character.toString(), character, startingPos[x], icons[x]);
            System.out.println("Player " + (x + 1) + " is: " + players[x].getName());

            players[x].initNotebook(players[x].getName());
            players[x].addNote("Player " + (x + 1), "Info",false);
        }

        return players;
    }

    // TODO: Move this to a new initialisation class
    private static Card[] initSolution(){
        Card[] solution = new Card[3];
        solution[0] = Character.getRandom();
        solution[1] = Weapon.getRandom();
        solution[2] = Rooms.getRandom();
        System.out.println("Solution is:\nCharacter: " + solution[0] + " Weapon: " + solution[1] + " Room: " + solution[2] + "\n");
        return solution;
    }

    // TODO: Move this to a new initialisation class
    private static Card[] initDeck(Card[] solution) {
        Card[] deck = new Card[18]; // 18 is the amount of total cards in cluedo - 3 (that are part of the solution)
        Card[] characters = Character.values();
        Card[] weapons = Weapon.values();
        Card[] rooms = Rooms.values();
        int i = 0;
        for (Card c : characters) {
            if (c.equals(solution[0]))    { // don't add the solution to the deck
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

    private static int roll_dice()   {
        int dice_num = (int)(Math.random() * 11 + 2);
        return dice_num;
    }

    private static void dealCards(Player[] player, Card[] deck, int num_players) {

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

    private static boolean takeTurn(Player player, int player_num)   {

        boolean sol = false; // true if player guesses solution correctly
        Hypothesis my_hypothesis = new Hypothesis(players,num_players); // for hypothesis
        MovePlayer movePlayer = new MovePlayer(num_players,players); // for moving
        map.printMap(players,num_players); // prints cluedo map
        int dice_num = roll_dice();
        player.addNote("Player " + player.getName() + " (" + player.getIcon() + ") rolls a " + dice_num + "!", "Game",true);

        int num;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        while(dice_num > 0) {
            System.out.println("You have " + dice_num + " turns left");
            // get players input on which option to make, limiting their input to choices 1 - 4
            do {
                System.out.println("Please choose an option:\n1: Move\n2: Form a hypothesis\n3: Make an accusation\n4: View Notebook");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number");
                    sc.next();
                }
                num = sc.nextInt();
            } while (num <= 0 || num > 5);
            // move
            if (num == 1)   {
                movePlayer.move_character(player);
                dice_num--;
            }
            // form hypothesis
            else if (num == 2)  {
                if (movePlayer.return_pos(player).equals("Hallway") || movePlayer.return_pos(player).equals("Tunnel"))    {
                    System.out.println("You must be in a room to form a hypothesis");
                    continue;
                }
                my_hypothesis.form_hypothesis(player,player_num,movePlayer.return_pos(player));
                break;
            }
            // accuse
            else if (num == 3)  {
                if (movePlayer.return_pos(player).equals("Hallway") || movePlayer.return_pos(player).equals("Tunnel"))    {
                    System.out.println("You must be in a room to form a hypothesis");
                    continue;
                }
                sol = my_hypothesis.accuse(player,player_num,movePlayer.return_pos(player));
                if (!sol)   {
                    break;
                }
                else    {
                    System.out.println("Congratulations! Player " + player.getName() + " has won the game!");
                    break;
                }
            }
            // view notebook
            else if (num == 4)  {
                String filter = "";
                int check = 1;
                printNotebook(player.getNotes());
                while (check!=0) {
                    System.out.println("Type in a keyword to filter your notebook (such as \"Game\") or press \"0\" to exit:");
                    filter = sc2.nextLine();
                    printNotebook(player.filterNotes(filter));
                    try
                    {
                        check = Integer.parseInt(filter);
                    }
                    catch (NumberFormatException ex)
                    {
                        check = 1;
                    }
                }
            }
            else    {
                System.out.println("Error in input.");
            }
        }
        //System.out.println("Out of turns. " + player.getPos().toString());
        return sol;
    }

}