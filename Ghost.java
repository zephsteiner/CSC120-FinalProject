public class Ghost extends Person {
    protected Item item;
    protected String description;
  
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

    //accessors
    public Item getItem(){
        return this.item;
    }

    //methods
    public void examine(){
        System.out.println(description);
    }

    public void give(Protagonist p){
        p.pickUp(this.item);
    }
}
