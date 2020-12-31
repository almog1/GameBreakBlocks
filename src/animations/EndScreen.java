/**
 * @author Almog Gueta
 * @version 4.0
 * @since 22.06.18
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import games.Counter;

import java.awt.Color;

/**
 * Animation of the screen in the end of the game .
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private Counter score;
    private boolean isWin;

    /**
     * Constructor of a new EndScreen .
     *
     * @param score . Counter with the current score
     * @param isWin . boolean of true if won false if lost
     */
    public EndScreen(Counter score, boolean isWin) {
        this.score = score;
        this.isWin = isWin;
    }

    /**
     * Show pause frame .
     *
     * @param d  . DrawSurface to put the screen pause there
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        String isWinSt;
        //string of win or lose
        if (this.isWin) {
            isWinSt = "You Win!";
        } else {
            isWinSt = "Game Over.";
        }
        d.setColor(new Color(157, 106, 117));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(91, d.getHeight() / 2 - 2, isWinSt + " Your score is " + this.score.getValue(), 40);
        d.drawText(89, d.getHeight() / 2 - 1, isWinSt + " Your score is " + this.score.getValue(), 40);
        d.drawText(90, d.getHeight() / 2 - 2, isWinSt + " Your score is " + this.score.getValue(), 40);
        d.drawText(91, d.getHeight() / 2 - 1, isWinSt + " Your score is " + this.score.getValue(), 40);

        d.setColor(new Color(125, 75, 86));
        d.drawText(90, d.getHeight() / 2 - 1, isWinSt + " Your score is " + this.score.getValue(), 40);

        d.setColor(Color.BLACK);
        d.drawText(11, d.getHeight() - 51, "Press space to continue", 28);
        d.drawText(10, d.getHeight() - 51, "Press space to continue", 28);
        d.drawText(9, d.getHeight() - 49, "Press space to continue", 28);
        d.drawText(10, d.getHeight() - 49, "Press space to continue", 28);

        d.setColor(new Color(149, 98, 109));
        d.drawText(10, d.getHeight() - 49, "Press space to continue", 28);

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
