import java.util.Hashtable;
public class Outside extends Location {
    private Hashtable<String, Building> map;

    public Outside(){
        this.name = "Outside";
        this.description = "Mmm Fresh air";
        this.key = null;
        this.map = new Hashtable<>();
        this.addToMap("Seelye");
        this.addToMap("Washburn");
        this.addToMap("McConnel");
        this.addToMap("Park");
        this.addToMap("Sessions");
        this.addToMap("mendenhal");
        this.addToMap("parsons");
        this.addToMap("Northrop");
        this.addToMap("Cahpin");
        this.addToMap("Tyler");
        this.addToMap("Hubbard");
        this.addToMap("Comstock");
    }

    //methods
    public Hashtable<String, Building> getMap(){
        return this.map;
    }

    private void addToMap(String name, String description, Item key){
        Building b = new Building(name, description, key);
        this.map.put(b.name, b);
    }

    private void addToMap(String name, String description){
        Building b = new Building(name, description);
        this.map.put(b.name, b);
    }

    private void addToMap(String name){
        Building b = new Building(name);
        this.map.put(b.name, b);
    }

    public void enter(Building b, Protagonist p){
        p.loc = b.getFloor(1);
    }

    public void enter(String building, Protagonist p){
        p.loc = this.map.get(building).getFloor(1);
    }
}
