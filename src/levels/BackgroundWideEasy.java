/**
 * @author Almog Gueta
 * @version 3.0
 * @since 24.05.18
 */
package levels;

import biuoop.DrawSurface;
import games.GameLevel;
import geometry.Line;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background of the second level .
 */
public class BackgroundWideEasy implements Sprite {
    /**
     * Constructor of the second level background .
     */
    public BackgroundWideEasy() {
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        //draw the background in black
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 60, 800, 600);
        Line[] lines = new Line[80];
        int xVal = 20;
        //create all the lines
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(180, 180, xVal, 300);
            xVal = xVal + 8;
        }
        //set the color for the lines
        d.setColor(new Color(239, 231, 176));
        //draw the lines
        for (int j = 0; j < lines.length; j++) {
            Line line1 = lines[j];
            d.drawLine((int) line1.start().getX(), (int) line1.start().getY(),
                    (int) line1.end().getX(), (int) line1.end().getY());
        }
        //create the circles of the sun
        d.setColor(new Color(239, 231, 176));
        d.fillCircle(180, 180, 70);
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(180, 180, 60);
        d.setColor(new Color(255, 221, 28));
        d.fillCircle(180, 180, 50);
    }

    /**
     * Notify the sprites.Sprite that time has passed .
     *
     * @param dt . double specifies the amount of seconds passed since the last call
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
