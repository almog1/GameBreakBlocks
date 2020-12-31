/**
 * @author Almog Gueta
 * @version 4.0
 * @since 08.06.18
 */
package games;

import java.io.Serializable;

/**
 * Score info class represent a score .
 * it has name of the player and his score in this game
 */
public class ScoreInfo implements Comparable, Serializable {
    private String name;
    private int score;

    /**
     * Constructor of a new ScoreInfo .
     *
     * @param name  . String the name of the player
     * @param score . Int the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Return the name of the Score player .
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the score if the info .
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Compare between two ScoreInfos .
     * if this scoreinfpt score is bigger then the other score - return -1
     * if the other is bigger - return 1
     * if equals - return 0
     *
     * @param other . Object to compare the score to this
     * @return 1 0 or -1 by comparing them
     */
    @Override
    public int compareTo(Object other) {
        //check they both instance of ScoreInfo
        if (other instanceof ScoreInfo) {
            ScoreInfo otheScoreInfo = (ScoreInfo) other;
            //check if the other score is bigger - return 1
            //if this bigger - return -1 . if equals return 0
            if (this.score < otheScoreInfo.getScore()) {
                return 1;
            } else if (this.score > otheScoreInfo.getScore()) {
                return -1;
            } else {
                return 0;
            }
        } else {
            //default
            return -1;
        }
    }

    /**
     * Return the string of the Score Info - name and score .
     *
     * @return the String
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\t" + " Score: " + this.score;
    }
}

