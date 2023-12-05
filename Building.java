import java.util.Hashtable;

public class Building extends Location{
    private Hashtable< Integer, Floor> floors;

    public Building(String name, String description){
        this.name = name;
        this.description = description;
        this.key = null;
        this.floors = new Hashtable<>(5);
    }

    public Building(String name, String description, Item key){
        this(name, description);
        this.key = key;
    }

    //accessors
    public Floor getFloor(Integer floorNum){
        return this.floors.get(floorNum);
    }

    //methods
    public void addFloor(String name, String description, Item key, Integer floorNum){
        Floor f = new Floor(name, description, key, null);
        floors.put(floorNum, f);
    } 
}
  