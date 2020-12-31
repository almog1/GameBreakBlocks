/**
 * @author Almog Gueta
 * @version 4.0
 * @since 22.06.18
 */

import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import games.GameFlow;
import games.HighScoresTable;
import games.Task;
import levels.LevelInformation;
import levels.LevelSpecificationReader;
import menus.Menu;
import menus.MenuAnimation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Main for assiamnet 6 .
 * Run a game by level set file
 */
public class Ass6Game {
    //GUI WIDTH
    private static final int WIDTH_GUI = 800;

    // GUI Height
    private static final int HEIGHT_GUI = 600;

    //lifes number
    private static final int NUMBER_OF_LIFES = 7;

    /**
     * Get the levels from a file and run them by the values .
     * if no level set file - use the default file
     *
     * @param args . String[] array string of level set
     */
    public static void main(String[] args) {

        GUI gui = new GUI("GAME", WIDTH_GUI, HEIGHT_GUI);
        AnimationRunner animationRun = new AnimationRunner(gui);
        //create main menu
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu", gui.getKeyboardSensor());
        String levelSetFilePath = "level_sets.txt";
        //if there are arguments - change the level set path
        if (args.length > 0) {
            levelSetFilePath = args[0];
        }
        //make a reader from the level pset file
        Reader reader = new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetFilePath)));
        //list of the levels from the file set
        List<String> levelSetStrings = new ArrayList<String>();
        //make a sub menu
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Sub Menu", gui.getKeyboardSensor());
        //try to read lines from the file - if failed - throw an error
        try {
            String line = ((BufferedReader) reader).readLine();
            //until the end of the file
            while (line != null) {
                levelSetStrings.add(line);
                line = ((BufferedReader) reader).readLine();
            }
            //take every two lines to be the keys and message and key
            for (int i = 1; i <= levelSetStrings.size(); i = i + 2) {
                String key, value;
                //split key and value
                String[] keyAndValue = levelSetStrings.get(i - 1).split(":");
                key = keyAndValue[0];
                value = keyAndValue[1];
                String fileName = levelSetStrings.get(i);
                subMenu.addSelection(key, value, new Task<Void>() {
                    @Override
                    public Void run() {
                        //list of level information's
                        List<LevelInformation> levels = new ArrayList<LevelInformation>();
                        try {
                            Reader reader = new BufferedReader(new InputStreamReader(
                                    ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)));
                            LevelSpecificationReader levelSpec = new LevelSpecificationReader();
                            levels = levelSpec.fromReader(reader);
                            //create a new game flow
                            GameFlow game = new GameFlow(animationRun, gui.getKeyboardSensor(), NUMBER_OF_LIFES);
                            //run the levels
                            game.runLevels(levels);
                            return null;
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (Exception e) {
                                    System.out.println(e.toString());
                                }
                            }
                        }
                        return null;
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        //add the sub menu to the menu
        menu.addSubMenu("s", "Start a new game", subMenu);
        File fileScores = new File("highscores");
        //if the file isn't exist - create it
        if (!fileScores.exists()) {
            try {
                fileScores.createNewFile();
            } catch (Exception e) {
                System.out.println("can't create it!");
            }
        }
        HighScoresTable highScoreTable = HighScoresTable.loadFromFile(fileScores);
        menu.addSelection("h", "See High Scores", new Task<Void>() {
            @Override
            public Void run() {
                try {
                    HighScoresTable highScoreTable = HighScoresTable.loadFromFile(fileScores);
                    KeyPressStoppableAnimation scores = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                            "space", new HighScoresAnimation(highScoreTable, "space"));
                    animationRun.run(scores);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                return null;
            }
        });

        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        });

        //run until exit
        while (true) {
            animationRun.run(menu);
            //if no status
            if (menu.getStatus() == null) {
                animationRun.run(subMenu);
                // wait for user selection
                Task<Void> task = subMenu.getStatus();
                task.run();
                subMenu.resetMenu();
                menu.resetMenu();
            } else {
                animationRun.run(menu);
                Task<Void> task = menu.getStatus();
                task.run();
                menu.resetMenu();
            }
        }
    }
}
