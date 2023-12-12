import java.util.ArrayList;
import java.util.List;

public class StartingGhost extends Ghost {
    ArrayList<String> hints = new ArrayList<>(
        List.of("hint1", "hint2")
    );

    public StartingGhost(Item item) {
        super("Lily", item, "a spectral woman in her mid-thirties");
        //set these values
    }
}
