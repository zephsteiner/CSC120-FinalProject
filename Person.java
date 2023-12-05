public class Person {
    protected String name;

    /** Default constructor */
    public Person() {
        this.name = "<NAME UNKNOWN>";
    }

    public Person(String name, Location loc){
        this.name = name;
    }

    //accessors
    public String getName(){
        return this.name;
    }
}