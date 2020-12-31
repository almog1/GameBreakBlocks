/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package levels;

import games.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Second level .
 * a kind of level information
 */
public class WideEasy implements LevelInformation {
    private BackgroundWideEasy background;

    /**
     * Create a new WideEasy level information .
     */
    public WideEasy() {
        this.background = new BackgroundWideEasy();
    }

    /**
     * Number of balls in this level .
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 10; //there are 10 balls in this level
    }

    /**
     * The list of the Velocities of the balls .
     *
     * @return the list of all the Velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        //run until number of balls
        int dx, dy;
        int angle = -50;
        int speed = 5;
        Velocity vel = new Velocity(10, 10);
        vel = vel.fromAngleAndSpeed(angle, speed);
        // change velocity of 5 balls
        for (int i = 0; i < 5; i++) {
            velocityList.add(vel);
            angle = angle + 10;
            vel = vel.fromAngleAndSpeed(angle, speed);
        }
        angle = angle + 10;
        vel = vel.fromAngleAndSpeed(angle, speed);
        //change velocity if another 5 balls
        for (int i = 0; i < 5; i++) {
            velocityList.add(vel);
            angle = angle + 10;
            vel = vel.fromAngleAndSpeed(angle, speed);
        }
        return velocityList;
    }

    /**
     * The speed of the paddle .
     *
     * @return the speed (dx value)
     */
    public int paddleSpeed() {
        return 6;
    }

    /**
     * The width size of the paddle .
     *
     * @return the width
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * The name of this Level .
     *
     * @return the level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Create a Sprite of the background and return it .
     *
     * @return the Background of this level
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Create the blocks of this level in a list .
     *
     * @return the lise of blocks
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        double xUp = 35;
        double yUp = 300; // the basic values of upperLeft point
        Color[] colors = new Color[15];
        colors[0] = Color.RED;
        colors[1] = Color.RED;
        colors[2] = Color.ORANGE;
        colors[3] = Color.ORANGE;
        colors[4] = Color.YELLOW;
        colors[5] = Color.YELLOW;
        colors[6] = Color.GREEN;
        colors[7] = Color.GREEN;
        colors[8] = Color.GREEN;
        colors[9] = Color.BLUE;
        colors[10] = Color.BLUE;
        colors[11] = Color.PINK;
        colors[12] = Color.PINK;
        colors[13] = Color.CYAN;
        colors[14] = Color.CYAN;

        double widthBlock = 745 / 15;
        double heightBlock = 20;
        //create the blocks
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1,
                    colors[i], Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        return blocks;
    }

    /**
     * The blocks that need to remove .
     *
     * @return the blocks number
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
