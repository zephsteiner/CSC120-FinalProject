import java.util.Hashtable;

//import com.google.common.graph.*;

public class Game {
    //protected ArrayList<Location> map; // actually this is a graph
    protected Protagonist player;
    protected Hashtable<String,Location> map;


    public Game() {
        // create all the items (z)
        Item oneCard = new Item("OneCard", "This is your key to the campus", false); //start
        Item goatKey = new Item("key", "A heavy brass key", false); //goat
        Item cake = new Item("chocolate cake", "A delicious looking chocolate cake", true); //julia, hubbard
        Item waterGun = new Item("water gun", "A small neon green water pistol. It's empty.", true); //bugsy, parsons


        // create all the dialogue (m)
        // create the ghosts 
        // create the locations
        this.map = new Hashtable<>();
        this.map.put("Seelye", new Building("Seelye"));
        this.map.put("Washburn", new Building("Washburn"));
        this.map.put("McConnell", new Building("McConnell"));
        this.map.put("Park",new Building("Park"));
        this.map.put("Sessions", new Building("Sessions"));
        this.map.put("Mendenhall", new Building("Mendenhall"));
        this.map.put("Parsons", new Building("Parsons"));
        this.map.put("Northrop", new Building("Northrop"));
        this.map.put("Chapin", new Building("Chapin"));
        this.map.put("Tyler", new Building("Tyler"));
        this.map.put("Hubbard", new Building("Hubbard"));
        this.map.put("Comstock", new Building("Comstock"));
        this.map.put("Outside", new Location("Outside"));
        // initialize the protagonist (z)
        // create the graph of the locations
        // 

    }

    public void run() {
        // this should have dialogue and call the explore and add and such methods
                

        //exit the building you're currently in

        //list all the options of being outside

        //enter new building

        //explore method (check if there is anything in the location)
            //from here, go to new location or pick up item or talk to ghost

        //
    }
    public static void main(String[] args) {
        // create new game
        // call the run method

    }
}
