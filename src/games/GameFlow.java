/**
 * @author Almog Gueta
 * @version 3.0
 * @since 24.05.18
 */
package games;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.io.File;
import java.util.List;

/**
 * The GameFlow class is in charge of creating the different levels .
 * and moving from one level to the next
 */
public class GameFlow {
    // score and lives
    //They should be created and kept at the GameFlow
    // go as parameter to game level
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private File fileScores;
    private HighScoresTable highScoreTable;

    /**
     * Create a new Game Flow .
     *
     * @param animationRunner . Animation Runner of thie game flow
     * @param keyboardSensor  . KeyboardSensor of this gui
     * @param lives           . Int of number of lives the Game start with
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, int lives) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        //initialize the Score and Lives counters
        this.lives = new Counter(lives);
        this.score = new Counter();
        this.fileScores = new File("highscores");
        //if not exist - create it
        this.highScoreTable = HighScoresTable.loadFromFile(this.fileScores);
    }

    /**
     * Get a list of level information .
     * And run the levels
     *
     * @param levels . List<LevelInformation> to run
     */
    public void runLevels(List<LevelInformation> levels) {
        // run on every level
        for (LevelInformation levelInfo : levels) {
            //take the game level from this list
            GameLevel level = new GameLevel(this.animationRunner, this.keyboardSensor, levelInfo, this.score,
                    this.lives);
            //initialize this level
            level.initialize();
            //as long there are lives and there are blocks
            while (level.getBlocksCounter() > 0 && level.getNumberOfLives() > 0) {
                level.playOneTurn();
            }
            //if no more lives - break this while
            if (this.lives.getValue() == 0) {
                break;
            }
        }

        //if no more lifes
        if (this.lives.getValue() == 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new EndScreen(this.score, false)));
        } else {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new EndScreen(this.score, true)));
        }

        // check if the playerneed to add to the scores
        if (this.highScoreTable.getRank(this.score.getValue()) <= this.highScoreTable.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScoreTable.add(new ScoreInfo(name, this.score.getValue()));
        }

        //save the table
        try {
            this.highScoreTable.save(this.fileScores);
        } catch (Exception e) {
            System.out.println("Eroor while saving the data to the file!");
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new HighScoresAnimation(this.highScoreTable, "space")));

        return;
    }
}
