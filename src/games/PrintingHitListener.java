package games;

import sprites.Ball;
import sprites.Block;

/**
 * Printinig the listeners .
 */
public class PrintingHitListener implements HitListener {
    /**
     * its hit event - print hit points of the block .
     *
     * @param beingHit . Block that been hit
     * @param hitter   . Ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}