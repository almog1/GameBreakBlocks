/**
 * @author Almog Gueta
 * @version 3.0
 * @since 22.05.18
 */
package animations;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * PauseSCreen is a class of Animation .
 * adding an option to pause the game when pressing the p key.
 * display a screen with the message paused -- press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {

    /**
     * Empty constructor .
     */
    public PauseScreen() {

    }

    /**
     * Show pause frame .
     *
     * @param d  . DrawSurface to put the screen pause there
     * @param dt . double specifies the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        //draw a circlue in the middle
        d.setColor(Color.PINK);
        d.fillCircle(400, 300, 100);
        d.setColor(Color.BLACK);
        d.fillCircle(400, 300, 70);
        d.setColor(Color.PINK);
        d.drawText(375, 302, "PAUSE", 20);
        d.drawText(376, 303, "PAUSE", 20);
        d.drawText(350, 450, "press space to continue", 14);
    }

    /**
     * Tell if it should stop or not .
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return true;
    }
}
