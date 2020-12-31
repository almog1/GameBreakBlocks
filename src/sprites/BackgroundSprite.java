/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

import java.awt.Color;
import java.awt.Image;

/**
 * Object of the background of this game .
 * is a kind of sprite
 */
public class BackgroundSprite implements Sprite {
    //a color get in the constructor
    private Color color;
    private int x;
    private int y;
    private Image img;

    /**
     * Create a new Background from the color enterd .
     *
     * @param color . Color of the background
     * @param x     . int
     * @param y     . int
     */
    public BackgroundSprite(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new Background from the image enterd .
     *
     * @param img . Image of the background
     * @param x   . int
     * @param y   . int
     */
    public BackgroundSprite(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        //draw all the drawsurface in one color
        d.fillRectangle(0, 0, d.getHeight(), d.getHeight());
    }

    /**
     * Notify the sprites.Sprite that time has passed .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void timePassed(double dt) {

    }

    /**
     * Add the sprites.Sprite to the gameLevel by its own method .
     *
     * @param gameLevel . games.GameLevel to add the sprites.Sprite to it
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this); //add the background as a Sprite
    }
}
