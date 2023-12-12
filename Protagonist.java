import java.util.ArrayList;

public class Protagonist extends Person {
    protected ArrayList<Item> inventory;
    protected Floor loc;

    public Protagonist(Floor location) {
        super("you");
        this.loc = location;
    }

    public String pickUp(Item i) {
        if (this.inventory.contains(i)) {
            throw new RuntimeException("You're already holding " + i.getName());
        }
        this.inventory.add(i);
        return i.getDescription();
    }

    public void drop(Item i) {
        if (!this.inventory.contains(i)) {
            throw new RuntimeException("You don't have" + i.getName());
        }
        this.inventory.remove(i);
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
