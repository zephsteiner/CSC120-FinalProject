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
    private void addBuilding(String name){
        this.map.put(name, new Building(name));
        this.map.get(name).addFloor(1);
    }

    private void addBuilding(String name, String bDescription, String gfDescription, Item gfKey, Ghost gfGhost){
        this.map.put(name, new Building(name, bDescription));
        this.map.get(name).addFloor(gfDescription, gfKey, gfGhost, 1);
    }

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
        this.addBuilding("Seeyle");
        this.map.get("Seeyle").addFloor(2);
        this.map.get("Seeyle").addFloor(3);
        this.map.get("Seeyle").addFloor(4); //lily is here
        this.addBuilding("Washburn");
        this.addBuilding("McConnell");
        this.addBuilding("Park");
        this.addBuilding("Sessions");
        this.addBuilding("Mendenhall");
        this.addBuilding("Parsons");
        this.addBuilding("Northrop");
        this.addBuilding("Chapin");
        this.addBuilding("Tyler");
        this.addBuilding("Hubbard");
        this.addBuilding("Comstock");
        this.addBuilding("Outside");

        Protagonist player = new Protagonist(this.map.get("Seeyle").getFloor(4));
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
