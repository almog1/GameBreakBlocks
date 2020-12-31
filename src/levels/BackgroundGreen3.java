/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package levels;

import biuoop.DrawSurface;
import games.GameLevel;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background of the third level .
 * A sprite
 */
public class BackgroundGreen3 implements Sprite {
    /**
     * Constructor of the third level background .
     */
    public BackgroundGreen3() {
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 60, 800, 600);

        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(155, 160, 10, 200);

        d.setColor(new Color(216, 172, 102));
        d.fillCircle(160, 156, 12);
        d.setColor(new Color(246, 77, 54));
        d.fillCircle(160, 156, 8);
        d.setColor(new Color(255, 255, 255));
        d.fillCircle(160, 156, 4);

        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(140, 360, 40, 60);

        d.setColor(Color.BLACK);
        d.fillRectangle(100, 420, 120, 180);
        int xVal = 110, width = 12;
        int yVal = 430, height = 28;
        d.setColor(Color.WHITE);
        //run on every line
        for (int i = 0; i < 5; i++) {
            //run on the specific line
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(xVal, yVal, width, height);
                xVal = xVal + width + 10;
            }
            yVal = yVal + height + 10;
            xVal = 110;
        }

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
