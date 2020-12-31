/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package menus;

import biuoop.DrawSurface;
import games.GameLevel;
import sprites.Sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Background of the Menu .
 */
public class BackgroundMenu implements Sprite {
    private List<String> keys;
    private List<String> messages;
    private String title;

    /**
     * Constructor of the menu background .
     *
     * @param title    . String the title of this menu
     * @param keys     . List<String> keys to print them to the screen
     * @param messages . List<String> messages to print on the screen the options
     */
    public BackgroundMenu(String title, List<String> keys, List<String> messages) {
        this.keys = keys;
        this.messages = messages;
        this.title = title;
    }

    /**
     * Draw the sprites.Sprite on the DrawSurface .
     *
     * @param d . DrawSurface on this the sprites.Sprite will be drown
     */
    public void drawOn(DrawSurface d) {
        String key, message;
        int xValue, yValue;
        xValue = 53;
        yValue = 110;
        //draw the background with IMAGE
        BufferedImage img = null;
        d.setColor(Color.pink);
        d.fillRectangle(0, 0, 800, 600);
        //try to load the image to the background
    /*    try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "background_images\\menuBackImage.png");
            img = ImageIO.read((is));
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            throw new RuntimeException("Can't load menu image");
        }*/

        //draw the Test of this menu Title
        d.drawText(21, 54, this.title, 44);
        d.drawText(19, 54, this.title, 44);
        d.drawText(20, 56, this.title, 44);
        d.drawText(19, 56, this.title, 44);
        d.setColor(new Color(98, 179, 214));
        d.drawText(20, 54, this.title, 44);

        /**
         * run on the list of the keys
         */
        for (int i = 0; i < this.keys.size(); i++) {
            key = "(" + this.keys.get(i) + ")";
            d.setColor(Color.BLACK);
            d.drawText(xValue + 1, yValue, key, 28);
            d.drawText(xValue - 1, yValue, key, 28);
            d.drawText(xValue, yValue + 2, key, 28);
            d.drawText(xValue - 1, yValue + 2, key, 28);
            d.setColor(new Color(227, 208, 220));
            //d.setColor(Color.PINK.darker().darker());
            d.drawText(xValue, yValue, key, 28);
            yValue = yValue + 50;
        }
        yValue = 110;

        /**
         * run on the list of the messages
         */
        for (int i = 0; i < this.messages.size(); i++) {
            message = this.messages.get(i);
            d.setColor(Color.BLACK);
            d.drawText(xValue + 50 + 1, yValue, message, 28);
            d.drawText(xValue - 1 + 50, yValue, message, 28);
            d.drawText(xValue + 50, yValue + 2, message, 28);
            d.drawText(xValue - 1 + 50, yValue + 2, message, 28);
            d.setColor(new Color(227, 208, 220));
            //d.setColor(Color.PINK.darker().darker());
            d.drawText(xValue + 50, yValue, message, 28);
            yValue = yValue + 50;
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
