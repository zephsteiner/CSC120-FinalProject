public class Location {
    /** name of this Location */
    protected String name;

    /** description of this Location */
    protected String description;

    /** key to this Location */
    protected Item key;

    /**
     * Default Location constructor
     */
    public Location(){
        this.name = "<NAME UNKOWN>";
        this.description = "You're somewhere, all right.";
    }

    /**
     * Location constructor with name
     * @param name name of Location
     */
    public Location(String name) {
        this();
        this.name = name;
    }

    /**
     * Gets the name of this Location
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the key to this location
     * @return the key
     */
    public Item getKey() {
        return this.key;
    }

    /**
     * Prints the description of this Location if the Protagonist holds this key
     * @param p the player of this game
     */
    public void printDescription(Protagonist p){
        if(this.key == null){
           System.out.println(this.description); 
        }
        else if(p.inventory.contains(this.key)){
            System.out.println(this.description);
        } else{
            throw new RuntimeException(this.name + "is locked!");
        }
    }

    /**
     * Prints the description of this Location
     */
    public void printDescription(){
        System.out.println(this.description);
    }
}
