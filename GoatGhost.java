import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GoatGhost extends Ghost{
    Item apple;
    
    public GoatGhost(Item item, Item apple) {
        super("This is a goat", item, "This is a goat");
        this.apple = apple;
    }

    public class Audio{
        Clip clip;

        public void setFile(){
            try {
                File f = new File("goatSound.mp3");
                AudioInputStream sound = AudioSystem.getAudioInputStream(f);
                clip = AudioSystem.getClip();
                clip.open(sound);  
            } catch (Exception e) {

            }
        }

        public void play(){
            clip.setFramePosition(0);
            clip.start();
        }

        public void stop(){
            clip.setFramePosition(clip.getFrameLength());
        }
    }

    public void talk(Protagonist p, Scanner s) {
        Audio audio = new Audio();
        audio.setFile();
        if (p.inventory.contains(this.apple)) {
            audio.play();
            System.out.println("Wow this goat sure is loud! Maybe an apple would quiet it down. Give the goat your apple? (type 'y' to say yes, 'n' to say no.)");
            while (s.hasNext()) {
                if (s.next().equals("y")) {
                    audio.stop();
                    this.give(p);
                    p.inventory.remove(this.apple);
                    break;
                }
                else {
                    System.out.println("It's almost like music. Loud, bad, never ending music.");
                    audio.play();
                }
            }
        } else {
            audio.play();
            System.out.println("Wow this goat sure is loud! Maybe you could find something to quiet it down. I hear goats love apples.");
        }
    }
}


