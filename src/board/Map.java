package board;

import game.Card;
import game.Rooms;

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

    public Card getSlot(int x, int y){
        char c = map[x][y];
        Card card;

        switch (c){
            case 'K':   card = Rooms.KITCHEN;
                break;
            case 'B':   card = Rooms.BALLROOM;
                break;
            case 'C':   card = Rooms.CONSERVATORY;
                break;
            case 'L':   card = Rooms.LIBRARY;
                break;
            case 'l':   card = Rooms.LOUNGE;
                break;
            case 'b':   card = Rooms.BILLIARD_ROOM;
                break;
            case 'D':   card = Rooms.DINING_ROOM;
                break;
            case 'S':   card = Rooms.STUDY;
                break;
            case 'H':   card = Rooms.HALL;
                break;
            default:    return null;
        }

        return card;
    }

    //TODO: DFS Search to get distance to destination, while moving around walls etc.
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

}
