public class Ghost extends Person {
    Item item;
    String description;
  
    /** Default constructor */
    public Ghost() {
        super();
        this.item = null;
        this.description = "<YOU CAN'T SEE GHOSTS>";
    }

    public Ghost(String name, Location loc, Item item, String description){
        super(name, loc);
        this.item = item;
        this.description = description;
    }
}
