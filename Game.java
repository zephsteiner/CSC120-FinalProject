import java.util.Hashtable;

//import com.google.common.graph.*;

public class Game {
    protected Protagonist p;
    protected Hashtable<String,Building> map;



    public void enter(Building b, Protagonist p){
        boolean unlocked = b.getFloor(1).key == null || p.inventory.contains(b.getFloor(1).key);
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
    /**
     * Creates a Building object with one floor and adds it to the game's map.
     * Add more floors with Building.addFloor(...)
     * @param name The name of the Building to be added
     * @return The Building added
     */
    private Building addBuilding(String name){
        Building b = new Building(name);
        this.map.put(name, b);
        this.map.get(name).addFloor(1);
        return b;
    }


    public Game() {
        // create all the items (z)
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
        this.p =  new Protagonist(seelye.getFloor(4));

        Building wburn = this.addBuilding("Washburn");
        wburn.setItem(1, new Item("key", "It's a key", false)); //northrop 2
        wburn.addFloor(2);
        wburn.setGhost(2, new Ghost("Madeline", 
            new Item("a bowl of guacamole", "Looks tasty. Would pair well with chips", true), 
            "A girl in her early teens wearing a white dress. This strikes you as rather too on the nose for a ghost."));
        wburn.addFloor(3);

        Building mcl = this.addBuilding("McConnell");
        mcl.addFloor(2);
        mcl.setItem(2, new Item(
            "living squirrel (regular)", "What it says on the tin. This is a normal, alive squirrel", false, false));
        mcl.addFloor(3);
        mcl.setKey(3, this.map.get("Sessions").getFloor(3).getItem());
        mcl.setGhost(3, new Ghost("Bruno", 
            new Item("eclipse glasses", "It is important to stay safe when viewing the sun", true), 
            "THE it-girl of a mad scientist"));
        mcl.addFloor(4);

        Building park = this.addBuilding("Park");
        park.addFloor(2);
        park.setGhost(2, new Ghost("Jeanne", 
            new Item("a variety of teabags", "The guests will appreciate this; tea really elevates any event", true), 
            "A young woman with a wavering voice and quick eyes"));
        park.addFloor(3);
        park.setItem(3, new Item("small jar", "a small mason jar filled with a mysterious clear liquid", false));
        park.addFloor(4);

        Building sessions = this.addBuilding("Sessions");
        sessions.setGhost(1, new Ghost("John", 
            new Item("a bowl of chips", "Mmm. Crunchy. Do you prefer salsa or guac?", true), 
            "He's wearing a soldier's uniform. You think he looks tired."));
        sessions.addFloor(2);
        sessions.setKey(2, this.map.get("Northrop").getFloor(1).getItem());
        sessions.addFloor(3);
        sessions.setGhost(3, new Ghost("Lucy", 
            new Item("glitter", "This gets everywhere...", true), 
            "Her eyes are far away and her face is drawn"));
        sessions.setItem(3, new Item("key", "It's a key", false)); //mcconnell 3

        Building mhall = this.addBuilding("Mendenhall");
        mhall.addFloor(2);
        mhall.setItem(2, new Item("apple", "Goats like these", false));
        mhall.setGhost(2, new GoatGhost(new Item("key", "This must be the key to the party", true)));
        mhall.addFloor(3);
        mhall.setKey(3, this.map.get("Parsons").getFloor(1).getItem());

        Building psons = this.addBuilding("Parsons");
        psons.setItem(1, new Item("key", "It's a key", false)); //mhall 3
        psons.addFloor(2);
        psons.setGhost(2, new Ghost("Bugsy", 
            new Item("a water gun", "A small neon green water pistol. It's empty.", true), 
            "A tough looking man in a three-piece suit and a hat"));
        psons.addFloor(3);

        Building northrop = this.addBuilding("Northrop");
        northrop.setItem(1, new Item("key", "It's a key", false)); //sessions 2
        northrop.addFloor(2);
        northrop.setKey(2, this.map.get("Washburn").getFloor(1).getItem());
        northrop.setGhost(2, new Ghost("Francine", 
            new Item("a pack of Camel brand cigarettes", "There are a few missing", true), 
            "You can barely make out her form. She creaks when she moves"));
        northrop.addFloor(3);
        northrop.addFloor(4);
        northrop.addFloor(5);
        northrop.setItem(5, new Item("key", "It's a key", false)); //hubbard 2

        Building chapin = this.addBuilding("Chapin");
        chapin.addFloor(2);
        chapin.addFloor(3);
        chapin.addFloor(4);

        Building tyler = this.addBuilding("Tyler");
        tyler.addFloor(2);
        tyler.setKey(2, this.map.get("Hubbard").getFloor(3).getItem());
        tyler.addFloor(3);
        tyler.addFloor(4);

        Building hubbard = this.addBuilding("Hubbard");
        hubbard.addFloor(2);
        hubbard.setKey(2, this.map.get("Northrop").getFloor(5).getItem());
        hubbard.setGhost(2, new Ghost("Julia", 
            new Item("a chocolate cake", "A delicious looking chocolate cake", true), 
            "Is that... Julia Child?"));
        hubbard.addFloor(3);
        hubbard.setItem(3, new Item("key", "It's a key", false)); //tyler 2
        hubbard.addFloor(4);
        hubbard.setItem(4, new Item("living squirrel (abnormal)", 
            "You think it might be wise to avoid this squirrel", false, false));


        Building comstock = this.addBuilding("Comstock");
        comstock.addFloor(2);
        comstock.setKey(2, this.map.get("Seelye").getFloor(2).getItem());
        comstock.addFloor(3);
        comstock.addFloor(4);

        this.addBuilding("Outside");

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

//notes: should we label the keys lol
//i am loving the fact that the large slime jar is, in fact, useable at the party whereas small is not