package game;

import java.util.*;

public class Notebook {

    // An array list of strings will store the notes
    private List<String> notes = new ArrayList<String>();
    private String name;

    // TODO: Each note should have a type so you can filter (eg. moves made etc)

    public Notebook(String n){
        name = n;
        add_note(name + "\'s Notebook: ");
    }

    // Function to add a string to the notebook
    public void add_note(String n){
        notes.add(n);
    }

    public List<String> getNotes(){
        return notes;
    }
}
