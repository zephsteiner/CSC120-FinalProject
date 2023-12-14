import java.util.Hashtable;
import java.util.Scanner;

public class Ghost extends Person {
    /** Item the ghost is holding */
    protected Item item;

    /** description of the Ghost */
    protected String description;

    /** stores the Ghost's dialogue options */
    protected Hashtable<Integer, String> dialouge;

    /** Has the Protagonist talked to this Ghost? */
    protected Boolean talkedTo = false;
  
    /** Default constructor */
    public Ghost() {
        super();
        this.item = null;
        this.description = "<YOU CAN'T SEE GHOSTS>";
    }

    /**
     * Ghost constructor
     * @param name name of Ghost
     * @param item Item the Ghost holds
     * @param description description of the Ghost
     */
    public Ghost(String name, Item item, String description){
        super(name);
        this.item = item;
        this.description = description;
    }

    /**
     * Gets the Item a Ghost holds
     * @return the Item a Ghost holds
     */
    public Item getItem(){
        return this.item;
    }

    /**
     * Gets a specified line of dialogue from the Ghost
     * @param n Integer key of the desired line
     * @return the desired line
     */
    public String getLine(Integer n){
        return this.dialouge.get(n);
    }

    /**
     * Gets the description of a Ghost
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Assign this Ghost's Item to the Protagonist's inventory
     * @param p Protagonist to assign
     */
    public void give(Protagonist p){
        p.pickUp(this.item);
        System.out.println("They hand you " + this.item.getName() + ". " + this.item.getDescription());
    }

    /**
     * Accesses the Hashtable storing the dialogue to print specified lines based on the player's input
     * @param p Protagonist to talk to
     * @param s Scanner to get the player's input
     */
    public void talk(Protagonist p, Scanner s){
        if(this.talkedTo == true){
            if(p.inventory.contains(this.item)){
                System.out.println(this.name + ": " + this.getLine(11));
            } else{
                System.out.println(this.name + ": " + this.getLine(12));
            }
        } else{
            this.talkA();
            Integer choiceA = s.nextInt();
            Boolean checkA = choiceA == 1 || choiceA == 2;
            if(checkA){
                this.talkB(choiceA);
            } else {
                while(!checkA){
                    System.out.println("Please respond 1 to choose the first option, or 2 to choose the second.");
                    choiceA = s.nextInt();
                    s.nextLine();
                    checkA = choiceA == 1 || choiceA == 2;
                }
            }
            Integer choiceB = choiceA + s.nextInt();
            s.nextLine();
            Boolean checkB = choiceB == 2 || choiceB == 3 || choiceB == 4;
            if(checkB){
                this.talkC(choiceB, p);
            } else {
                while(!checkB){
                    System.out.println("Please respond 1 to choose the first option, or 2 to choose the second.");
                    choiceB = choiceA + s.nextInt();
                    checkB = choiceB == 2 || choiceB == 3 || choiceB == 4;
                }
            }
        }
    }
   
    /**
     * Acesses and prints the first section of dialouge from the hashtable
     */
    private void talkA() {
        System.out.println(this.name + ": " + this.getLine(0));
        System.out.println("What do you say?");
        System.out.println("1: "+this.getLine(1));
        System.out.println("2: "+this.getLine(2));
        System.out.println("Please respond 1 to choose the first option, or 2 to choose the second.");
    }

    /**
     * Acesses and prints the middle section of dialouge from the hashtable based on the users chosen response from the first section
     * @param i numerical represetation of the users chosen reponse from the first section of dialouge
     */
    private void talkB(Integer i){
        System.out.println(this.name + ": " + this.getLine(i+2));
        System.out.println("What do you say?");
        System.out.println("1: "+this.getLine(i+4));
        System.out.println("2: "+this.getLine(i+6));
        System.out.println("Please respond 1 to choose the first option, or 2 to choose the second.");
    }

    /**
     * Acesses and prints the final section of dialouge from the hashtable based on the users chosen response from the pervious sections
     * @param i numerical represetation of the users chosen reponse from the previous sections of dialouge
     * @param p Protagonist to speak to
     */
    private void talkC(Integer i, Protagonist p){
        if(i == 2 || i == 3){
            System.out.println(this.name + ": " + this.getLine(9));
            this.give(p);
        } else if(i == 4){
            System.out.println(this.name + ": " + this.getLine(10));
        }
        this.talkedTo = true;
    }

    /**
     * Sets the dialogue of this Ghost
     * @param g0
     * @param r1
     * @param r2
     * @param g1
     * @param g2
     * @param r1_1
     * @param r1_2
     * @param r2_1
     * @param r2_2
     * @param trinket
     * @param lose
     * @param end1
     * @param end2
     */
    public void setDialogue(String g0, String r1, String r2, String g1, String g2, String r1_1, String r1_2, String r2_1, String r2_2, String trinket, String lose, String end1, String end2){
        this.dialouge = new Hashtable<>(13);
        this.dialouge.put(0, g0);
        this.dialouge.put(1, r1);
        this.dialouge.put(2, r2);
        this.dialouge.put(3, g1);
        this.dialouge.put(4, g2);
        this.dialouge.put(5, r1_1);
        this.dialouge.put(6, r2_1);
        this.dialouge.put(7, r1_2);
        this.dialouge.put(8, r2_2);
        this.dialouge.put(9, trinket);
        this.dialouge.put(10, lose);
        this.dialouge.put(11, end1);
        this.dialouge.put(12, end2);
    }
}
