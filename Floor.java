public class Floor extends Location{
    /** Ghost that resides here */
    public Ghost ghost;

    /** the number of this Floor in a Building */
    protected Integer floorNum;

    /** the Building this Floor is in */
    public Building building;

    /** Item located on this Floor */
    public Item item;

    /**
     * Floor constructor
     * @param description description of the Floor
     * @param key key to the Floor
     * @param ghost Ghost that resides on the Floor
     * @param item Item located on the Floor
     * @param floorNum number of the Floor in its Building
     * @param building Building the Floor is located in
     */
    public Floor(String description, Item key, Ghost ghost, Item item, Integer floorNum, Building building){
        this.name = building + " " + Integer.toString(floorNum);
        this.description = description;
        this.ghost = ghost;
        this.floorNum = floorNum;
        this.building = building;
        this.item = item;
        this.key = key;
    }

    /**
     * simplified Floor constructor with only the Building and location within that Building
     * @param floorNum number of the Floor in its Building
     * @param building Building the Floor is located in
     */
    public Floor(Integer floorNum, Building building){
        this("You're somewhere", null, null, null, floorNum, building);
    }

    /**
     * Gets the Item located on this Floor
     * @return the Item
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * Does this Floor have an Item on it?
     * @return true if it does
     */
    public boolean hasItem() {
        if (this.item != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the Ghost located on this Floor
     * @return
     */
    public Ghost getGhost() {
        return this.ghost;
    }

    /**
     * Does this Floor have a Ghost?
     * @return true if there is a Ghost
     */
    public boolean hasGhost() {
        if (this.ghost != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Does this floor have a key?
     * @return true if there is a key
     */
    public boolean hasKey() {
        if (this.key != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the Floor's description
     * @return the Floor's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the number of this Floor inside its Building
     * @return
     */
    public Integer getFloorNum(){
        return this.floorNum;
    }

    /**
     * Change the location of the protagonist to one Floor higher in its Building
     * @param p the Protagonist to set the new location
     */
    public void goUp(Protagonist p){
        Floor up = this.building.getFloor(this.floorNum + 1);
        if(!this.building.getFloors().containsKey(this.floorNum+1)){
            throw new RuntimeException("You're on the top floor!");
        } else if(up.key == null | p.inventory.contains(up.getKey())){
            p.loc = up;
            up.printDescription();  
        } else{
            throw new RuntimeException("The floor above you is locked!");
        }
    }

    /**
     * Change the location of the protagonist to one Floor lower in its Building
     * @param p the Protagonist to set the new location
     */
    public void goDown(Protagonist p){
        Floor down = this.building.getFloor(this.floorNum - 1);
        if(!this.building.getFloors().containsKey(this.floorNum - 1)){
            throw new RuntimeException("You're on the bottom floor!");
        } else if(down.key == null | p.inventory.contains(down.getKey())){
            p.loc = down; 
            down.printDescription(); 
        } else{
            throw new RuntimeException("The floor below you is locked!");
        }
    }

    /**
     * Assigns a Ghost to this Floor 
     * @param g the Ghost to assign
     */
    public void addGhost(Ghost g) {
        this.ghost = g;
    }
}
