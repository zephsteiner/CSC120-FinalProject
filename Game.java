import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class Game {
    protected Protagonist p;
    protected Hashtable<String,Building> map;
    protected Hashtable<Integer,Item> keys;
    protected StartingGhost lily;
    protected ArrayList<String> responses = new ArrayList<String>(
        Arrays.asList ("enter", "exit", "go down", "go up", "pick up", "check inventory", "examine", "talk", "talk to lily", "help", "lost")
    );


    public void enter(Building b, Protagonist p){
        boolean unlocked = (!b.getFloor(1).hasKey() || p.inventory.contains(b.getFloor(1).getKey()));
        boolean ground = p.loc.getFloorNum() == 1;
        if(unlocked && ground) {
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

    private void printResponses() {
        System.out.println("Valid commands:");
        for (String s : this.responses) {
            System.out.println(" - " + s);
        }
        System.out.println("If you feel like you're done, go back to the 4th floor of Seelye and 'talk to lily'");
    }

    private void printMap() {
        System.out.println("Smith's buildings:");
        for (String s : Collections.list(this.map.keys())) {
            System.out.println(" - " + s);
        }
    }

    public Game() {
        // create all the dialogue (m)
        this.map = new Hashtable<>();
        this.keys = new Hashtable<>();
        this.keys.put(1, new Item("key", "This key says 'Comstock' on it", false)); //comstock 2
        this.keys.put(2, new Item("key", "This key has a tag that says 'Northrop second floor'", false)); //northrop 2
        this.keys.put(3, new Item("key", "It says 'Sessions'", false)); //sessions 2
        this.keys.put(4, new Item("key", "This looks like a key to McConnell", false)); //mcconnell 3
        this.keys.put(5,  new Item("key", "This key has 'Hubbard' energy", false)); //hubbard 2
        this.keys.put(6, new Item("key", "Holding this key, you have a strange desire to go to the second floor of Tyler", false)); //tyler 2
        this.keys.put(7, new Item("key", "You think this is a key to Mendenhall. But what floor?", false)); //mhall 3

        this.lily = new StartingGhost(new Item("OneCard", "This is your key to the campus", false));

        Building seelye =  this.addBuilding("Seelye");
        seelye.setDescription(1);
        seelye.addFloor(2);
        seelye.setItem(2, this.keys.get(1));
        seelye.setDescription(2);
        seelye.addFloor(3);
        seelye.setDescription(3);
        seelye.addFloor(4);
        seelye.setGhost(4, this.lily);
        seelye.setDescription(4, "You're on floor 4 of Seelye. This is where Lily lives.", true);
        this.p =  new Protagonist(seelye.getFloor(4));

        Building wburn = this.addBuilding("Washburn");
        wburn.setItem(1, this.keys.get(2));
        wburn.setDescription(1);
        wburn.addFloor(2);
        wburn.setGhost(2, new Ghost("Madeline", 
            new Item("bowl of guacamole", "Looks tasty. Would pair well with chips", true), 
            "girl in her early teens wearing a white dress. This strikes you as rather too on the nose for a ghost."));
        wburn.setDescription(2);
        wburn.addFloor(3);
        wburn.setDescription(3);

        Building northrop = this.addBuilding("Northrop");
        northrop.setItem(1, this.keys.get(3));
        northrop.setDescription(1);
        northrop.addFloor(2);
        northrop.setKey(2, this.keys.get(2));
        northrop.setGhost(2, new Ghost("Francine", 
            new Item("pack of Camel brand cigarettes", "There are a few missing", true), 
            "cloudy specter. You can barely make out her form. She creaks when she moves"));
        northrop.setDescription(2);
        northrop.addFloor(3);
        northrop.setDescription(3);
        northrop.addFloor(4);
        northrop.setDescription(4);
        northrop.addFloor(5);
        northrop.setItem(5, this.keys.get(5));
        northrop.setDescription(5);

        Building sessions = this.addBuilding("Sessions");
        sessions.setGhost(1, new Ghost("John", 
            new Item("bowl of chips", "Mmm. Crunchy. Do you prefer salsa or guac?", true), 
            "man of middling height and uncertain age. He's wearing a soldier's uniform. Upon further inspection, you think he's a young man who looks old beyond his years."));
        sessions.setDescription(1);
        sessions.addFloor(2);
        sessions.setKey(2, this.keys.get(3));
        sessions.setDescription(2);
        sessions.addFloor(3);
        sessions.setGhost(3, new Ghost("Lucy", 
            new Item("glitter", "This gets everywhere...", true), 
            "remarkably visually crisp spirit but her eyes are far away and her face is drawn"));
        sessions.setItem(3, this.keys.get(4)); 
        sessions.setDescription(3);

        Building mcl = this.addBuilding("McConnell");
        mcl.setDescription(1);
        mcl.addFloor(2);
        mcl.setItem(2, new Item(
            "living squirrel (regular)", "What it says on the tin. This is a normal, alive squirrel", false, false));
        mcl.setDescription(2);
        mcl.addFloor(3);
        mcl.setKey(3, this.keys.get(4));
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

        Building psons = this.addBuilding("Parsons");
        psons.setItem(1, this.keys.get(7)); //mhall 3
        psons.setDescription(1);
        psons.addFloor(2);
        psons.setGhost(2, new Ghost("Bugsy", 
            new Item("water gun", "A small neon green water pistol. It's empty.", true), 
            "tough looking man in a three-piece suit and a hat"));
        psons.setDescription(2);
        psons.addFloor(3);
        psons.setDescription(3);

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
        mhall.setKey(3, this.keys.get(7));
        mhall.setGhost(3, new Ghost("Pip", 
            new Item("tea things", "Matching teacups, saucers, and an elegant teapot", true), 
            "young boy and he's almost flying with his excitement. You better ask him what he's got for you, quickly"));
        mhall.setDescription(3);

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

        Building hubbard = this.addBuilding("Hubbard");
        hubbard.setDescription(1);
        hubbard.addFloor(2);
        hubbard.setKey(2, this.keys.get(5));
        hubbard.setGhost(2, new Ghost("Julia", 
            new Item("chocolate cake", "A delicious looking chocolate cake", true), 
            "middle-aged woman. Is that... Julia Child?"));
        hubbard.setDescription(2);
        hubbard.addFloor(3);
        hubbard.setItem(3, this.keys.get(6)); //tyler 2
        hubbard.setDescription(3);
        hubbard.addFloor(4);
        hubbard.setItem(4, new Item("living squirrel (abnormal)", 
            "You think it might be wise to avoid this squirrel", false, false));
        hubbard.setDescription(4);

        Building tyler = this.addBuilding("Tyler");
        tyler.setDescription(1);
        tyler.addFloor(2);
        tyler.setKey(2, this.keys.get(6));
        tyler.setDescription(2);
        tyler.addFloor(3);
        tyler.setGhost(3, 
            new Ghost("Gemma", new Item("salsa", "Would pair well with chips", true), 
            "strong, sturdy young woman. She's tall and quite pretty"));
        tyler.setDescription(3);
        tyler.addFloor(4);
        tyler.setDescription(4);

        Building comstock = this.addBuilding("Comstock");
        comstock.setDescription(1);
        comstock.addFloor(2);
        comstock.setKey(2, this.keys.get(1));
        comstock.setGhost(2, 
            new Ghost("Gloria", new Item("candle", "It's not lit", true), 
            "old woman. She looks mad. You should probably talk to her sooner rather than later and get out of her way"));
        comstock.setDescription(2);
        comstock.addFloor(3);
        comstock.setDescription(3);
        comstock.addFloor(4);
        comstock.setDescription(4);

        Building outside = this.addBuilding("Outside");
        outside.setDescription(1, "You're outside. Try to 'enter' a building.", true);
    }

    public void move(Scanner input) {
        String next = input.nextLine();
        boolean found = false;
        for (int r = 0; r < this.responses.size(); r++) {
            if (this.responses.get(r).equals(next)) {
                found = true;
            }
        }
        if (found) {
            if (next.equals("go down")) {
                try {
                    this.p.loc.goDown(p);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("go up")) {
                try {
                    this.p.loc.goUp(p);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("enter")) {
                this.printMap();
                System.out.println("What building?");
                String build = input.nextLine();
                if (this.map.containsKey(build)) {
                    try {
                    enter(this.map.get(build), this.p);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("Not a valid building. Try to enter again");
                }
            }
            if (next.equals("exit")) {
                try {
                    if (this.p.getLocation().equals(this.map.get("Outside").getFloor(1))) {
                        System.out.println("Nothing to exit. You're already outside.");
                    } else {
                        enter("Outside", this.p);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("pick up")) {
                try {
                    System.out.println("Added " + this.p.pickUp(this.p.getLocation().getItem()) + " to inventory");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("check inventory")) {
                try {
                    this.p.checkInventory();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("examine")) {
                this.p.checkInventory();
                System.out.println("What would you like to examine?");
                String item = input.nextLine();
                boolean itemFound = false;
                Item toExamine = null;
                for (int i = 0; i < this.p.inventory.size(); i++) {
                    toExamine = this.p.inventory.get(i);
                    if (toExamine.getName().equals(item)) {
                        itemFound = true;
                    }
                }
                if (itemFound) {
                    System.out.println(toExamine.getDescription());
                } else {
                    System.out.println("Not a valid item. Try to examine again");
                }
            }
            if (next.equals("talk")) {
                try {
                    this.p.getLocation().getGhost(); //add talk methods here
                } catch (Exception e) {
                    System.out.println("There isn't anyone to talk to");
                }
                System.out.println("talk");
            }
            if (next.equals("help")) {
                this.printResponses();
            }
            if (next.equals("lost")) {
                try {
                    this.p.getLocation().printDescription();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (next.equals("talk to lily")) {
                System.out.println("talk 2 lily");
                //game end goes here
            }
        } else {
            System.out.println("Invalid command. Try a valid command instead");;
        }

    }

    public void run() {
        System.out.println("Welcome to Smith!");
        System.out.println("You see " + lily.getDescription() + " drifting towards you. When she speaks, it's soft.");
        System.out.println("Lily: Hey there. I'm planning a ghost party for all the ghosts at Smith. Will you help me? (type 'y' to say yes, 'n' to say no.)");

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            if (input.next().equals("y")) {
                break;
            }
            else {
                System.out.println("Lily: Oh. Bye then.");
                System.exit(0);
            }
        }
        input.nextLine();
        System.out.println("AKJFHSDKFH");
        System.out.println("Great [here's how you play the game lol, go to the second floor]");
        
        boolean play = true;
        while (play) {
            this.move(input);
        }

        input.close();
    }
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.run();
    }
}