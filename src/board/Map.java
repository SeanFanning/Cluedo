package board;
import game.Player;

import game.Rooms;
import game.token.Slot;

import java.io.*;
import java.util.Arrays;

public class Map {

    private char[][] map_chars = new char[21][24]; // Character array to draw the map on the screen
    private Slot[][] map_slots = new Slot[21][24]; // Array containing all of the slots on the board

    public Map(){
        readMapFromFile();
        generateMapSlots();
    }

    // Generate the slot array from the character array
    private void generateMapSlots(){
        for(int i=0; i<map_chars.length; i++){
            for(int j=0; j<map_chars[0].length; j++){
                map_slots[i][j] = convertMap(j, i);
            }
        }
    }

    // Reads the map from the text file and converts it into the character array
    private void readMapFromFile(){
        try {
            File file = new File("src/board/map.txt"); // Map is stored in a textfile
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i=20;
            while((line = bufferedReader.readLine()) != null) {
                String l = line.toString();
                for(int j=0; j<24; j++) {
                    char c = l.charAt(j);
                    map_chars[i][j] = c;
                }
                i--;
            }
        } catch ( IOException e){
            System.err.println("\nIOException:\t" + e.getMessage());
        }
    }

    // Check if the player can move to a certain slot
    public boolean canMove(int x, int y){
        if(map_chars[x][y] != 'X'){
            return true;
        }
        else{
            return false;
        }
    }

    //Converts the characters in the char map into slots (Used by generateMapSlots method)
    public Slot convertMap(int x, int y){
        char c = map_chars[y][x];
        Slot slot;

        switch (c){ // Create a slot for whatever character is in that position in the map
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
            case 'T':   slot = new Slot("Tunnel");
                break;
            case ' ':   slot = new Slot("Hallway");
                break;
            case 'X':   slot = new Slot("Wall");
                break;
            default:    slot = new Slot("Error");
        }

        return slot;
    }

    // Returns the slot at that location
    public Slot getSlot(int x, int y){
        return map_slots[x][y];
    }

    // Gets the absolute distace for a player to travel between 2 points
    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    // Prints the map with all of the players shown in their current locations
    public void printMap(Player[] players, int num_players){

        char[][] tmp_map = new char[map_chars.length][];
        for(int i=0; i<map_chars.length; i++){
            tmp_map[i] = map_chars[i].clone();
        }

        for(int i=0; i<num_players; i++){
            int x = players[i].getPos()[0];
            int y = players[i].getPos()[1];

            tmp_map[y][x] = players[i].getIcon();
        }

        for(int i=map_chars.length-1; i>=0; i--) {
            System.out.println(Arrays.toString(tmp_map[i]));
        }
    }

}
