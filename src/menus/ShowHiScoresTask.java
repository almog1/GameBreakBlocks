/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package menus;

import animations.Animation;
import animations.AnimationRunner;
import games.Task;

/**
 * Task shows the high score .
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor of a new ShowHiScores Task .
     *
     * @param runner              . AnimationRunner of this task
     * @param highScoresAnimation . Animation to run
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * Run the animation .
     *
     * @return null
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
