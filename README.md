# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

Our overall approach was to design the classes and write basic methods for them first and then slowly bring everything together, writing the flavortext and the actual game loop last. To make this work, we made use of overloaded constructors so we could add things like ghost descriptions after building everything.

(Zephyr) The new things I learned on this project were how to visualize the way all of our code connected. Nothing we did was too different from what we've been doing all semester but completely buildign a game from scratch like this really helped my comprehension of how everything needed to work together. If we had unlimited time, I would have loved to have made our game map more complicated, with different outside areas. 

(Mackenzie) The biggest thing I learned the value of the offical documentation, while working on the homeworks most of the problems I needed to solve were simple enough to be on stack overflow, but working on this project that was not the case. I very noticably imporved in the speed and efficacy with which I could figure out what I needed to do by just scanning the offcial doccumation quickly. I was honestly kind of astounded how quickly I was able to figure out the audio just using one article which told me what classes to look at and then looking at the methods on oracle to understand what I actually needed to do on only my third or fourth try. If I had unlimted time I would work on some of the commands and other UI elemnts to make the game feel more intuitive and engaging. The enter command in particular I would've loved to make more natural, like saying "enter McConnel" as one command as opposed to the two step process. Or even if that was two much fixing it so it accepted any capitalization or making it so that if you mistyped you wouldn't have to redo the whole enter process.

The most helpful piece of feedback we got was from Jordan, who helped us figure out how to write the game loop in a way that was actually object oriented, since we were struggling to move past procedural thinking for that specific aspect.

Our advice to our past selves would be to write the basic game loop WAY earlier because it would have made testing everything much easier.

When we started the project, we split up the things we had to do between the two of us which was a helpful starting point for the pretty organic way that we divided things as they came up. We used our in-class time (and occasionally staying late) to work on things that needed both of us to think about and then took on separate tasks (usually in separate files, for github reasons) to take home. 

For our desgin justification we intitally planned to intergrate guava into both the map and the dialouge for the ghosts. We decided to use hashtables for both of these elements instead. In general the problem we ran into with Guava was navigating the data structure and acessing the data inside. Particularly for the dialoug, since we needed to write methods that could navigate the graph based on user input. When we were testing the dialouge in graph format we ended up having to write an if statement for each node which had an out-degree > 1 and ultimately the code was nearly just as long as if we had just used a list. We decided to use a hashtable with integer keys instead because this allowed us to do arithmatic on the keys, with the user input as a variable, such that it would produce the key of the next relevant string. In the case of the map we ended up simplifying it so much that we no longer needed a graph as all of the nodes representing buildings wouldve been connected to the "outside" node with no other connections. Becasue of this a hashtable with string keys only accessible while the player is outside has all the same functionality with the addtional benifit of having the string input by the user be able to call the building directly using the key.