/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package sprites;

import biuoop.DrawSurface;
import games.GameLevel;

import java.awt.Image;

/**
 * Image background .
 */
public class ImageBackground implements Background {
    private int xPos;
    private int yPos;
    private Image image;

    /**
     * Constructor of a new ImageBackground .
     *
     * @param image . Image
     * @param xPos  . int xPosition
     * @param yPos  . int yPosition
     */
    public ImageBackground(Image image, int xPos, int yPos) {
        this.image = image;
        this.xPos = xPos;
        this.yPos = yPos;
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
        // Draw the image on a DrawSurface
        d.drawImage(this.xPos, this.yPos, this.image);
    }
}
