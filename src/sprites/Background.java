/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

/**
 * Background class for backgrounds .
 */
public interface Background extends Sprite {
    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    void drawOn(DrawSurface d);


    @Override
    void timePassed(double dt);

    /**
     * Add the sprites.Sprite to the gameLevel by its own method .
     *
     * @param gameLevel . games.GameLevel to add the sprites.Sprite to it
     */
    void addToGame(GameLevel gameLevel);
}
