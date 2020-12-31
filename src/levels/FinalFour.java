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
 * Fourth Level - FinalFour .
 */
public class FinalFour implements LevelInformation {
    private BackgroundFinalFour background;

    /**
     * Cretae it with the background .
     */
    public FinalFour() {
        this.background = new BackgroundFinalFour();
    }

    /**
     * Number of balls in this level .
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * The list of the Velocities of the balls .
     *
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        int angle = -50;
        int speed = 10;
        Velocity vel = new Velocity(10, 10);
        vel = vel.fromAngleAndSpeed(angle, speed);
        velocityList.add(vel); // the velocity of first ball
        angle = angle + 50;
        vel = vel.fromAngleAndSpeed(angle, speed);
        velocityList.add(vel); //the velocity of second ball
        angle = angle + 50;
        vel = vel.fromAngleAndSpeed(angle, speed);
        velocityList.add(vel); //the velocity of third ball
        return velocityList;
    }

    /**
     * The speed of the paddle .
     *
     * @return the speed (dx value)
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * The width size of the paddle .
     *
     * @return the width
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * The name of this Level .
     *
     * @return the level name
     */
    public String levelName() {
        return "Final Four";
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
        //int xUp = WIDTH_GUI - BORDER_SIZE - NUMBER_OF_BLOCKS * BLOCK_WIDTH;
        double xUp = 35, xUpCp = 35;
        double yUp = 100; // the basic values of upperLeft point

        double widthBlock = 745 / 15;
        double heightBlock = 28;
        Color[] colors = new Color[7];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.WHITE;
        colors[5] = Color.PINK;
        colors[6] = Color.CYAN;

        //just for 2 points hit of the greys
        for (int j = 0; j < 15; j++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 2, colors[0], Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        xUp = xUpCp;
        yUp = yUp + heightBlock;
        //create the blocks
        for (int i = 1; i < colors.length; i++) {
            //create the line
            for (int k = 0; k < 15; k++) {
                Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1, colors[i], Color.BLACK);
                blocks.add(block);
                xUp = xUp + widthBlock;
            }
            xUp = xUpCp;
            yUp = yUp + heightBlock;
        }
        return blocks;
    }

    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * The blocks that need to remove .
     *
     * @return the blocks number
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
