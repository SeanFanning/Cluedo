package game;

public class Note {

    private String note;
    private String type;

    public Note(String n, String t){
        note = n;
        type = t;
    }

    public String getNote(){
        return note;
    }
    public String getType(){
        return type;
    }
}