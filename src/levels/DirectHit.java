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
 * First Level - direct hit .
 */
public class DirectHit implements LevelInformation {
    private BackgroundDirectHit background;

    /**
     * Cretae it with the background .
     */
    public DirectHit() {
        this.background = new BackgroundDirectHit();
    }

    /**
     * Number of balls in this level .
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 1; //just one ball in the game
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
        velocityList.add(new Velocity(0, 600)); //the velocity of the ball
        return velocityList;
    }

    /**
     * The speed of the paddle .
     *
     * @return the speed (dx value)
     */
    public int paddleSpeed() {
        return 600;
    }

    /**
     * The width size of the paddle .
     *
     * @return the width
     */
    public int paddleWidth() {
        return 70;
    }
    // the level name will be displayed at the top of the screen.

    /**
     * The name of this Level .
     *
     * @return the level name
     */
    public String levelName() {
        return "Direct Hit";
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

    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * Create the blocks of this level in a list .
     *
     * @return the lise of blocks
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block = new Block(new Point(365, 130), 70, 70,
                1, Color.RED.darker(), Color.BLACK);
    //    d.setColor(new Color((float) 0.xxx,(float) 0.yyy,(float) 0.zzz));
       // Color color = new Color(256,0,0)
        blocks.add(block);
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
        return 1;
    }
}