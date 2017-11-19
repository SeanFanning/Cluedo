package board;

public class Map {
    //private int[][] map = new int[8][8];

    private char[][] map = new char[][]{
        { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
        { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
        { 'X', 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', 'X' },
        { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
        { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
    };

    public boolean canMove(int x1, int y1, int x2, int y2, int moves){
        if((map[x2][y2] != 'X') && (moves >= getDistance(x1, y1, x2, y2))){
            return true;
        }
        else{
            return false;
        }
    }

    public char[][] getMap(){
        return map;
    }

    //TODO: DFS Search to get distance to destination, while moving around walls etc.
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

}
