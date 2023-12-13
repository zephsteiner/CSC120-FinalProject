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

    public void addFloor(Integer floorNum){
        Floor f = new Floor(floorNum, this);
        floors.put(floorNum, f);
    }

    public String setDescription(Integer floor){
        Floor f = this.getFloor(floor);
        f.description = "You're on floor " + Integer.toString(floor) + " of " + this.name + ".";
        if (f.getItem() != null) {
            if (f.getItem().getName().equals("key")){
                f.description += " You see a " + f.getItem().getName() + ". " + f.getItem().getDescription() + ".";
            } else{
                f.description += " You see a " + f.getItem().getName() + ".";
            }
        }
        if (f.getGhost() != null) {
            f.description += " You see a " + f.getGhost().getDescription() + ".";
        }
        return f.description;
    }

    public void setDescription(Integer floor, String more, boolean override) {
        Floor f = this.getFloor(floor);
        // f.description = "You're on floor " + Integer.toString(floor) + " of " + this.name + "." + more;
        if (override) {
            f.description = more;
        } else {
            f.description = this.setDescription(floor) + " " + more;
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
  