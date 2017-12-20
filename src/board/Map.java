package board;
import game.Player;

import game.Card;
import game.Rooms;
import game.token.Slot;

import java.util.Arrays;

public class Map {
    private char[][] map = new char[][]{ //TODO: Convert this into an array of Slots
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
            { 'X', 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', ' ', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
    };

    private Slot[][] map_slots = new Slot[map.length][map[0].length];
    
    
    public Map(){
        generateMapSlots();
    }
    
    private void generateMapSlots(){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                map_slots[i][j] = convertMap(j, i);
            }
        }
    }

    public boolean canMove(int x, int y){
        if(map[x][y] != 'X'){
            return true;
        }
        else{
            return false;
        }
    }

    public char[][] getMap(){
        return map;
    }

    //Converts the characters in the char map into slots
    public Slot convertMap(int x, int y){
        char c = map[y][x];
        Card card;
        Slot slot;

        switch (c){
            case 'K':   slot = new Slot(Rooms.KITCHEN.toString());
                break;
            case 'B':   slot = new Slot(Rooms.BALLROOM.toString());
                break;
            case 'C':   slot = new Slot(Rooms.CONSERVATORY.toString());
                break;
            case 'L':   slot = new Slot(Rooms.LIBRARY.toString());
                break;
            case 'l':   slot = new Slot(Rooms.LOUNGE.toString());
                break;
            case 'b':   slot = new Slot(Rooms.BILLIARD_ROOM.toString());
                break;
            case 'D':   slot = new Slot(Rooms.DINING_ROOM.toString());
                break;
            case 'S':   slot = new Slot(Rooms.STUDY.toString());
                break;
            case 'H':   slot = new Slot(Rooms.HALL.toString());
                break;
            case ' ':   slot = new Slot("Hallway");
                break;
            case 'X':   slot = new Slot("Wall");
                break;
            default:    slot = new Slot("Error");
        }

        return slot;
    }

    public Slot getSlot(int x, int y){
        return map_slots[y][x];
    }

    //TODO: DFS Search to get distance to destination, while moving around walls etc.
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    public void printMap(Player[] players, int num_players){

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

}
