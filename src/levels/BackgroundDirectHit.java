/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package levels;

import biuoop.DrawSurface;
import games.GameLevel;
import geometry.Line;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background of the first level .
 */
public class BackgroundDirectHit implements Sprite {
    /**
     * Constructor of the first level background .
     */
    public BackgroundDirectHit() {
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        //draw the background in black
        d.setColor(Color.BLACK.darker());
        d.fillRectangle(0, 60, 800, 600);
        //sprite with a draw
        //create the lines
        Line[] lines = new Line[4];
        lines[0] = new Line(400, 35, 400, 125);
        lines[1] = new Line(400, 205, 400, 305);
        lines[2] = new Line(260, 165, 360, 165);
        lines[3] = new Line(440, 165, 540, 165);

        //draw all the lines on the drawsurface
        for (int i = 0; i < lines.length; i++) {
            Line line1 = lines[i];
            d.setColor(Color.BLUE);
            d.drawLine((int) line1.start().getX(), (int) line1.start().getY(),
                    (int) line1.end().getX(), (int) line1.end().getY());
        }
        d.drawCircle(400, 165, 110);
        d.drawCircle(400, 165, 90);
        d.drawCircle(400, 165, 70);
    }

    // notify the sprite that time has passed

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
