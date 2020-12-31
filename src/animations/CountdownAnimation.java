/**
 * @author Almog Gueta
 * @version 4.0
 * @since 22.06.18
 */
package animations;

import biuoop.DrawSurface;
import collisions.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation display the given gameScreen .
 * for numOfSeconds seconds, and on top of them it shows
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double timeOfStay;
    private boolean needStop;
    private long startTime;
    private boolean isFirstTime;

    /**
     * Constructor of a new CoutdownAnimation .
     *
     * @param numOfSeconds . double number of seconds
     * @param countFrom    . int count form
     * @param gameScreen   . SpriteCollection of the game
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom + 1;
        this.gameScreen = gameScreen;
        //time for evert count
        this.timeOfStay = this.numOfSeconds / this.countFrom;
        this.needStop = false;
        this.isFirstTime = false;

    }

    /**
     * method of one frame of countdown .
     * draw all the sprites and print countfrom on the "backround"
     *
     * @param d  . DrawSurface on it
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d); //draw all the sprites of this game on the screen

        // if it is the first running - intialize the start time to now
        if (this.isFirstTime) {
            this.isFirstTime = false;
            this.startTime = System.currentTimeMillis();
        }
        // timing
        long usedTime = (System.currentTimeMillis() / 1000) - (this.startTime / 1000); //count the time passed
        // check if the time of use passed - need to decrease countFrom
        if (usedTime < this.timeOfStay) {

            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 - 1, d.getHeight() / 2 - 1, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2 + 1, d.getHeight() / 2 - 1, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2 - 1, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);

            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);
        } else {
            this.countFrom = this.countFrom - 1;
            this.startTime = System.currentTimeMillis();
        }
        //if countForm is 0 - stop
        if (this.countFrom == 0) {
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 - 1, d.getHeight() / 2 - 1, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2 + 1, d.getHeight() / 2 - 1, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);
            d.drawText(d.getWidth() / 2 - 1, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);

            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 - 2, (Integer.toString(this.countFrom)), 40);
            this.needStop = true;
        }
    }

    /**
     * Change the boolean to stop this running .
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return this.needStop;
    }
}
