public class Floor extends Location{
    public Floor(String name, String description){
        this.name = name;
        this.description = description;
        this.key = null;
    }

    public Floor(String name, String description, Item key){
        this(name, description);
        this.key = key;
    }
}
