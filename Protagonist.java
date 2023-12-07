import java.util.ArrayList;

public class Protagonist extends Person {
    protected ArrayList<Item> inventory;
    protected Floor loc;

    public Protagonist(Floor location) {
        super("you");
        this.loc = location;
    }

    public void pickUp(Item i) {
        this.inventory.add(i);
        //add check for item already there
    }

    public void drop(Item i) {
        this.inventory.remove(i);
        //add check
    }

    public void use(Item i) {
        //smthn
    }

    public String checkInventory() {
        //do this
        return "girl help";
    }

    public String examine(Item i) {
        return i.getDescription();
    }

    public void explore() {
        //
    }

    public void talk(Person p) {
        // dialogue time
    }


}
