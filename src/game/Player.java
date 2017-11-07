package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards = new ArrayList<Card>();
    protected Notebook notebook;
    protected Character character;

    public Player(String n, Character c){
        character = c;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public void initNotebook(String n){
        notebook = new Notebook(n);
    }
}
