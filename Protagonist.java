import java.util.ArrayList;

public class Protagonist extends Person {
    protected ArrayList<Item> inventory;

    public Protagonist() {
        super("you", null);
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


}
