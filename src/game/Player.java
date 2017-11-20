package game;

import game.token.CharacterPawn;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards = new ArrayList<Card>();
    private Notebook notebook;
    private Character character;
    private CharacterPawn pawn;

    public Player(String n, Character c, int[] startingPos, char icon){
        character = c;
        pawn = new CharacterPawn(startingPos[0], startingPos[1], icon);
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

    public List<String> getNotes(){
        return notebook.getNotes();
    }

    public String getName(){
        return character.toString();
    }

    public int[] getPos(){
        return pawn.getPosition();
    }

    public void setPos(int x, int y){
        pawn.setPosition(x, y);
    }

    public char getIcon(){
        return pawn.getIcon();
    }
}
