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
    // // key, ghost and item
    // protected void addFloor(String description, Item key, Ghost ghost, Item item,Integer floorNum){
    //     Floor f = new Floor(description, key, ghost, item, floorNum, this);
    //     floors.put(floorNum, f);
    // }

    // // key and ghost
    // protected void addFloor(String description, Item key, Ghost ghost, Integer floorNum){
    //     addFloor(description, key, ghost, null, floorNum);
    // }

    // // ghost and item
    // protected void addFloor(String description, Ghost ghost, Item item, Integer floorNum){
    //     addFloor(description, null, ghost, item, floorNum);
    // }

    // // key and item
    // protected void addFloor(String description, Item key, Item item, Integer floorNum){
    //     addFloor(description, key, null, item, floorNum);
    // }

    //none of it
    public void addFloor(Integer floorNum){
        Floor f = new Floor(floorNum, this);
        floors.put(floorNum, f);
    }

    public void setDescription(Integer floor){
        Floor f = this.getFloor(floor);
        f.description = "You're on floor " + Integer.toString(floor) + " of " + this.name + ".";
        if (f.getItem() != null) {
            f.description += " You see a " + f.getItem().getName() + ".";
        }
        if (f.getGhost() != null) {
            //f.description += " You see a " + f.getGhost().getDescription() + ".";
        }
    }

    public void setKey(Integer floor, Item newKey){
        Floor f = this.getFloor(floor);
        f.key = newKey;
    }

    public void setGhost(Integer floor, Ghost newGhost){
        Floor f = this.getFloor(floor);
        f.ghost = newGhost;
    }

    public void setItem(Integer floor, Item newItem){
        Floor f = this.getFloor(floor);
        f.item = newItem;
    }
}
  