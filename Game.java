import java.util.Hashtable;

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
        // create all the dialogue (m)
        this.map = new Hashtable<>();

        Building seelye =  this.addBuilding("Seeyle");
        seelye.setDescription(1);
        seelye.addFloor(2);
        seelye.setItem(2, new Item("key", "This key says 'Comstock' on it", false)); //comstock 2
        seelye.setDescription(2);
        seelye.addFloor(3);
        seelye.setDescription(3);
        seelye.addFloor(4);
        seelye.setGhost(4, new StartingGhost(new Item("OneCard", "This is your key to the campus", false)));
        seelye.setDescription(4);
        this.p =  new Protagonist(seelye.getFloor(4));

        Building wburn = this.addBuilding("Washburn");
        wburn.setItem(1, new Item("key", "This key has a tag that says 'Northrop second floor'", false)); //northrop 2
        wburn.setDescription(1);
        wburn.addFloor(2);
        wburn.setGhost(2, new Ghost("Madeline", 
            new Item("bowl of guacamole", "Looks tasty. Would pair well with chips", true), 
            "girl in her early teens wearing a white dress. This strikes you as rather too on the nose for a ghost."));
        wburn.setDescription(2);
        wburn.addFloor(3);
        wburn.setDescription(3);

        Building mcl = this.addBuilding("McConnell");
        mcl.setDescription(1);
        mcl.addFloor(2);
        mcl.setItem(2, new Item(
            "living squirrel (regular)", "What it says on the tin. This is a normal, alive squirrel", false, false));
        mcl.setDescription(2);
        mcl.addFloor(3);
        mcl.setKey(3, this.map.get("Sessions").getFloor(3).getItem());
        mcl.setGhost(3, new Ghost("Bruno", 
            new Item("eclipse glasses", "It is important to stay safe when viewing the sun", true), 
            "tall, wild-looking specter. He is THE it-girl of a mad scientist"));
        mcl.setDescription(3);
        mcl.addFloor(4);
        mcl.setDescription(4);

        Building park = this.addBuilding("Park");
        park.setDescription(1);
        park.addFloor(2);
        park.setGhost(2, new Ghost("Jeanne", 
            new Item("a variety of teabags", "The guests will appreciate this; tea really elevates any event", true), 
            "young woman with a wavering voice and quick eyes"));
        park.setDescription(2);
        park.addFloor(3);
        park.setItem(3, new Item("small jar", "a small mason jar filled with a mysterious clear liquid", false));
        park.setDescription(3);
        park.addFloor(4);
        park.setDescription(4);

        Building sessions = this.addBuilding("Sessions");
        sessions.setGhost(1, new Ghost("John", 
            new Item("bowl of chips", "Mmm. Crunchy. Do you prefer salsa or guac?", true), 
            "man of middling height and uncertain age. He's wearing a soldier's uniform. Upon further inspection, you think he's a young man who looks old beyond his years."));
        sessions.setDescription(1);
        sessions.addFloor(2);
        sessions.setKey(2, this.map.get("Northrop").getFloor(1).getItem());
        sessions.setDescription(2);
        sessions.addFloor(3);
        sessions.setGhost(3, new Ghost("Lucy", 
            new Item("glitter", "This gets everywhere...", true), 
            "remarkably visually crisp spirit but her eyes are far away and her face is drawn"));
        sessions.setItem(3, new Item("key", "This looks like a key to McConnell", false)); //mcconnell 3
        sessions.setDescription(3);

        Building mhall = this.addBuilding("Mendenhall");
        mhall.setGhost(1, new Ghost("Leah", 
            new Item("large jar", "A large mason jar filled with a mysterious clear liquid", true), 
            "woman of uncertain age. There is a light of what you think is mischief dancing in her eyes"));
        mhall.setDescription(1);
        mhall.addFloor(2);
        mhall.setItem(2, new Item("apple", "Goats like these", false));
        mhall.setGhost(2, new GoatGhost(new Item("key", "This must be the key to the party", true)));
        mhall.setDescription(2);
        mhall.addFloor(3);
        mhall.setKey(3, this.map.get("Parsons").getFloor(1).getItem());
        mhall.setGhost(3, new Ghost("Pip", 
            new Item("tea things", "Matching teacups, saucers, and an elegant teapot", true), 
            "young boy and he's almost flying with his excitement. You better ask him what he's got for you, quickly"));
        mhall.setDescription(3);

        Building psons = this.addBuilding("Parsons");
        psons.setItem(1, new Item("key", "You think this is a key to Mendenhall. But what floor?", false)); //mhall 3
        psons.setDescription(1);
        psons.addFloor(2);
        psons.setGhost(2, new Ghost("Bugsy", 
            new Item("water gun", "A small neon green water pistol. It's empty.", true), 
            "tough looking man in a three-piece suit and a hat"));
        psons.setDescription(2);
        psons.addFloor(3);
        psons.setDescription(3);

        Building northrop = this.addBuilding("Northrop");
        northrop.setItem(1, new Item("key", "It says 'Sessions'", false)); //sessions 2
        northrop.setDescription(1);
        northrop.addFloor(2);
        northrop.setKey(2, this.map.get("Washburn").getFloor(1).getItem());
        northrop.setGhost(2, new Ghost("Francine", 
            new Item("pack of Camel brand cigarettes", "There are a few missing", true), 
            "cloudy specter. You can barely make out her form. She creaks when she moves"));
        northrop.setDescription(2);
        northrop.addFloor(3);
        northrop.setDescription(3);
        northrop.addFloor(4);
        northrop.setDescription(4);
        northrop.addFloor(5);
        northrop.setItem(5, new Item("key", "This key has 'Hubbard' energy", false)); //hubbard 2
        northrop.setDescription(5);

        Building chapin = this.addBuilding("Chapin");
        chapin.setGhost(1, 
            new Ghost("Vivi", new Item("party hats", "Party City branded party hats. Wait. The nearest Party City is in Chicopee. Where did this ghost get these?", true), 
            "young woman with a sharp, knowing face"));
        chapin.setDescription(1);
        chapin.addFloor(2);
        chapin.setDescription(2);
        chapin.addFloor(3);
        chapin.setDescription(3);
        chapin.addFloor(4);
        chapin.setDescription(4);

        Building tyler = this.addBuilding("Tyler");
        tyler.setDescription(1);
        tyler.addFloor(2);
        tyler.setKey(2, this.map.get("Hubbard").getFloor(3).getItem());
        tyler.setDescription(2);
        tyler.addFloor(3);
        tyler.setGhost(3, 
            new Ghost("Gemma", new Item("salsa", "Would pair well with chips", true), 
            "strong, sturdy young woman. She's tall and quite pretty"));
        tyler.setDescription(3);
        tyler.addFloor(4);
        tyler.setDescription(4);

        Building hubbard = this.addBuilding("Hubbard");
        hubbard.setDescription(1);
        hubbard.addFloor(2);
        hubbard.setKey(2, this.map.get("Northrop").getFloor(5).getItem());
        hubbard.setGhost(2, new Ghost("Julia", 
            new Item("chocolate cake", "A delicious looking chocolate cake", true), 
            "middle-aged woman. Is that... Julia Child?"));
        hubbard.setDescription(2);
        hubbard.addFloor(3);
        hubbard.setItem(3, new Item("key", 
            "Holding this key, you have a strange desire to go to the second floor of Tyler", false)); //tyler 2
        hubbard.setDescription(3);
        hubbard.addFloor(4);
        hubbard.setItem(4, new Item("living squirrel (abnormal)", 
            "You think it might be wise to avoid this squirrel", false, false));
        hubbard.setDescription(4);


        Building comstock = this.addBuilding("Comstock");
        comstock.setDescription(1);
        comstock.addFloor(2);
        comstock.setKey(2, this.map.get("Seelye").getFloor(2).getItem());
        comstock.setGhost(2, 
            new Ghost("Gloria", new Item("candle", "It's not lit", true), 
            "old woman. She looks mad. You shoudl probably talk to her sooner rather than later and get out of her way"));
        comstock.setDescription(2);
        comstock.addFloor(3);
        comstock.setDescription(3);
        comstock.addFloor(4);
        comstock.setDescription(4);

        this.addBuilding("Outside");

    }

    public void run() {
        System.out.println("Welcome to Smith!");
        System.out.println(this.map.get("Seeyle").getFloor(4).getDescription());



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
        Game myGame = new Game();
        myGame.run();
    }
}