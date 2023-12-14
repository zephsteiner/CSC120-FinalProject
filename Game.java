import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class Game {
    /** the protagonist of this game */
    protected Protagonist p;

    /** collection of all buildings accessible in this game */
    protected Hashtable<String,Building> map;

    /** collection of all keys to a building in this game */
    protected Hashtable<Integer,Item> keys;

    /** the tutorial character of this game */
    protected StartingGhost lily;

    /** collection of valid inputs by the user */
    protected ArrayList<String> responses = new ArrayList<String>(
        Arrays.asList ("enter", "exit", "go down", "go up", "pick up", "check inventory", "examine", "talk", "talk to lily", "help", "lost", "y", "n", "1", "2")
    );

     /** collection of valid inputs by the user */
    protected ArrayList<String> commands = new ArrayList<String>(
        Arrays.asList ("enter", "exit", "go down", "go up", "pick up", "check inventory", "examine", "talk", "talk to lily", "help", "lost")
    );


    /**
     * Allows the user to 'enter' a building on the map if they are outside and the building is unlocked. 
     * Allows the user to 'exit' a building if they are on the ground floor
     * @param b Building to enter
     * @param p Protagonist to move
     */
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

    /**
     * Overloaded enter method that calls the String key associated with a building 
     * @param building String key of a Building
     * @param p Protagonist to move
     */
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

    /**
     * Prints all the valid commands a user can input
     */
    private void printResponses() {
        System.out.println("Valid commands:");
        for (String s : this.commands) {
            System.out.println(" - " + s);
        }
        System.out.println("If you feel like you're done, go back to the 4th floor of Seelye and 'talk to lily'");
    }

    /**
     * Prints all the valid locations a user can input
     */
    private void printMap() {
        System.out.println("Smith's buildings:");
        for (String s : Collections.list(this.map.keys())) {
            System.out.println(" - " + s);
        }
    }

    /**
     * Constructor for Game. Initializes everything the player can interact with
     */
    public Game() {
        this.map = new Hashtable<>();
        this.keys = new Hashtable<>();
        this.keys.put(1, new Item("key", "This key says 'Comstock' on it", false)); //comstock 2
        this.keys.put(2, new Item("key", "This key has a tag that says 'Northrop second floor'", false)); //northrop 2
        this.keys.put(3, new Item("key", "It says 'Sessions'", false)); //sessions 2
        this.keys.put(4, new Item("key", "This looks like a key to McConnell", false)); //mcconnell 3
        this.keys.put(5,  new Item("key", "This key has 'Hubbard' energy", false)); //hubbard 2
        this.keys.put(6, new Item("key", "Holding this key, you have a strange desire to go to the second floor of Tyler", false)); //tyler 2
        this.keys.put(7, new Item("key", "You think this is a key to Mendenhall. But what floor?", false)); //mhall 3

        this.lily = new StartingGhost();

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
        wburn.getFloor(2).getGhost().setDialogue("Boo!!",
            "Ahhh!!.. Haha got me there.", "AHHHH!! JIMINY MOTHER TRUCKING CRICKETS!",  
            "Oh good. I do love a good joke. Some people take it awfully seriously which just ruins the fun.", "Oh! Oh no! I didn’t mean to actually scare you. Oh, I’m so sorry.",
            "Speaking of fun, I’m here to invite you to a party tonight! It aught to be a great time!", "Now back to business, Lily is having a party tonight and you’re invited. Is there anything you have for me to bring?",
            "It’s okay…would you like to come to a party tonight? Lily’s hosting a party, I’m supposed to ask you if you have anything for me to bring.", "You should be more careful with that. You are a ghost you know.",
            "Oh yes of course! Here one of the students left this in the fridge.", "Oh I really am sorry. Please forgive me I didn’t mean it. I’ll leave you alone now.",
            "Boo! Haha get it. Cause I'm a ghost.", "No please I said I'd leave you alone.");
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
        northrop.getFloor(2).getGhost().setDialogue("What? What do want? Why are you bothering me?",
            "Hi! Lilly sent me to invite people to the party later. Do you have anything for me to bring?", "Oh uh sorry. I was supposed to invite you to lilly's party later. I can go.", 
            "There's going to be a party later? Nobody told me about any party.", "Party? Why in the world is she having a party?", 
            "Oh, well it's in Seeyle later today! It should be a lot of fun. Do you have anything you'd want me to bring?", "It's kind of last minute but I'm inviting all the ghosts I can find on campus. You should really come.", 
            "It's just for fun! You should come. Most people are bringing things but you don't have to.", "I don't know. Sorry, Ill go.", 
            "You know what, take this. I' might as well get out for once.", "Good riddance!", 
            "What, you're back again? I already said I'd go.", "What? You again? Stop bothering me.");
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
        sessions.getFloor(1).getGhost().setDialogue("Ah a visitor, hello.",
            "Hiya, I'm collecting stuff for the party tonight. Do you have anything you'd like me to bring?", "Hello! I've come to invite you to a party tonight.", 
            "Golly gee a party here, I haven't been to one of those in ages. I bet I can find something worth bringing.", "Well golly. It sure has been a long time since I've been to a party.", 
            "It's shaping up to be a real good one too. I'm trying to invite all the ghosts on campus, yourself included of course!", "I'm sure whatever you've got will be more then good enough.", 
            "That's alright, I'm sure you'll get right back in the swing of things and have a grand time.", "Well it looks like this is going to be a real fun one. Lots of people are invited.", 
            "Here, take these. Hopefully folks like them. They're a bit too plain for me.", "I don't do to well with lots of people. I think I'll just stay here with Lucy.", 
            "You know if you were to find  some dips we would be in for a real party. Ah well, see you later.", "Where has Lucy gone anyhow?");
        sessions.setDescription(1);
        sessions.addFloor(2);
        sessions.setKey(2, this.keys.get(3));
        sessions.setDescription(2);
        sessions.addFloor(3);
        sessions.setGhost(3, new Ghost("Lucy", 
            new Item("glitter", "This gets everywhere...", true), 
            "remarkably visually crisp spirit but her eyes are far away and her face is drawn"));
        sessions.getFloor(3).getGhost().setDialogue("Oh hello.",
            "Hi! How are you?", "Hi there! I've come to invite you to Lily's party later!", 
            "Oh uh. No ones asked me that in a long time. I'm doing okay actually.", "A party. Isn't that exciting.", 
            "Wonderful! If you want Lily is  having a party later and I came to invite you. ", "I'm happy to hear that. Would you like to come to a party later? I'm collecting things to bring if you have anything.", 
            "It's going to be lots of fun! Is there anything you'd like me to bring for you?", "Yes, all of the ghosts on campus are invited!", 
            "I'd love to come! Here have this, it'll be very festive.", "All of the ghosts. That sounds like a bit much for me.", 
            "Hello again. I do hope that glitter hasn't been making too much of a mess for you.", "Oh hello again. You have fun tonight.");
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
        mcl.getFloor(3).getGhost().setDialogue("Oh hello! You startled me. I was just looking at this chart here. Did you know there's a solar eclipse today?",
            "Woah a solar eclipse!", "I didn't know. But I do know there's going to be a ghost party. Do you have anything I could bring to Lily?", 
            "It's very exciting! There are two every year, but it's not often that you can actually see one from campus.", "Something for a party? Oh, I don't know. What do people do at parties?", 
            "You should come to Lily's ghost party later so everyone can watch it together.", "I can't wait! This is even more exciting then the party.", 
            "Well, if the eclipse and the party are at the same time people would probably be pretty interested in that!", "They do lots of things! I'm sure you've got something around here people would like.", 
            "Oh boy! why don't you take these.", "Oh I really don't know. I think I'd rather just watch the eclipse anyhow.", 
            "Make sure you don't lose those glasses. You'll need them to see the eclipse.", "Oh hello again, have fun at your party. I'll be here waiting for the eclipse.");
        mcl.setDescription(3);
        mcl.addFloor(4);
        mcl.setDescription(4);

        Building park = this.addBuilding("Park");
        park.setDescription(1);
        park.addFloor(2);
        park.setGhost(2, new Ghost("Jeanne", 
            new Item("a variety of teabags", "The guests will appreciate this; tea really elevates any event", true), 
            "young woman with a wavering voice and quick eyes"));
        park.getFloor(2).getGhost().setDialogue("I haven't seen you around here before. What are you doing?",
            "I don't come to Park much. I'm delivering invitations for Lily's party tonight.", "I was looking for you actually. Lilly sent me.", 
            "Yes of course! The party how could I forget?", "Oh, this is about that party she's plotting isn't it?", 
            "Ah great, you already know about it! Is there anything you wanted me to bring?", "That's why I came to remind you. Were you going to bring anything? I can take it for you.", 
            "It sure is! I came to invite you. Is there anything you'd like me bring for you?", "Plotting? I think it's going to be quite fun. You should come!", 
            "I could swear I had something around here... Ah, Yes! Here you go.", "No I can't. Big groups make me...jumpy.", 
            "I gave you that tea already right? Yes...Yes, I did!", "You're back? I'm still not going. To many eyes in one place.");    
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
        psons.getFloor(2).getGhost().setDialogue("Ay kid, whatta you doin here?",
            "I’m here to invite you to a party, later today in seeyle.", "Are you Bugsy Siegel?", 
            "What kind of party? There won’t be any funny business goin on will there?", "Now what kinda question is that? Am I Bugsy Siegel am I not Bugsy Siegel, what’s the difference to you?", 
            "No, no funny business that I know of.", "I mean you never know what’s going to happen at a party", 
            "I’m sorry, I just came to invite you to the party tonight. I hope you’ll come", "I just wanted to know, I didn’t mean to upset you.", 
            "Here kid, take this. You never know when it might come in handy.", "Well now you have. Go on get outta here.", 
            "You hold on to that now. And I’ll see ya at the party.", "I said split.");   
        psons.setDescription(2);
        psons.addFloor(3);
        psons.setDescription(3);

        Building mhall = this.addBuilding("Mendenhall");
        mhall.setGhost(1, new Ghost("Leah", 
            new Item("large jar", "A large mason jar filled with a mysterious clear liquid", true), 
            "woman of uncertain age. There is a light of what you think is mischief dancing in her eyes"));
        mhall.getFloor(1).getGhost().setDialogue( "Ah yes, hello!",
            "Hello! I'm here to invite you to Lily's party.", "Hi! Sorry were you expecting someone?", 
            "Oooo, I do love a good party. Lily's hosting you said?I know just what to bring.", "No, No, Are you the one who's telling people about the party?", 
            "Oh wonderful! I'm also bringing things over for everyone. Would you give it to me.", "Oh lovely! What is it?", 
            "Yes, I am! I've come to invite you and I can also bring anything over that you have.", "Yes! Were you planning on coming? It's shaking up to be quite fun.", 
            "I can't tell you what it is now, but be very very careful that it doesn't break. Could get extremely dangerous.", "I'm afraid I've got other plans. But, uh.... watch out for shenanigans if you're at the party. Ghost pranks can be a bit much for the living.", 
            "Really do be careful with that jar.", "Don't be too worried. I'm not planning anything more then the usual hijinks.");    
        mhall.setDescription(1);
        mhall.addFloor(2);
        mhall.setItem(2, new Item("apple", "Goats like these", false));
        mhall.setGhost(2, new GoatGhost(new Item("key", "This must be the key to the party", true), mhall.getFloor(2).getItem()));
        mhall.setDescription(2);
        mhall.addFloor(3);
        mhall.setKey(3, this.keys.get(7));
        mhall.setGhost(3, new Ghost("Pip", 
            new Item("tea things", "Matching teacups, saucers, and an elegant teapot", true), 
            "young boy and he's almost flying with his excitement. You better ask him what he's got for you, quickly"));
        mhall.getFloor(3).getGhost().setDialogue("Hello! Hello!! Hello!!!",
            "Hello! It looks like you have something for me?", "Why hello there!", 
            "I do! I do!", "Are you the party person?? I have something and I HAVE to give it to the party person!", 
            "Is it for the party? I'm supposed to be collecting party supplies you know.", "Well don't keep it a secret? What've you got?", 
            "That's me! What've you got?", "Well it's Lilly's party not mine, so she's the real party person. But I am collecting things.", 
            "It's a teapot!! Look! Look! AND teacups!!!", "Oh, well I'm only supposed to give it to the party person. So if that's not you I really better not.", 
            "Still have the teapot? And the teacups??? They're very important!", "You already said you're not the party person. I can't give it to you.");
        mhall.setDescription(3);

        Building chapin = this.addBuilding("Chapin");
        chapin.setGhost(1, 
            new Ghost("Vivi", new Item("party hats", "Party City branded party hats. Wait. The nearest Party City is in Chicopee. Where did this ghost get these?", true), 
            "young woman with a sharp, knowing face"));
        chapin.getFloor(1).getGhost().setDialogue("Ah hello, you're running a little behind schedule.",
            "Oh, I'm sorry. I suppose that means I should get right to business.", "Oh, I didn't know there was a schedule?", 
            "Yes exactly. It's good to finally see someone taking some initiative with this event.", "Didn't know there was a schedule!?!? This party is today. How could you possibly get all the preparation done without a schedule?", 
            "You're dear friend Lilly would like to cordially invite you to a ghostly party later today in Seeyle Hall. Is there anything I can bring there for you?",  "So, is there anything you'd like me to bring?", 
            "You're right. I should stay on task. Do you have anything for me to bring to the party? ",  "Well I've mostly just been wandering around I guess.", 
            "Take these and hurry along. I won't have this party starting late.", "Well my goodness. Well in that case you can mark my RSVP as a no. I will not associate with such a poorly organized event.", 
            "Quit your dilly dallying and get back to work. You've got a job to do.",  "Shouldn't you be wandering around?");    
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
        hubbard.getFloor(2).getGhost().setDialogue("Hello, I’m Julia Child. Welcome to the French Chef.",
            "Oh my goodness Julia Child!",  "Hello, sorry to interrupt but I don’t think we’re on tv right now. I’ve come to invite you to a party.", 
            "Today we’ll be making my famous chocolate cake with a fudge ganache.", "How marvelous, because today we’ll be making my famous chocolate cake.", 
            "That’ll be perfect for the party!", "I love chocolate cake!", 
            "That’ll be perfect for the party!", "How soon will it be ready?", 
            "Bon Appetite.", "Good things take time. If you don’t want to wait you should  leave.", 
            "Have you no patience at all.", "A party without cake is really just a meeting.");    
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
        tyler.getFloor(3).getGhost().setDialogue( "Sup dude! Did you see the game last night?",
            "Heck yeah I did! We totally crushed that other team.", "Oh, uh, no I didn't. I'm here to invite you to a party later today. Lily is throwing it.", 
            "Right! It was sick. Wait what are you doing agian?", "Woah, is it gonna be a rager?", 
            "Oh right! Do you have anything you want me to bring to the party tonight?", "Lily's throwing a party tonight and you're invited!", 
            "Yeah totally! I'm inviting all the ghosts!", "Well it's all ghosts, so it will probably be whatever you guys normally do.", 
            "Sick! Have this.", "Aw man! Ghost parties are so lame. I'm out.", 
            "I'll see you at the party. I hope it's a rager.", "Come back if you here about anything actually cool.");    
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
        comstock.getFloor(2).getGhost().setDialogue( "Who are you? Who said you could bother me?",
            "Lily sent me. I’m here to invite you to a ghost party tonight, and to ask if you had anything for me to bring.",  "Sorry, I just wanted to let you know Lily is having a party. You’re invited if you want.", 
            "That Lily, always thinks she can ask whatever she wants of everyone. Then again It might be nice to find a new haunt for the night.",  "People these days! They think they can just walk up to anyone and start making demands. So i’m invited to your little party, huh?", 
            "You don’t have to come. It’s totally up to you.", "Exactly, everyone needs a change of pace from time to time. Is there anything I can bring for you?", 
            "Well it’s not really my party. It’s for all the ghosts on campus. I’m just helping to deliver the invites and collect supplies.",  "I’m really sorry. I didn’t mean to offend you.", 
            "You can take this. But don’t think this means you can tell me what to do.", "Well it’s too late for that now isn’t it. Just get out of my house already.", 
            "You got what you came for, now get out of my house.",  "Go now! before I make you.");    
        comstock.setDescription(2);
        comstock.addFloor(3);
        comstock.setDescription(3);
        comstock.addFloor(4);
        comstock.setDescription(4);

        Building outside = this.addBuilding("Outside");
        outside.setDescription(1, "You're outside. Try to 'enter' a building.", true);
    }

    /**
     * Runs the tutorial and makes a loop to run the game until an end condition is reached
     */
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
        System.out.println("Lily: Great! Try going down to the second floor. I think there might be an item you'll need there. Say 'help' if you get stuck.");
        
        boolean play = true;
        while (play) {
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
                        this.p.getLocation().getGhost().talk(p, input); //add talk methods here
                    } catch (Exception e) {
                        System.out.println("There isn't anyone to talk to");
                    }
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
                    int winItems = 0;
                    for (Item i : this.p.inventory) {
                        if (i.getHelpWin()) {
                            winItems += 1;
                        }
                    }
                    boolean canWin = (winItems >= 8);
                    boolean isInSeelye = false;
                    if (this.p.getLocation().equals(this.map.get("Seelye").getFloor(4))) {
                        isInSeelye = true;
                    }
                    if (canWin && isInSeelye) {
                        System.out.println("You win!");
                        play = false;
                    } else {
                        if (isInSeelye) {
                            System.out.println("Lily: Ah, I need a few more items.");
                        } else {
                            System.out.println("Lily isn't here!");
                        }
                    }
                }
            } 
            else {
                System.out.println("Invalid command. Try a valid command instead");
            }
        }
        input.close();
    }
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.run();
    }
}