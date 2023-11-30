public class Item {
    private String name;
    private String description;
    private boolean helpWin;

    public Item(String name, String description, boolean helpWin){
        this.name = name;
        this.description = description;
        this.helpWin = helpWin;
    }

    //acessors
    public boolean getHelpWin(){
        return this.helpWin;
    }

    public String getName(){
        return this.name;
    }

    //methods
    public void examine(){
        System.out.println(this.description);
    }
}
