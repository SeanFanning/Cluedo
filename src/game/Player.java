package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards = new ArrayList<Card>();
    private Notebook notebook;
    private Character character;

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

    public void addNote(String s){
        notebook.add_note(s);
    }

    public void readNotebook(){
        notebook.read_notebook();
    }

    public String getName(){
        return character.toString();
    }
}
