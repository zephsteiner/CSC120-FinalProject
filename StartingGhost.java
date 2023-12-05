import java.util.ArrayList;
import java.util.List;

public class StartingGhost extends Ghost {
    ArrayList<String> hints = new ArrayList<>(
        List.of("hint1", "hint2")
    );

    public StartingGhost() {
        super("Lily", null, new Item("Your OneCard", "This is your key to the campus", false), "this is a starting ghost");
        //set these values
    }
}
