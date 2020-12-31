/**
 * @author Almog Gueta
 * @version 3.0
 * @since 21.05.18
 */
package sprites;

import biuoop.DrawSurface;
import games.Counter;
import games.GameLevel;

import java.awt.Color;

/**
 * The LivesIndicator class is in charge of displaying the current Lives .
 * It hold a reference to the lives counter
 * It is a kind ot Sprite
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private int x;
    private int y;

    /**
     * Constructor of a new Sprite of LivesIndicator .
     *
     * @param lives . Counter of the current lives
     * @param x     . int the x value of point to write the lives
     * @param y     . int the y value of point to wirte the lives
     */
    public LivesIndicator(Counter lives, int x, int y) {
        this.lives = lives;
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the the text of this Lives Indicators in the surface given .
     *
     * @param surface . DrawSurface on it draw the text by its values
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //print the text of this sprite
        surface.drawText(this.x, this.y, "Lives: " + this.lives.getValue(), 14);
    }

    /**
     * If the time passed - what will it do .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void timePassed(double dt) {

    }

    /**
     * Add it to the gameLevel .
     *
     * @param gameLevel . games.GameLevel to add the LivesIndicator to it
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this); //add the LivesIndicator to the Sprites collections
    }
}
