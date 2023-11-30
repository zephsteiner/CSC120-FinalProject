public class Person {
    String name;
    Location loc;

    /** Default constructor */
    public Person() {
        this.name = "<NAME UNKNOWN>";
        this.loc = null;
    }

    public Person(String name, Location loc){
        this.name = name;
        this.loc = loc;
    }
}