import java.util.Hashtable;

public class Building extends Location{
    /** Hashtable of building floors with Integer floor number key for the respective Floor object */
    private Hashtable< Integer, Floor> floors;

    /**
     * Building constructor with ability to set a custom description
     * @param name name of Building
     * @param description description of Building
     */
    public Building(String name, String description){
        this.name = name;
        this.description = description;
        this.key = null;
        this.floors = new Hashtable<>(5);
    }

    /**
     * Building constructor with a default description
     * @param name Name of Building
     */
    public Building(String name){
        this(name, "Sure looks like a building.");
    }

    /**
     * Gets a specific Floor object of a Building
     * @param floorNum the number of the Floor to be found
     * @return the Floor object to be found
     */
    public Floor getFloor(Integer floorNum){
        return this.floors.get(floorNum);
    }

    /**
     * Gets the Hashtable of Floors of a Building
     * @return the Hashtable of Floors of a Building
     */
    public Hashtable<Integer, Floor> getFloors(){
        return this.floors;
    }

    /**
     * Adds an additional floor object to a Building
     * @param floorNum the number of the Floor to add in the Building
     */
    public void addFloor(Integer floorNum){
        Floor f = new Floor(floorNum, this);
        floors.put(floorNum, f);
    }

    /**
     * Sets a default description of the floor number and the building with any items or ghosts also on the floor
     * @param floor the Floor to set the description of
     * @return the description that was set
     */
    public String setDescription(Integer floor){
        Floor f = this.getFloor(floor);
        f.description = "You're on floor " + Integer.toString(floor) + " of " + this.name + ".";
        if (f.hasItem()) {
            f.description += " You see a " + f.getItem().getName() + ".";
        }
        if (f.hasGhost()) {
            f.description += " You see a " + f.getGhost().getDescription() + ".";
        }
        return f.description;
    }

    /**
     * Sets a custom description of a floor
     * @param floor the Floor to set the description of
     * @param more the custom description
     * @param override true if the desired outcome is only the custom description
     * @return the description that was set
     */
    public String setDescription(Integer floor, String more, boolean override) {
        Floor f = this.getFloor(floor);
        // f.description = "You're on floor " + Integer.toString(floor) + " of " + this.name + "." + more;
        if (override) {
            f.description = more;
        } else {
            f.description = this.setDescription(floor) + " " + more;
        }
        return f.description;
    }

    /**
     * Sets a key to a specific Floor of the Buidling
     * @param floor the floor to assign the key to
     * @param newKey the key to assign
     */
    public void setKey(Integer floor, Item newKey){
        Floor f = this.getFloor(floor);
        f.key = newKey;
    }

    /**
     * Sets a Ghost to a specific Floor of the Buidling
     * @param floor the floor to assign the Ghost to
     * @param newGhost the Ghost to assign
     */
    public void setGhost(Integer floor, Ghost newGhost){
        Floor f = this.getFloor(floor);
        f.ghost = newGhost;
    }

    /**
     * Sets a key to a specific Floor of the Buidling
     * @param floor the floor to assign the Item to
     * @param newItem the Item to assign
     */
    public void setItem(Integer floor, Item newItem){
        Floor f = this.getFloor(floor);
        f.item = newItem;
    }
}
  