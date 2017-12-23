package test;
import board.Map;
import game.Character;
import game.Game;
import game.Player;
import org.junit.*;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private static ArrayList<Character> characters;
    private static Player[] players;
    private static int num_players;
    private static Map map = new Map();

    @Before
    public void setup() {
        characters = Character.shuffleCharacters(num_players);
        players = Game.initPlayers(6,characters);
    }

    @Test
    public void test_Initialization()   {
        assertEquals(players.length,6);
    }

}
