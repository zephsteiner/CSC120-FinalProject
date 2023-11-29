import java.util.ArrayList;

public class Building extends Location{
    ArrayList<Floor> floors;

    public Building(String name, String description){
        this.name = name;
        this.description = description;
        this.key = null;
        this.floors = new ArrayList<>(5);
    }

    public Building(String name, String description, Item key){
        this(name, description);
        this.key = key;
    }
}
  