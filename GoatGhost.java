import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GoatGhost extends Ghost{
    /** what the GoatGhost needs to shut up */
    Item apple;
    
    /**
     * GoatGhost constructor
     * @param item Item the GoatGhost holds
     * @param apple what the GoatGhost needs to be quiet
     */
    public GoatGhost(Item item, Item apple) {
        super("Goat", item, "goat. This is a goat");
        this.apple = apple;
    }

    public class Audio{
        /** clip to play */
        Clip clip;

        /**
         * Readies the audio file goatSound.wav to be accesed through clip
         */
        public void setFile(){
            try {
                File f = new File("goatSound.wav");
                AudioInputStream sound = AudioSystem.getAudioInputStream(f);
                clip = AudioSystem.getClip();
                clip.open(sound);  
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * plays clip
         */
        public void play(){
            clip.setFramePosition(0);
            clip.start();
        }

        /**
         * stops playing clip
         */
        public void stop(){
            clip.stop();
            clip.flush();
        }
    }

    /**
     * Prints specified lines based on the player's input
     * @param p Protagonist to talk to
     * @param s Scanner to parse the player's input
     */
    public void talk(Protagonist p, Scanner s) {
        Audio audio = new Audio();
        audio.setFile();
        if(talkedTo == false){
            if (p.inventory.contains(this.apple)) {
                audio.stop();
                audio.play();
                System.out.println("Wow this goat sure is loud! Maybe an apple would quiet it down. Give the goat your apple? (type 'y' to say yes, 'n' to say no.)");
                while (s.hasNext()) {
                    if (s.nextLine().equals("y")) {
                        audio.stop();
                        this.give(p);
                        p.inventory.remove(this.apple);
                        this.talkedTo = true;
                        break;
                    } else {
                    System.out.println("It's almost like music. Loud, bad, never ending music.");
                    audio.stop();
                    audio.play();
                    }
                }
            } else {
                audio.play();
                System.out.println("Wow this goat sure is loud! Maybe you could find something to quiet it down. I hear goats love apples.");
            }
        } else {
            System.out.println("The goat is too busy eatting the apple to talk to you.");
        }
    }
}


