import com.google.common.graph.*;
public class Ghost extends Person {
    protected Item item;
    protected String description;
    protected Graph<String> dialouge; 
  
    /** Default constructor */
    public Ghost() {
        super();
        this.item = null;
        this.description = "<YOU CAN'T SEE GHOSTS>";
    }

    public Ghost(String name, Item item, String description){
        super(name);
        this.item = item;
        this.description = description;
    }

    //accessors
    public Item getItem(){
        return this.item;
    }

    //methods
    public void examine(){
        System.out.println(description);
    }

    public void give(Protagonist p){
        p.pickUp(this.item);
    }

    public void talk() {
        //smthn
    }

    public void setDialogue(String ghost, String response1, String response2, String ghost1, String ghost2, String response1_1, String response1_2, String response2_1, String response2_2, String getTrinket, String getFucked){
        ImmutableGraph<String> g = 
        GraphBuilder.directed()
            .<String>immutable()
            .putEdge(ghost, response1)
            .putEdge(response1, ghost1)
            .putEdge(ghost1, response1_1)
            .putEdge(response1_1, getTrinket)
            .putEdge(ghost1, response1_2)
            .putEdge(response1_2, getTrinket)
            .putEdge(ghost, response2)
            .putEdge(response2, ghost2)
            .putEdge(ghost2, response2_1)
            .putEdge(response2_1, getTrinket)
            .putEdge(ghost2, response2_2)
            .putEdge(response2_2, getFucked)
            .build();

        this.dialouge = g;
    }
}
