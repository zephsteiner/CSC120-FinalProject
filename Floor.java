public class Floor extends Location{
    protected Ghost ghost;
    protected Integer floorNum;
    protected Building building;

    public Floor(String description, Ghost ghost, Integer floorNum, Building building){
        this.name = building + " " + Integer.toString(floorNum);
        this.description = description;
        this.ghost = ghost;
        this.floorNum = floorNum;
        this.building = building;
        this.key = null;
    }

    public Floor(String description, Item key, Ghost ghost, Integer floorNum, Building building){
        this(description, ghost, floorNum, building);
        this.key = key;
    }

    public Floor(Integer floorNum, Building building){
        this("Wow it sure is inside in here", null, floorNum, building);
    }

    //methods
    public Ghost getGhost(){
        return this.ghost;
    }

    public void goUp(Protagonist p){
        Floor up = this.building.getFloor(this.floorNum + 1);
        if(!this.building.getFloors().containsKey(this.floorNum+1)){
            throw new RuntimeException("You're on the top floor!");
        } else if(up.key == null | p.inventory.contains(up.getKey())){
            p.loc = up;
            up.printDescription(p);  
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
            down.printDescription(p); 
        } else{
            throw new RuntimeException("The floor below you is locked!");
        }
    }

    public void leave(Protagonist p){
        if(this.floorNum != 1){
            throw new RuntimeException("You can't leave from floor " + Integer.toString(floorNum) + ". Go to the first floor.");
        } else{
            p.loc = Outside;
            //i don't know how to get this to point to outside without having to make an instance in the floor class
        }
    }
}
