public class Person {
    /** name of this Person */
    protected String name;

    /** Default constructor */
    public Person() {
        this.name = "<NAME UNKNOWN>";
    }

    /**
     * Constructor with name
     * @param name name of Person
     */
    public Person(String name){
        this.name = name;
    }

    /**
     * Gets this Person's name
     * @return this name
     */
    public String getName(){
        return this.name;
    }
}