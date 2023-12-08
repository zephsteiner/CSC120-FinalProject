import java.util.Hashtable;

//import com.google.common.graph.*;

public class Game {
    protected Protagonist player;
    protected Hashtable<String,Building> map;

    public void enter(Building b, Protagonist p){
        boolean unlocked = b.getFloor(1).key == null | p.inventory.contains(b.getFloor(1).key);
        boolean ground = p.loc.getFloorNum() == 1;
        if(unlocked && ground){
            p.loc = b.getFloor(1);
            b.getFloor(1).printDescription();
        } else if(!unlocked && ground){
            throw new RuntimeException("This building is locked.");
        } else if(!ground){
            throw new RuntimeException("You cannot leave a building from floor " + Integer.toString(p.loc.getFloorNum()) + ". Please go to floor 1.");
        }
    }

    public void enter(String building, Protagonist p){
        Building b = this.map.get(building);
        enter(b, p);
    }

    //creates a building a it's 1st floor and adds it to the map
    //all addtional floors need to be made using one of the Building.addFloor() methods
    private Building addBuilding(String name){
        Building b = new Building(name);
        this.map.put(name, b);
        this.map.get(name).addFloor(1);
        return b;
    }


    public Game() {
        // create all the items (z)
        Item goatKey = new Item("key", "A heavy brass key", false); //goat
        Item cake = new Item("chocolate cake", "A delicious looking chocolate cake", true); //julia, hubbard
        Item waterGun = new Item("water gun", "A small neon green water pistol. It's empty.", true); //bugsy, parsons


        // create all the dialogue (m)
        // create the ghosts 
        // create the locations
        this.map = new Hashtable<>();

        Building seelye =  this.addBuilding("Seeyle");
        seelye.addFloor(2);
        seelye.setItem(2, new Item("key", "It's a key", false)); //comstock 2
        seelye.addFloor(3);
        seelye.addFloor(4);
        seelye.setGhost(5, new StartingGhost(new Item("OneCard", "This is your key to the campus", false)));
        this.player =  new Protagonist(seelye.getFloor(4));

        Building wburn = this.addBuilding("Washburn");
        wburn.setItem(1, new Item("key", "It's a key", false)); //northrop 2
        wburn.addFloor(2);
        wburn.setGhost(2, new Ghost("Madeline", 
            new Item("bowl of guacamole", "Looks tasty. Would pair well with chips", true), 
            "A girl in her early teens wearing a white dress. This strikes you as rather too on the nose for a ghost."));
        wburn.addFloor(3);

        Building mcl = this.addBuilding("McConnell");
        mcl.addFloor(2);
        mcl.addFloor(3);
        mcl.addFloor(4);

        Building park = this.addBuilding("Park");
        park.addFloor(2);
        park.addFloor(3);
        park.addFloor(4);

        Building sessions = this.addBuilding("Sessions");
        sessions.setGhost(1, new Ghost("John", 
            new Item("bowl of chips", "Mmm. Crunchy. Do you prefer salsa or guac?", true), 
            "He's wearing a soldier's uniform. You think he looks tired."));
        sessions.addFloor(2);
        sessions.addFloor(3);

        this.addBuilding("Mendenhall");
        this.addBuilding("Parsons");
        this.addBuilding("Northrop");
        this.addBuilding("Chapin");
        this.addBuilding("Tyler");
        this.addBuilding("Hubbard");
        this.addBuilding("Comstock");
        this.addBuilding("Outside");
        // initialize the protagonist (z)
        // create the graph of the locations
        // 

    }

    public void run() {
        //scannnerrrrrrr
        
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
