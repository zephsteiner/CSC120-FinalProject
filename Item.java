public class Item {
    private String name;
    private String description;
    private boolean helpWin;
    private boolean canPickUp = true;

    public Item(String name, String description, boolean helpWin){
        this.name = name;
        this.description = description;
        this.helpWin = helpWin;
    }

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
