/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package levels;

import biuoop.DrawSurface;
import games.GameLevel;
import geometry.Line;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background of the fourth level .
 * a kindof Sprite
 */
public class BackgroundFinalFour implements Sprite {
    /**
     * Constructor of the fourth level background .
     */
    public BackgroundFinalFour() {
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 60, 800, 600);

        Line[] lines = new Line[10];
        int xVal = 220, yVal = 380;
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(xVal, yVal, xVal - 30, 600);
            xVal = xVal + 10;
        }

        d.setColor(Color.WHITE);
        //line for the left rain
        for (int j = 0; j < lines.length; j++) {
            Line line1 = lines[j];
            d.drawLine((int) line1.start().getX(), (int) line1.start().getY(),
                    (int) line1.end().getX(), (int) line1.end().getY());
        }

        d.setColor(new Color(204, 204, 204));
        d.fillCircle(225, 380, 24);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(240, 400, 26);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(260, 370, 28);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(295, 380, 28);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(270, 400, 20);

        //Second

        xVal = 620;
        yVal = 500;
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(xVal, yVal, xVal - 35, 600);
            xVal = xVal + 10;
        }

        d.setColor(Color.WHITE);
        //change all the lines for the rain
        for (int j = 0; j < lines.length; j++) {
            Line line1 = lines[j];
            d.drawLine((int) line1.start().getX(), (int) line1.start().getY(),
                    (int) line1.end().getX(), (int) line1.end().getY());
        }

        d.setColor(new Color(204, 204, 204));
        d.fillCircle(630, 460, 24);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(645, 500, 26);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(665, 470, 28);


        d.setColor(new Color(170, 170, 170));
        d.fillCircle(700, 480, 28);

        d.setColor(new Color(170, 170, 170));
        d.fillCircle(675, 500, 20);

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
