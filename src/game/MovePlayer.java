package game;

import board.Map;
import game.token.Slot;

import java.util.Arrays;
import java.util.Scanner;

public class MovePlayer {

    private static Map board = new Map();
    private static char[][] map = board.getMap();;
    private Player[] players;
    private int num_players;

    public MovePlayer(int num_players, Player[] players)    {
        this.num_players = num_players;
        this.players = players;
    }

    private void printMap(){

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

    public void move_character(int player_num)  {
        player_num = player_num - 1;
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = players[player_num].getPos();
        int x = coordinates[1];
        int y = coordinates[0];

        printMap();
        Slot room = board.getSlot(players[player_num].getPos()[0], players[player_num].getPos()[1]);
        players[player_num].addNote("You are at " + Arrays.toString(coordinates) + " in " + room.getSlot());

        System.out.println("Where would you like to move? [A, W, S, D]");
        String direction = scanner.nextLine();
        switch (direction.toUpperCase())  {
            case "A":   if(board.canMove(x, y-1)){
                System.out.println("You moved West");
                players[player_num].setPos(x, y-1);
                }else{
                System.out.println("You cant move there");
            }
                break;
             case "W":   if(board.canMove(x+1, y)){
                 players[player_num].addNote("You moved North");
                 players[player_num].setPos(x+1, y);
                 }else{
                 System.out.println("You cant move there");
             }
                 break;

            case "S":   if(board.canMove(x-1, y)){
                players[player_num].addNote("You moved South");
                players[player_num].setPos(x-1, y);
                }else{
                    System.out.println("You cant move there");
                }
                break;

            case "D":   if(board.canMove(x, y+1)){
                players[player_num].addNote("You moved East");
                players[player_num].setPos(x, y+1);
                }else{
                    System.out.println("You cant move there");
                }
                    break;

            default:    System.out.println("Not a valid input");
                break;
            }


        System.out.println("Out of moves");
        printMap();
    }
}
