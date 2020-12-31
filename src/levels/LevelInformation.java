/**
 * @author Almog Gueta
 * @version 3.0
 * @since 22.05.18
 */
package levels;

import games.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * Information about this level .
 */
public interface LevelInformation {
    /**
     * The number of balls in this game .
     *
     * @return the number of balls
     */
    int numberOfBalls();
    //
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * The initial velocity of each ball .
     *
     * @return List of the velocities of the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * Speed of the paddle .
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * Width of the paddle .
     *
     * @return the size of the the paddle
     */
    int paddleWidth();

    // the level name will be displayed at the top of the screen.

    /**
     * The name of the level .
     *
     * @return String of the name of the level
     */
    String levelName();

    // Returns a sprite with the background of the level

    /**
     * A sprite of this level background .
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level .
     * @return a List of Blocks
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed .
     * @return the number of blocks we need toremo
     */
    int numberOfBlocksToRemove();
}
