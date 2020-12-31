/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package games;


import sprites.Ball;
import sprites.Block;

/**
 * Ball Remover call is in charge of removing balls, and updating an availabe-balls counter .
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Create a new ball remover with a Count and with the current gameLevel .
     *
     * @param gameLevel           . GameLevel to remove the ball from it
     * @param remainingBalls . Count of the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method remove the ball from the gameLevel .
     *
     * @param beingHit . Block that hitted
     * @param hitter   . Ball hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel); //remove the ball from the gameLevel
        this.remainingBalls.decrease(1); //decrease the counter number by one
    }
}
