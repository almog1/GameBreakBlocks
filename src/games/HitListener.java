/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package games;

import sprites.Ball;
import sprites.Block;

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface .
 * It should register themselves with a HitNotifier object using its addHitListener method
 */
public interface HitListener {

    /**
     * Hit event that send when been hitter .
     *
     * @param beingHit . Block that been hit
     * @param hitter   . Ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}