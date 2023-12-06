public class Location {
    protected String name;
    protected String description;
    protected Item key;

    public Location(){
        this.name = "<NAME UNKOWN>";
        this.description = "You have no idea where you are";
    }

    //accessors
    public String getName() {
        return this.name;
    }

    public Item getKey() {
        return this.key;
    }

    //methods
    public void lookAround(Protagonist p){
        if(p.inventory.contains(this.key)){
            System.out.println(this.description);
        } else{
            throw new RuntimeException(this.name + "is locked!");
        }
        //add check for if the location has a key in the first place
        //maybe this should be called unlock?
    }
}
