package board;

import game.Card;
import game.Rooms;
import game.token.Slot;

public class Map {
    private char[][] map = new char[][]{
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
            { 'X', 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', ' ', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', ' ', ' ', ' ', ' ', 'X', 'C', 'C', 'C', 'X' },
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
    };

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

    public Slot getSlot(int x, int y){
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
            case ' ':   slot = new Slot("Outside");
                break;
            case 'X':   slot = new Slot("Wall");
                break;
            default:    slot = new Slot("Error");
        }

        return slot;
    }

    //TODO: DFS Search to get distance to destination, while moving around walls etc.
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

}
