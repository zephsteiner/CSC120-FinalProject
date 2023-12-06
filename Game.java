import com.google.common.graph.*;

public class Game {
    //protected ArrayList<Location> map; // actually this is a graph
    protected Protagonist player;
    protected ImmutableGraph<Location> map;


    public Game() {
        // create all the items (z)
        Item oneCard = new Item("OneCard", "This is your key to the campus", false); //start
        Item goatKey = new Item("key", "A heavy brass key", false); //goat
        Item cake = new Item("chocolate cake", "A delicious looking chocolate cake", true); //julia, hubbard
        Item waterGun = new Item("water gun", "A small neon green water pistol. It's empty.", true); //bugsy, parsons



        // create all the dialogue (m)
        // create the ghosts 
        // create the locations
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
