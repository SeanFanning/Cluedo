package game.token;


public class CharacterPawn{

    private int[] position = new int[2];
    public char icon;

    public CharacterPawn(int x, int y, char i){
        position[0] = y;
        position[1] = x;
        icon = i;
    }

    public String getType(){
        return "Character Pawn";
    }

    public char getIcon(){
        return icon;
    }

    public void setPosition(int x, int y){
        position[0] = y;
        position[1] = x;
    }

    public int[] getPosition(){
        return position;
    }
}
