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

    public Building(String name){
        this(name, "Sure looks like a building.");
    }

    //accessors
    public Floor getFloor(Integer floorNum){
        return this.floors.get(floorNum);
    }

    public Hashtable<Integer, Floor> getFloors(){
        return this.floors;
    }

    //methods
    protected void addFloor(String description, Item key, Ghost ghost, Integer floorNum){
        Floor f = new Floor(description, key, ghost, floorNum, this);
        floors.put(floorNum, f);
    }

    protected void addFloor(String description, Ghost ghost, Integer floorNum){
        Floor f = new Floor(description, ghost, floorNum, this);
        floors.put(floorNum, f);
    }

    protected void addFloor(String description, Item key, Integer floorNum){
        Floor f = new Floor(description, key, null, floorNum, this);
        floors.put(floorNum, f);
    } 

    protected void addFloor(Integer floorNum){
        Floor f = new Floor(floorNum, this);
        floors.put(floorNum, f);
    }
}
  