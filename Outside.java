import java.util.ArrayList;
public class Outside extends Location {
    private ArrayList<Building> map;

    public Outside(){
        this.name = "Outside";
        this.description = "Mmm Fresh air";
        this.key = null;
        this.map = new ArrayList<>();
    }

    //methods
    public ArrayList<Building> getMap(){
        return this.map;
    }   
}
