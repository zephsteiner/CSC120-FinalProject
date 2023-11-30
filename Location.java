public class Location {
    protected String name;
    protected String description;
    protected Item key;

    //acessors
    public String getName() {
        return this.name;
    }

    public Item getKey() {
        return this.key;
    }

    //methods
    public void lookAround(Protagonist p){
        if(p.inventory.contains(this.key)){
            System.out.println(this.description);
        } else{
            throw new RuntimeException(this.name + "is locked!");
        }
    }
}
