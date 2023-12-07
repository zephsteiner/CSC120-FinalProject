import java.util.Hashtable;

public class Building extends Location{
    private Hashtable< Integer, Floor> floors;

    public Building(String name, String description){
        this.name = name;
        this.description = description;
        this.key = null;
        this.floors = new Hashtable<>(5);
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
    // key, ghost and item
    protected void addFloor(String description, Item key, Ghost ghost, Item item,Integer floorNum){
        Floor f = new Floor(description, key, ghost, item, floorNum, this);
        floors.put(floorNum, f);
    }

    // key and ghost
    protected void addFloor(String description, Item key, Ghost ghost, Integer floorNum){
        addFloor(description, key, ghost, null, floorNum);
    }

    // ghost and item
    protected void addFloor(String description, Ghost ghost, Item item, Integer floorNum){
        addFloor(description, null, ghost, item, floorNum);
    }

    // key and item
    protected void addFloor(String description, Item key, Item item, Integer floorNum){
        addFloor(description, key, null, item, floorNum);
    }

    //none of it
    protected void addFloor(Integer floorNum){
        Floor f = new Floor(floorNum, this);
        floors.put(floorNum, f);
    }
}
  