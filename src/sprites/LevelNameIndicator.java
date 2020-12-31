/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

import java.awt.Color;

/**
 * The LevelNameIndicator class is in charge of displaying the current Level Name .
 * It hold a reference to the level name variable
 * It is a kind ot Sprite
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    private int x;
    private int y;

    /**
     * Constructor of a new Sprite of LevelNameIndicator .
     *
     * @param levelName . String of the current name
     * @param x         . int the x value of point to write at
     * @param y         . int the y value of point to wirte the name
     */
    public LevelNameIndicator(String levelName, int x, int y) {
        this.levelName = levelName;
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the the text of this Level Name in the surface given .
     *
     * @param surface . DrawSurface on it draw the text by its values
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //print the text of this sprite
        surface.drawText(this.x, this.y, "Level Name: " + this.levelName, 14);
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
     * @param gameLevel . games.GameLevel to add the Level Indicator to it
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this); //add the levelInsdicator to the Sprites collections
    }
}
