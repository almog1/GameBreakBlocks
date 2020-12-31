/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

import java.awt.Color;

/**
 * Class of colorBackground .
 */
public class ColorBackground implements Background {
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color color;

    /**
     * Constructor of a new ImageBackground .
     *
     * @param color  . Color
     * @param xPos   . int xPosition
     * @param yPos   . int yPosition
     * @param width  . int the width of this background
     * @param height . int the height of the background
     */
    public ColorBackground(Color color, int xPos, int yPos, int width, int height) {
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this); //add the background as a Sprite
    }

    @Override
    public void timePassed(double dt) {
        //nothing
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Draw in the right color
        d.setColor(this.color);
        d.fillRectangle(this.xPos, this.yPos, this.width, this.height);
    }
}
