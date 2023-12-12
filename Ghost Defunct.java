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

    public String getCurrentNode(){
        ///aaahhh
    }

    //methods
    public void examine(){
        System.out.println(this.description);
    }

    public void give(Protagonist p){
        p.pickUp(this.item);
    }

    public void talk() {
        Graph<String> g = this.dialouge;
        String n =; //g0
        Boolean end = g.outDegree(n) == 0;
        while(!end){
            if(g.outDegree(n) == 1){
                n = g.successors(n);
                System.out.println(n);
            } else if(g.outDegree(n)>1){
                //print a numbered list of all succesors
                //ask for a choice -> seprate scanner?
                n = choice;
            }
        }
    }

    public void setDialogue(String g0, String r1, String r2, String g1, String g2, String r1_1, String r1_2, String r2_1, String r2_2, String trinket, String lose){
        ImmutableGraph<String> g = 
        GraphBuilder.directed()
            .<String>immutable()
            .putEdge(g0, r1)
            .putEdge(r1, g1)
            .putEdge(g1, r1_1)
            .putEdge(r1_1, trinket)
            .putEdge(g1, r1_2)
            .putEdge(r1_2, trinket)
            .putEdge(g0, r2)
            .putEdge(r2, g2)
            .putEdge(g2, r2_1)
            .putEdge(r2_1, trinket)
            .putEdge(g2, r2_2)
            .putEdge(r2_2, lose)
            .build();

        this.dialouge = g;
    }
}
