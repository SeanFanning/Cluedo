package test;

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
        boolean a = board.canMove(1,1);
        assertEquals(a, true);
    }
}