public class Item {
    /** name of this Item */
    private String name;

    /** description of this Item */
    private String description;

    /** Do you need this Item to win? */
    private boolean helpWin;

    /** Can you pick this Item up? */
    private boolean canPickUp = true;

    /**
     * Item constructor
     * @param name name of the Item
     * @param description description of Item
     * @param helpWin is this item needed to win the game?
     */
    public Item(String name, String description, boolean helpWin){
        this.name = name;
        this.description = description;
        this.helpWin = helpWin;
    }

    /**
     * Item constructor for non-pickupable items
     * @param name name of the Item
     * @param description description of Item
     * @param helpWin is this item needed to win the game?
     * @param pickUp can you pick up this item?
     */
    public Item(String name, String description, boolean helpWin, boolean pickUp) {
        this(name, description, helpWin);
        this.canPickUp = pickUp;
    }

    /**
     * Gets if the Item is needed to win the game
     * @return true if it is needed
     */
    public boolean getHelpWin(){
        return this.helpWin;
    }

    /**
     * Gets the name of this Item
     * @return the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets if the item can be picked up
     * @return true if the Item can be picked up
     */
    public boolean getCanPickUp() {
        return this.canPickUp;
    }

    /**
     * Gets the description of the Item
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Overwritten toString for Item
     * @return name and description of this item
     */
    public String toString() {
        return name + ", " + description;
    }
}
