package test;

import game.*;
import org.junit.*;
import static org.junit.Assert.*;
import game.Player;
import game.Character;
import java.util.ArrayList;
import java.util.Hashtable;

public class MovePlayerTest {

    private static Player[] players;
    MovePlayer mv;

    @Before
    public void setup() {
        int num_players = 6;
        ArrayList<Character> characters = Character.shuffleCharacters(num_players);
        players = Game.initPlayers(6,characters);
        mv = new MovePlayer(num_players,players);
    }

    @Test
    public void test_checkMove()    {
        Hashtable<String, Boolean> hm = new Hashtable();
        hm.put("Travel through tunnel [T]",false);
        hm.put("West [A]",false);
        hm.put("South [S]",false);
        hm.put("North [W]",true);
        hm.put("East [D]",false);

        assertEquals(hm,mv.checkMove(1,7));
    }

}
