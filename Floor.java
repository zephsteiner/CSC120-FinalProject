public class Floor extends Location{
    protected Ghost ghost;
    protected Integer floorNum;
    protected Building building;
    protected Item item;

    public Floor(String description, Ghost ghost, Integer floorNum, Building building, Item item){
        this.name = building + " " + Integer.toString(floorNum);
        this.description = description;
        this.ghost = ghost;
        this.floorNum = floorNum;
        this.building = building;
        this.item = item;
        this.key = null;
    }

    public Floor(String description, Item key, Ghost ghost, Integer floorNum, Building building){
        this(description, ghost, floorNum, building, null);
        this.key = key;
    }

    public Floor(Integer floorNum, Building building){
        this("Wow it sure is inside in here", null, floorNum, building, null);
    }

    //methods
    public Ghost getGhost(){
        return this.ghost;
    }

    public Integer getFloorNum(){
        return this.floorNum;
    }

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

    public void addGhost(Ghost g) {
        this.ghost = g;
    }
}
