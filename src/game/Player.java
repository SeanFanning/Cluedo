package game;

import game.token.CharacterPawn;
import game.token.Slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {

    private List<Card> cards = new ArrayList<Card>();
    private Notebook notebook;
    private Character character;
    private CharacterPawn pawn;
    private boolean inGame = true;

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

    public void addNote(String n, String t, Boolean b){
        notebook.add_note(n, t, b);
    }

    public List<Note> getNotes(){
        return notebook.getNotes();
    }

    public List<Note> filterNotes(String t){
        return notebook.filterNotes(t);
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

    public boolean returnGame() {
        return inGame;
    }
    public void rm() {
        inGame = false;
    }
}
