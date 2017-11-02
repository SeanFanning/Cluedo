package game;

/*import game.token.Pawn;
import game.token.Weapon;*/

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private Card[] solution;
    private Card[] new_player;

    public Game(int num_players)   {
        this.solution = this.initSolution();
        this.new_player = this.initNewPlayer(num_players);
    }
    public static void main(String[] args){
        /*Pawn p1 = new Pawn(4, 6, "Bob");

        System.out.println(p1.getName());
        System.out.println(p1.getPosition()[0] + " " + p1.getPosition()[1]);

        p1.setPosition(8, 37);
        System.out.println(p1.getPosition()[0] + " " + p1.getPosition()[1]);

        Weapon w1 = new Weapon(7, 1, "Lead Pipe");

        System.out.println(w1.getName());
        System.out.println(w1.getPosition()[0] + " " + w1.getPosition()[1]);

        w1.setPosition(2, 73);
        System.out.println(w1.getPosition()[0] + " " + w1.getPosition()[1]);*/

        int num_players = 6;
        Game new_game = new Game(num_players);


    }

    public Card[] initNewPlayer(int num_players)   {
        this.new_player = new Card[num_players];

        for(int x=0; x<num_players; x++)    {
            this.new_player[x] = Character.getRandom();
            System.out.println("Player " + (x + 1) + " is: " + this.new_player[x]);
        }

        return this.new_player;
    }

    public Card[] initSolution()    {
        this.solution = new Card[3];
        this.solution[0] = Character.getRandom();
        this.solution[1] = Weapon.getRandom();
        this.solution[2] = Rooms.getRandom();
        System.out.println("Character: " + this.solution[0] + " Weapon: " + this.solution[1] + " Room: " + this.solution[2]);
        return this.solution;
    }

    enum Character implements Card{
        MISS_SCARLET,
        PROFESSOR_PLUM,
        MRS_PEACOCK,
        REVEREND,
        COL_MUSTARD,
        MRS_WHITE;

        private static final List<Character> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();


        public String getType() {
            return "Character";
        }

        public static Character getRandom() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

    }

    enum Rooms implements Card{
        KITCHEN,
        BALLROOM,
        LIBRARY,
        CONSERVATORY,
        LOUNGE,
        BILLIARD_ROOM,
        DINING_ROOM,
        STUDY,
        HALL;

        private static final List<Rooms> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();


        public String getType() {
            return "Room";
        }

        public static Rooms getRandom() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

    }

    enum Weapon implements Card{
        LEAD_PIPE,
        REVOLVER,
        KNIFE,
        CANDLESTICK,
        POISON,
        ROPE;

        private static final List<Weapon> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();


        public String getType() {
            return "Weapon";
        }

        public static Weapon getRandom() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

    }

}