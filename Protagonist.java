import java.util.ArrayList;

public class Protagonist extends Person {
    protected ArrayList<Item> inventory;
    protected Floor loc;

    public Protagonist(Floor location) {
        super("you");
        this.loc = location;
        this.inventory = new ArrayList<>();
    }

    public Floor getLocation() {
        return this.loc;
    }

    public String pickUp(Item i) {
        if (this.inventory.contains(i)) {
            throw new RuntimeException("You're already holding " + i.getName());
        }
        if (!i.getCanPickUp()) {
            throw new RuntimeException("You cannot and perhaps should not pick up " + i.getName());
        }
        this.inventory.add(i);
        return i.getName();
    }

    public void drop(Item i) {
        if (!this.inventory.contains(i)) {
            throw new RuntimeException("You don't have " + i.getName());
        }
        this.inventory.remove(i);
    }

    public void checkInventory() {
        System.out.println("Inventory:");
        for (Item i : this.inventory) {
            System.out.println(" - " + i.getName());
        }
    }

    public String examine(Item i) {
        return i.getDescription();
    }

    public void talk(Person p) {
        // dialogue time
    }


}
