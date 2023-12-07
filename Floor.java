public class Floor extends Location{
    protected Ghost ghost;

    public Floor(String name, String description, Ghost ghost){
        this.name = name;
        this.description = description;
        this.ghost = ghost;
        this.key = null;
    }

    public Floor(String name, String description, Item key, Ghost ghost){
        this(name, description, ghost);
        this.key = key;
    }
}
