import java.util.ArrayList;

public class Protagonist extends Person {
    /** stores all the Items the Protagonist has picked up */
    protected ArrayList<Item> inventory;

    /** where the Protagonist is located */
    protected Floor loc;

    /**
     * Constructor for Protagonist, initializes their location
     * @param location where the Protagonist starts
     */
    public Protagonist(Floor location) {
        super("you");
        this.loc = location;
        this.inventory = new ArrayList<>();
    }

    /**
     * Gets the Protagonist's current Location
     * @return this location
     */
    public Floor getLocation() {
        return this.loc;
    }

    /**
     * Adds the specified Item to the Protagonist's inventory
     * @param i Item to add
     * @returnthe name of the Item added
     */
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

    /**
     * Prints the Protagonist's current inventory
     */
    public void checkInventory() {
        System.out.println("Inventory:");
        for (Item i : this.inventory) {
            System.out.println(" - " + i.getName());
        }
    }

    /**
     * Gets the description of the specified Item
     * @param i Item to get description of
     * @return the description of the Item
     */
    public String examine(Item i) {
        return i.getDescription();
    }
}
