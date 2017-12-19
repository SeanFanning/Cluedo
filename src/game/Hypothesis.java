package game;
import java.util.*;
import board.Map;
import game.token.Slot;

public class Hypothesis {

    private Player[] players;
    private int num_players;

    public Hypothesis(Player[] players, int num_players)  {
        this.players = players;
        this.num_players = num_players;
    }

    public void form_hypothesis(Player player, int player_num, Card ro) {
        Card room = ro; // change this to room player is actually in
        player.addNote("You are in the " + room, "Hypothesis");
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Which character would you like to accuse? [1:6] - \n" + java.util.Arrays.asList(Character.values()));
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextInt();
        } while (number <= 0 || number > 6);
        Card character = Character.values()[number - 1];
        player.addNote("You accused " + character + ".", "Accusation");
        do {
            System.out.println("Which weapon would you like to pick? [1:6] - \n" + java.util.Arrays.asList(Weapon.values()));
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextInt();
        } while (number <= 0 || number > 6);
        Card weapon = Weapon.values()[number - 1];
        player.addNote("You picked the " + weapon + " weapon.", "Hypothesis");
        player.addNote("I formulated the Hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ".", "Hypothesis");

        int count = 0;
        for (Player p : players)    {
            if (count == player_num)    {
                count++;
                continue;
            }
            System.out.println("Player " + p.getName());
            p.addNote(player.getName() + " formulated the hypothesis that " + character + " made the murder in the "+ room + " with the " + weapon + ".", "Hypothesis");
            count++;
        }


        boolean sol_right = true;
        int index = player_num - 1;
        if (index == -1)    {
            index = num_players - 1;
        }

        for (Player pl : players) {
            for (Card c : players[index].getCards()) {
                if (c.equals(weapon)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis");
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis");
                    sol_right = false;
                    break;
                } else if (c.equals(character)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis");
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis");
                    sol_right = false;
                    break;
                } else if (c.equals(room)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis");
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis");
                    sol_right = false;
                    break;
                }

            }
            if (!sol_right) {
                break;
            }
            index--;
            if (index == -1)    {
                index = num_players - 1;
            }
        }
        if (sol_right) {
            player.addNote("No one else has these cards", "Hypothesis");
        }
        else    {
            count = 0;
            for (Player p : players)    {
                if (count == player_num || count == index)    {
                    count++;
                    continue;
                }
                p.addNote(players[index].getName() + " refuted the hypothesis by showing a card.", "Hypothesis");
                count++;
            }
        }

    }

    public void accuse(int player_num)  {

    }
}
