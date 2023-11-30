public class Person {
    protected String name;
    protected Location loc;

    /** Default constructor */
    public Person() {
        this.name = "<NAME UNKNOWN>";
        this.loc = null;
    }

    public Person(String name, Location loc){
        this.name = name;
        this.loc = loc;
    }

    //accessors
    public String getName(){
        return this.name;
    }

    public Location getLocation(){
        return this.loc;
    }
}