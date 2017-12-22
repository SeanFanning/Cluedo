package test;

import board.Map;
import game.token.CharacterPawn;
import game.token.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private CharacterPawn pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new CharacterPawn(1, 1, '*');
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
    public void test_positioning(){ // Test moving and positioning of the pawn
        int[] a1 = new int[] {1, 1};
        assertEquals(Arrays.toString(a1), Arrays.toString(pawn.getPosition()));

        int[] a2 = new int[] {6, 21};
        pawn.setPosition(a2[0], a2[1]);
        assertEquals("[21, 6]", Arrays.toString(pawn.getPosition())); // The x and y vectors are swapped for the array
    }

    @Test
    public void test_values(){ //Test if character values
        assertEquals('*', pawn.getIcon());
        assertEquals("Character Pawn", pawn.getType());
    }
}