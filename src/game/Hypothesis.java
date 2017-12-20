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

    private Card[] build_hypothesis(Player player, String room, String rename) {

        List characters = java.util.Arrays.asList(Character.values());
        List weapons = java.util.Arrays.asList(Weapon.values());

        String ch = "";
        int x = 1;
        for (Object c : characters) {
            ch = ch + x + ") " + c + " ";
            x = x +1;
        }

        String we = "";
        int y = 1;
        for (Object w : weapons)    {
            we = we + y + ") " + w + " ";
            y = y + 1;
        }

        player.addNote("You are in the " + room, "Hypothesis",true);
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Which character would you like to pick? \n" + ch);
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextInt();
        } while (number <= 0 || number > 6);
        Card character = Character.values()[number - 1];
        player.addNote("You picked " + character + ".", rename,true);
        do {
            System.out.println("Which weapon would you like to pick? \n" + we);
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextInt();
        } while (number <= 0 || number > 6);
        Card weapon = Weapon.values()[number - 1];
        player.addNote("You picked the " + weapon + " weapon.", rename,true);


        Card[] ans = {character, weapon};
        return ans;
    }

    public void form_hypothesis(Player player, int player_num, String room) {

        Card [] ans = build_hypothesis(player,room,"Hypothesis");
        Card character = ans[0];
        Card weapon = ans[1];

        player.addNote("I formulated the Hypothesis that " + character + " made the murder in the " + room + " with the " + weapon + ".", "Hypothesis",true);
        int count = 0;
        for (Player p : players)    {
            if (count == player_num)    {
                count++;
                continue;
            }
            p.addNote(player.getName() + " formulated the hypothesis that " + character + " made the murder in the "+ room + " with the " + weapon + ".", "Hypothesis",false);
            count++;

            // Move the target character to the same room as the current player
            if(p.getName().equals(character.toString())){
                p.setPos(player.getPos()[1], player.getPos()[0]);
                p.addNote("You have been moved for a Hypothesis to the " + room, "Hypothesis", false);
            }
        }


        boolean sol_right = true;
        int index = player_num - 1;
        if (index == -1)    {
            index = num_players - 1;
        }

        for (Player pl : players) {
            for (Card c : players[index].getCards()) {
                if (c.equals(weapon)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis",true);
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis",false);
                    sol_right = false;
                    break;
                } else if (c.equals(character)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis",true);
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis",false);
                    sol_right = false;
                    break;
                } else if (c.toString().equals(room)) {
                    player.addNote(players[index].getName() + " has card " + c + ". Hypothesis has been refuted.", "Hypothesis",true);
                    players[index].addNote("I refuted the hypothesis showing card \"" + c + "\"", "Hypothesis",false);
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
            player.addNote("No one else has these cards", "Hypothesis",true);
        }
        else    {
            count = 0;
            for (Player p : players)    {
                if (count == player_num || count == index)    {
                    count++;
                    continue;
                }
                p.addNote(players[index].getName() + " refuted the hypothesis by showing a card.", "Hypothesis",false);
                count++;
            }
        }

    }

    public boolean accuse(Player player, int player_num, String room)  {
        Card [] ans = build_hypothesis(player,room,"Accusation");
        Card character = ans[0];
        Card weapon = ans[1];

        boolean sol_right = true;
        int index = player_num - 1;
        if (index == -1)    {
            index = num_players - 1;
        }

        for (Player pl : players) {
            for (Card c : players[index].getCards()) {
                if (c.equals(weapon)) {
                    sol_right = false;
                    break;
                } else if (c.equals(character)) {
                    sol_right = false;
                    break;
                } else if (c.toString().equals(room)) {
                    sol_right = false;
                    break;
                }

            }
            if (!sol_right) {
                System.out.println("Your solution is wrong. You are the weakest link, goodbye.");
                player.rm();
                break;
            }
            index--;
            if (index == -1)    {
                index = num_players - 1;
            }
        }
        return sol_right;
    }
}
