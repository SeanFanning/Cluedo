package game;

import java.util.*;

public class Notebook {

    // An array list of strings will store the notes
    // private List<String> notes = new ArrayList<String>();
    private List<Note> notes = new ArrayList<Note>();
    private String name;

    // TODO: Each note should have a type so you can filter (eg. moves made etc)

    public Notebook(String n){
        name = n;
        add_note(name + "\'s Notebook: ", "Info");
    }

    // Function to add a string to the notebook
    public void add_note(String n, String t){
        Note note = new Note(n, t);
        System.out.println(n);
        notes.add(note);
    }

    public List<Note> getNotes(){
        return notes;
    }

    public List<Note> filterNotes(String type){
        List<Note> found = new ArrayList<Note>();
        Note n;

        for(int i=0; i<notes.size(); i++){
            n = notes.get(i);

            if(n.getType().equals(type)){
                found.add(n);
            }
        }

        return found;
    }
}

