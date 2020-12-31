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
 * Third level - Green3 .
 */
public class Green3 implements LevelInformation {
    private BackgroundGreen3 background;

    /**
     * Cretae it with the background .
     */
    public Green3() {
        this.background = new BackgroundGreen3();
    }

    /**
     * Number of balls in this level .
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 2;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * The list of the Velocities of the balls .
     *
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        int angle = -50;
        int speed = 420;
        Velocity vel = new Velocity(10, 10);
        vel = vel.fromAngleAndSpeed(angle, speed);
        velocityList.add(vel); // the velocity of first ball
        angle = angle + 100;
        vel = vel.fromAngleAndSpeed(angle, speed);
        velocityList.add(vel); //the velocity of second ball
        return velocityList;
    }

    /**
     * The speed of the paddle .
     *
     * @return the speed (dx value)
     */
    public int paddleSpeed() {
        return 480;
    }

    /**
     * The width size of the paddle .
     *
     * @return the width
     */
    public int paddleWidth() {
        return 100;
    }
    // the level name will be displayed at the top of the screen.

    /**
     * The name of this Level .
     *
     * @return the level name
     */
    public String levelName() {
        return "Green 3";
    }
    // Returns a sprite with the background of the level

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
        double xUp = 310;
        double yUp = 150; // the basic values of upperLeft point

        double widthBlock = 45;
        double heightBlock = 30;
        double xUpCp = xUp;
        //create the blocks line 5
        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 2,
                    Color.GRAY, Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        xUp = xUpCp + widthBlock;
        xUpCp = xUp;
        yUp = yUp + heightBlock;
        //create the blocks line 4
        for (int i = 0; i < 9; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1,
                    Color.RED, Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        xUp = xUpCp + widthBlock;
        xUpCp = xUp;
        yUp = yUp + heightBlock;
        //create the blocks line 3
        for (int i = 0; i < 8; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1,
                    Color.YELLOW, Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        xUp = xUpCp + widthBlock;
        xUpCp = xUp;
        yUp = yUp + heightBlock;
        //create the blocks - line 2
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1,
                    Color.BLUE, Color.BLACK);
            blocks.add(block);
            xUp = xUp + widthBlock;
        }
        xUp = xUpCp + widthBlock;
        xUpCp = xUp;
        yUp = yUp + heightBlock;
        //create the blocks -line 1
        for (int i = 0; i < 6; i++) {
            Block block = new Block(new Point(xUp, yUp), widthBlock, heightBlock, 1,
                    Color.WHITE, Color.BLACK);
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
        return 40;
    }
}
