package game;

import board.Map;
import game.token.Slot;
import java.util.*;



public class MovePlayer {

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

        return hm;
    }

    public String return_pos(Player player)    {
        int[] coordinates = player.getPos();
        int x = coordinates[1];
        int y = coordinates[0];
        Slot room = board.getSlot(player.getPos()[0], player.getPos()[1]);
        return  room.getSlot();
    }

    public void move_character(Player player)  {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = player.getPos();
        int x = coordinates[1];
        int y = coordinates[0];

        board.printMap(players,num_players);
        Slot room = board.getSlot(player.getPos()[0], player.getPos()[1]);
        System.out.println("You are at " + Arrays.toString(coordinates) + " in " + room.getSlot());

        Hashtable<String,Boolean> hm = checkMove(x,y);
        String message = "Select where you would like to move: ";
        String[] directions = {"West [A]","North [W]","South [S]","East [D]"};

        int lol = 0;
        for (String dir : directions)   {
            if (hm.get(dir))    {
                if (lol == 0) {
                    message += dir;
                }
                else    {
                    message += ", " + dir;
                }
                lol = 1;
            }
        }

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


                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }

        //System.out.println("Out of moves");
        board.printMap(players,num_players);
    }
}
