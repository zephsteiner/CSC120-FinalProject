public class Ghost extends Person {
    Item item;
    String descripton;
  
    public Ghost(String name, Location loc, Item item, String description){
        super(name, loc);
        this.item = item;
        this.descripton = description;
    }
}
