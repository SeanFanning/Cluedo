package game;
import java.util.*;
import board.Map;
import game.token.Slot;

public class Hypothesis {

    Player[] players;

    public Hypothesis(Player[] players)  {
        this.players = players;
    }

    public void form_hypothesis(int player_num) {
        Card room = Rooms.getRandom();
        System.out.println("You are in the " + room);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which character would you like to accuse? [1:6] - \n" + java.util.Arrays.asList(Character.values()));
        String c_value = scanner.nextLine();
        Card character = Character.values()[Integer.parseInt(c_value) - 1];
        System.out.println("You accused " + character + ".");
        System.out.println("Which weapon would you like to pick? [1:6] - \n" + java.util.Arrays.asList(Weapon.values()));
        String w_value = scanner.nextLine();
        Card weapon = Weapon.values()[Integer.parseInt(w_value) - 1];
        System.out.println("You picked the " + weapon + " weapon.");
        System.out.println("I formulated the Hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ".");
        players[player_num - 1].addNote("I formulated the Hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ",");

        boolean sol_right = true;
        for (int i = (player_num - 2); i >= 0; i--) {
            for (Card c : players[i].getCards()) {
                if (c.equals(weapon)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                } else if (c.equals(character)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                } else if (c.equals(room)) {
                    System.out.println("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    players[player_num - 1].addNote("Player " + (i + 1) + " has card " + c + ". Hypothesis has been refuted.");
                    sol_right = false;
                    break;
                }
            }
            if (!sol_right) {
                break;
            }
        }
    }
}
