package game;

import java.util.*;

public class Notebook {

    // An array list of strings will store the notes
    private List<String> notes = new ArrayList<String>();
    private String name;

    public Notebook(String n){
        name = n;
        add_note(name + "\'s Notebook: ");
    }

    // Function to add a string to the notebook
    public void add_note(String n){
        notes.add(n);
    }

    // Function which prints each line in the notebook
    public void read_notebook(){
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i));
        }
    }
}
