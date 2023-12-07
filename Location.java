public class Location {
    protected String name;
    protected String description;
    protected Item key;

    public Location(){
        this.name = "<NAME UNKOWN>";
        this.description = "You're somewhere, all right.";
    }

    public Location(String name) {
        this();
        this.name = name;
    }

    //accessors
    public String getName() {
        return this.name;
    }

    public Item getKey() {
        return this.key;
    }

    //methods
    public void printDescription(Protagonist p){
        if(this.key == null){
           System.out.println(this.description); 
        }
        else if(p.inventory.contains(this.key)){
            System.out.println(this.description);
        } else{
            throw new RuntimeException(this.name + "is locked!");
        }
        //add check for if the location has a key in the first place
        //maybe this should be called unlock?
    }

    public void printDescription(){
        System.out.println(this.description);
    }
}
