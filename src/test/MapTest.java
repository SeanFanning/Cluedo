package test;

import game.token.Slot;
import org.junit.Test;
import org.junit.Before;
import board.Map;

import static org.junit.Assert.*;

public class MapTest {

    private Map board;
    private int dist_5, dist_11;

    @Before
    public void setUp() throws Exception {
        board = new Map();
        dist_5 = board.getDistance(0, 0, 0, 5);
        dist_11 = board.getDistance(6,2,3,10);
    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
//
//    @org.junit.Test
//    public void canMove() throws Exception {
//    }
//
//    @org.junit.Test
//    public void getDistance() throws Exception {
//    }

    @Test
    public void test_getDistance(){
        assertEquals(dist_5, 5);
        assertEquals(dist_11, 11);
    }

    @Test
    public void test_canMove(){
        // Test moving in a room
        boolean a = board.canMove(1,1);
        assertEquals(true, a);

        // Test moving in a hallway
        boolean b = board.canMove(2, 7);
        assertEquals(true, b);

        // Test moving into a wall
        boolean c = board.canMove(0, 7);
        assertEquals(false, c);
    }

    @Test
    public void test_Slots(){ //Test if the correct board has been loaded, and the slots are correct
        //Test Hallway
        Slot s1 = board.getSlot(1, 7);
        assertEquals("Hallway", s1.getSlot());

        //Test Wall
        Slot s2 = board.getSlot(0, 0);
        assertEquals("Wall", s2.getSlot());

        //Test Rooms
        Slot s3 = board.getSlot(2, 2);
        assertEquals("LOUNGE", s3.getSlot());
        Slot s4 = board.getSlot(2, 21);
        assertEquals("STUDY", s4.getSlot());
        Slot s5 = board.getSlot(12, 21);
        assertEquals("BILLIARD_ROOM", s5.getSlot());

        //Test Tunnel
        Slot s6 = board.getSlot(3, 22);
        assertEquals("Tunnel", s6.getSlot());
    }
}