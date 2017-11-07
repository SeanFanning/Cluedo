package game;

import java.util.*;

public class Notebook {

    private List<String> notes = new ArrayList<String>();
    private String name;

    public Notebook(String n){
        name = n;
        add_note(name + "\'s Notebook: ");
    }

    public void add_note(String n){
        notes.add(n);
    }

    public void read_notebook(){
        for(int i=0; i<notes.size(); i++){
            System.out.println(notes.get(i));
        }
    }
}
