package test;

import game.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import game.Hypothesis;
import game.Player;
import game.Character;
import java.util.ArrayList;

public class HypothesisTest {

    private static ArrayList<Character> characters;
    private static Player[] players;
    Hypothesis hyp_test;
    Card [] solution;
    private int num_players = 6;

    @Before
    public void setup() {
        characters = Character.shuffleCharacters(num_players);
        players = Game.initPlayers(6,characters);
        solution = Game.initSolution();
        hyp_test = new Hypothesis(players,num_players);
        Game.dealCards(players,Game.initDeck(solution),num_players);
    }

    @Test
    public void test_check_room()   {
        assertEquals(false,hyp_test.check_player_room("Hallway"));
        assertEquals(false,hyp_test.check_player_room("Tunnel"));
        for (game.Rooms room : game.Rooms.values()) {
            assertEquals(true,hyp_test.check_player_room(room.toString()));
        }
    }

    @Test
    public void test_true_form_hypothesis()  {
        String[] a_sol = find_sol(solution);
        String sol = a_sol[0] + a_sol[1];

        ByteArrayInputStream in = new ByteArrayInputStream(sol.getBytes());
        System.setIn(in);
        assertEquals(true,hyp_test.form_hypothesis(players[0],0,solution[2].toString()));
    }

    @Test
    public void test_false_form_hypothesis()  {
        String [] a_sol = find_sol(solution);
        int x = Integer.parseInt(a_sol[1]);
        if (x == 1) {
            x = 6;
        }
        else    {
            x--;
        }
        String sol = a_sol[0] + Integer.toString(x);
        ByteArrayInputStream in = new ByteArrayInputStream(sol.getBytes());
        System.setIn(in);
        assertEquals(false,hyp_test.form_hypothesis(players[0],0,solution[2].toString()));
    }

    @Test
    public void test_accuse()  {
        String[] a_sol = find_sol(solution);
        String sol = a_sol[0] + a_sol[1];
        ByteArrayInputStream in = new ByteArrayInputStream(sol.getBytes());
        System.setIn(in);
        assertEquals(true,hyp_test.accuse(players[0],0,solution[2].toString()));
    }

    public String[] find_sol(Card[] solution)    {

        String [] sol = {"1\n","1"};
        if (solution[0].toString().equals("MISS_SCARLET"))  {
            sol[0] = "1\n";
        }
        else if (solution[0].toString().equals("PROFESSOR_PLUM")) {
            sol[0] = "2\n";
        }
        else if (solution[0].toString().equals("MRS_PEACOCK")) {
            sol[0] = "3\n";
        }
        else if (solution[0].toString().equals("REVEREND")) {
            sol[0] = "4\n";
        }
        else if (solution[0].toString().equals("COL_MUSTARD")) {
            sol[0] = "5\n";
        }
        else if (solution[0].toString().equals("MRS_WHITE")) {
            sol[0] = "6\n";
        }

        if (solution[1].toString().equals("LEAD_PIPE"))  {
            sol[1] = "1";
        }
        else if (solution[1].toString().equals("REVOLVER")) {
            sol[1]= "2";
        }
        else if (solution[1].toString().equals("KNIFE")) {
            sol[1]= "3";
        }
        else if (solution[1].toString().equals("CANDLESTICK")) {
            sol[1]= "4";
        }
        else if (solution[1].toString().equals("POISON")) {
            sol[1]= "5";
        }
        else if (solution[1].toString().equals("ROPE")) {
            sol[1]= "6";
        }
        return sol;

    }

}
