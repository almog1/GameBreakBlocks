/**
 * @author Almog Gueta
 * @version 4.0
 * @since 08.06.18
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import games.HighScoresTable;

import java.awt.Color;

/**
 * HighScoresAnimation is graphical representation of the scores .
 * It will display the scores in the high-scores table,
 * until a specified key is pressed.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTables;
    private KeyboardSensor keyboard;
    private boolean stop;
    private String endKey;

    /**
     * Empty Constructor .
     */
    /**
     * HighScoresAnimation class represent the table of scores .
     *
     * @param scores . HighScoresTable table of scores
     * @param endKey . String the key to end this animation
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.scoresTables = scores;
        this.endKey = endKey;
    }
    /**
     * Constructor of a new HighScoresAnimation - scores table .
     *
     * @param scores . HighScoresTable the table of all the scores
     * @param endKey . String of stopping this screen
     * @param k      . KeyboardSensor the sensor
     */
/*    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        this.scoresTables = scores;
        this.endKey = endKey;
    }*/

    /**
     * Tell if it should stop or not .
     *
     * @return true or false
     */
    @Override
    public boolean shouldStop() {
        //   return this.stop;
        return true;
    }

    /**
     * Show Scores frame .
     *
     * @param d  . DrawSurface to put the screen here
     * @param dt . double specifies the amount of seconds passed since the last call
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.BLACK);

        d.drawText(21, 34, "High Scores", 38);
        d.drawText(19, 34, "High Scores", 38);
        d.drawText(20, 36, "High Scores", 38);
        d.drawText(19, 36, "High Scores", 38);
        d.setColor(Color.PINK);
        d.drawText(20, 34, "High Scores", 38);

        d.setColor(Color.BLACK);
        d.drawText(51, 94, "Player Name", 28);
        d.drawText(49, 94, "Player Name", 28);
        d.drawText(50, 96, "Player Name", 28);
        d.drawText(49, 96, "Player Name", 28);
        //    d.setColor(new Color(210,113,227));
        d.setColor(Color.PINK.darker());
        d.drawText(50, 94, "Player Name", 28);

        d.setColor(Color.BLACK);
        d.drawText(501, 94, "Score", 28);
        d.drawText(499, 94, "Score", 28);
        d.drawText(500, 96, "Score", 28);
        d.drawText(499, 96, "Score", 28);
        //    d.setColor(new Color(210,113,227));
        d.setColor(Color.PINK.darker());
        d.drawText(500, 94, "Score", 28);

        d.setColor(Color.BLACK);
        d.drawText(51, 104, "____________________________________", 28);
        d.drawText(49, 104, "____________________________________", 28);
        d.drawText(50, 106, "____________________________________", 28);
        d.drawText(49, 106, "____________________________________", 28);
        //    d.setColor(new Color(210,113,227));
        d.setColor(Color.PINK.darker());
        d.drawText(50, 104, "____________________________________", 28);

        d.setColor(Color.BLACK);
        d.drawText(201, 550, "Press " + this.endKey + " to continue", 28);
        d.drawText(199, 550, "Press " + this.endKey + " to continue", 28);
        d.drawText(200, 552, "Press " + this.endKey + " to continue", 28);
        d.drawText(199, 552, "Press " + this.endKey + " to continue", 28);
        d.setColor(new Color(227, 208, 220));
        //d.setColor(Color.PINK.darker().darker());
        d.drawText(200, 550, "Press " + this.endKey + " to continue", 28);

        int xValue, yValue;
        xValue = 53;
        yValue = 150;
        //print the list
        for (int i = 0; i < this.scoresTables.getHighScores().size(); i++) {
            d.setColor(Color.BLACK);
            d.drawText(xValue + 1, yValue, this.scoresTables.getHighScores().get(i).getName(), 28);
            d.drawText(xValue - 1, yValue, this.scoresTables.getHighScores().get(i).getName(), 28);
            d.drawText(xValue, yValue + 2, this.scoresTables.getHighScores().get(i).getName(), 28);
            d.drawText(xValue - 1, yValue + 2, this.scoresTables.getHighScores().get(i).getName(), 28);
            d.setColor(new Color(227, 208, 220));
            //d.setColor(Color.PINK.darker().darker());
            d.drawText(xValue, yValue, this.scoresTables.getHighScores().get(i).getName(), 28);

            d.setColor(Color.BLACK);
            d.drawText(xValue + 450 + 1, yValue,
                    Integer.toString(this.scoresTables.getHighScores().get(i).getScore()), 28);
            d.drawText(xValue - 1 + 450, yValue,
                    Integer.toString(this.scoresTables.getHighScores().get(i).getScore()), 28);
            d.drawText(xValue + 450, yValue + 2,
                    Integer.toString(this.scoresTables.getHighScores().get(i).getScore()), 28);
            d.drawText(xValue - 1 + 450, yValue + 2,
                    Integer.toString(this.scoresTables.getHighScores().get(i).getScore()), 28);
            d.setColor(new Color(227, 208, 220));
            //d.setColor(Color.PINK.darker().darker());
            d.drawText(xValue + 450, yValue,
                    Integer.toString(this.scoresTables.getHighScores().get(i).getScore()), 28);

            yValue = yValue + 50;
        }
    }
}