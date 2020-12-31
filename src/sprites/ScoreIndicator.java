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
 * The ScoreIndictor class is in charge of displaying the current score .
 * It hold a reference to the scores counter
 * It is a kind ot Sprite
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int x;
    private int y;

    /**
     * Constructor of a new Sprite of ScoreIndicator .
     *
     * @param score . Counter of the current score
     * @param x     . int the x value of point to write at
     * @param y     . int the y value of point to wirte the score
     */
    public ScoreIndicator(Counter score, int x, int y) {
        this.score = score;
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the the text of this ScoreIndicator in the surface given .
     *
     * @param surface . DrawSurface on it draw the text by its values
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //print the text of this sprite
        surface.drawText(this.x, this.y, "Score: " + this.score.getValue(), 14);
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
     * @param gameLevel . games.GameLevel to add the ScoreIndicator to it
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this); //add the scoreInsicator to the Sprites collections
    }
}
