package game;

import board.Map;
import game.token.Slot;
import java.util.*;

// movement class
public class MovePlayer{

    private static Map board = new Map();
    private Player[] players;
    private int num_players;

    public MovePlayer(int num_players, Player[] players)    {
        this.num_players = num_players;
        this.players = players;
    }

    public Hashtable<String, Boolean> checkMove(int x, int y)
    {

        Hashtable<String, Boolean> hm = new Hashtable();

        // creates a hashtable of player movable locations so the players choice are only limited to those options
        if (board.canMove(x, y - 1)) {
            hm.put("West [A]",true);
        }
        else {
            hm.put("West [A]",false);
        }

        if (board.canMove(x+1, y )) {
            hm.put("North [W]",true);
        }
        else {
            hm.put("North [W]",false);
        }

        if (board.canMove(x-1, y )) {
            hm.put("South [S]",true);
        }
        else {
            hm.put("South [S]",false);
        }

        if (board.canMove(x, y + 1)) {
            hm.put("East [D]",true);
        }
        else {
            hm.put("East [D]",false);
        }

        if (board.getSlot(x, y).getSlot().equals("Tunnel")){
            hm.put("Travel through tunnel [T]", true);
            System.out.println("Slot: " + board.getSlot(x, y).getSlot());
        }
        else {
            hm.put("Travel through tunnel [T]",false);
        }

        return hm;
    }

    public String return_pos(Player player)    {
        int[] coordinates = player.getPos();
        Slot room = board.getSlot(player.getPos()[1], player.getPos()[0]);
        return  room.getSlot();
    }

    public void move_character(Player player)  {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = player.getPos();
        int x = coordinates[1];
        int y = coordinates[0];

        board.printMap(players,num_players);
        Slot room = board.getSlot(player.getPos()[1], player.getPos()[0]);
        System.out.println("You are at " + Arrays.toString(coordinates) + " in " + room.getSlot());

        Hashtable<String,Boolean> hm = checkMove(x,y);
        String message = "Select where you would like to move: ";
        String[] directions = {"West [A]","North [W]","South [S]","East [D]","Travel through tunnel [T]"};

        // concatenates msg based on locations where you can move, so if you can't move East it won't display East
        int iterate = 0;
        for (String dir : directions)   {
            if (hm.get(dir))    {
                if (iterate == 0) {
                    message += dir;
                }
                else    {
                    message += ", " + dir;
                }
                iterate = 1;
            }
        }

        // asks players where they want to move and checks if they can move there
        inputLoop:  while(true) {
            System.out.println(message);
            String direction = scanner.nextLine();
            switch (direction.toUpperCase()) {
                case "A":
                    if (board.canMove(x, y - 1)) {
                        System.out.println("You moved West");
                        player.setPos(x, y - 1);
                        break inputLoop;
                    } else {
                        System.out.println("You cant move there");
                        break;
                    }
                case "W":
                    if (board.canMove(x + 1, y)) {
                        player.addNote("You moved North", "Movement",true);
                        player.setPos(x + 1, y);
                        break inputLoop;
                    } else {
                        System.out.println("You cant move there");
                        break;
                    }


                case "S":
                    if (board.canMove(x - 1, y)) {
                        player.addNote("You moved South", "Movement",true);
                        player.setPos(x - 1, y);
                        break inputLoop;
                    } else {
                        System.out.println("You cant move there");
                        break;
                    }


                case "D":
                    if (board.canMove(x, y + 1)) {
                        player.addNote("You moved East", "Movement",true);
                        player.setPos(x, y + 1);
                        break inputLoop;
                    } else {
                        System.out.println("You cant move there");
                        break;
                    }

                case "T":
                    if (board.getSlot(x, y).getSlot().equals("Tunnel")) {
                        if (board.getSlot(x - 1, y - 1).getSlot().equals("KITCHEN")) {
                            player.addNote("You travelled through the tunnel to the Study", "Movement", true);
                            player.setPos(3, 22);
                            break inputLoop;
                        } else if (board.getSlot(x + 1, y - 1).getSlot().equals("CONSERVATORY")){
                            player.addNote("You travelled through the tunnel to the Lounge", "Movement", true);
                            player.setPos(4, 1);
                            break inputLoop;
                        } else if (board.getSlot(x - 1, y - 1).getSlot().equals("STUDY")){
                            player.addNote("You travelled through the tunnel to the Kitchen", "Movement", true);
                            player.setPos(19, 5);
                            break inputLoop;
                        } else if (board.getSlot(x - 1, y + 1).getSlot().equals("LOUNGE")){
                            player.addNote("You travelled through the tunnel to the Conservatory", "Movement", true);
                            player.setPos(17, 22);
                            break inputLoop;
                        } else {
                            System.out.println("Unexpected");
                            break;
                        }
                    }

                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }

        //System.out.println("Out of moves");
        board.printMap(players,num_players);
    }
}
