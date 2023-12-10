public class Item {
    private String name;
    private String description;
    private boolean helpWin;
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

    //accessors
    public boolean getHelpWin(){
        return this.helpWin;
    }

    public String getName(){
        return this.name;
    }

    public boolean getCanPickUp() {
        return this.canPickUp;
    }

    public String getDescription() {
        return this.description;
    }

    //methods
    public String toString() {
        return name + ", " + description;
    }

    public void examine(){
        System.out.println(this.description);
    }
}
