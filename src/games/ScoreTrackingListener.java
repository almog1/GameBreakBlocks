/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package games;

import sprites.Ball;
import sprites.Block;

/**
 * Track the score .
 */
public class ScoreTrackingListener implements HitListener {
    //score for remove a block
    private static final int REMOVE_BLOCK_SCORE = 10;

    //score for hit a block
    private static final int HIT_BLOCK_SCORE = 5;

    private Counter currentScore;

    /**
     * Constuctore of a new Score trecking .
     *
     * @param scoreCounter . Counter to initialize the current counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Add scores by the hit .
     *
     * @param beingHit . Block that been hit
     * @param hitter   . Ball that hit this block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_BLOCK_SCORE); //add for hitting
        //check the hitting on them is 0 - add more 10 points
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(REMOVE_BLOCK_SCORE);
        }
    }
}
