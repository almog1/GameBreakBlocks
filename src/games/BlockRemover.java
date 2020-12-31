/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package games;

import sprites.Ball;
import sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the gameLevel .
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Create a new block remover with a Count and with the current gameLevel .
     *
     * @param gameLevel          . GameLevel to remove the block from it
     * @param removedBlocks . Count of the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method check if the block reached to 0 points .
     * if yes - delete the block and the listener from it
     *
     * @param beingHit . Block that hitted
     * @param hitter   . Ball hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //if hit points reach to 0 - remove it
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.gameLevel); //remove this block from the gameLevel
            beingHit.removeHitListener(this); //remove this listener from the block
            this.remainingBlocks.decrease(1); //decrease the counter number by one
        }
    }
}