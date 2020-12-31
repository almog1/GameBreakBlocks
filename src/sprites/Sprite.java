/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

/**
 * A Sprite of the game .
 * anithing in the game that can be drawn
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    void drawOn(DrawSurface d);

    // notify the sprite that time has passed

    /**
     * Notify the sprites.Sprite that time has passed .
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    void timePassed(double dt);

    /**
     * Add the sprites.Sprite to the gameLevel by its own method .
     *
     * @param gameLevel . games.GameLevel to add the sprites.Sprite to it
     */
    void addToGame(GameLevel gameLevel);
}