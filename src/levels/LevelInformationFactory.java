package levels;
/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */

import games.Velocity;
import sprites.Background;
import sprites.Block;
import sprites.ColorBackground;
import sprites.ImageBackground;
import sprites.Sprite;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Class create level information .
 */
public class LevelInformationFactory implements LevelInformation {
    private Background background;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blocks;
    private List<Velocity> velocities;
    private String levelName;
    private int blockToRemove;

    /**
     * Create new level information factory .
     */
    public LevelInformationFactory() {
        this.background = null;
        this.numberOfBalls = 0;
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.blocks = new ArrayList<Block>();
        this.velocities = new ArrayList<Velocity>();
        this.levelName = null;
        this.blockToRemove = 0;
    }

    /**
     * Change the backgrouund .
     *
     * @param back . Background to change to it
     */
    public void setBackground(Background back) {
        this.background = back;
    }

    /**
     * Get background and change it .
     *
     * @param colorBack . Color
     */
    public void setBackground(Color colorBack) {
        this.background =
                new ColorBackground(colorBack, 0, 30, 800, 600);
    }

    /**
     * Get background and change it .
     *
     * @param imageBack . Image of image file
     */
    public void setBackground(Image imageBack) {
        this.background = new ImageBackground(imageBack, 0, 25);
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Change the number of balls .
     *
     * @param number . int the number of balls
     */
    public void setNumberOfBalls(int number) {
        this.numberOfBalls = number;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Change the paddle speed .
     *
     * @param paddleSp . int new size of the paddle
     */
    public void setPaddleSpeed(int paddleSp) {
        this.paddleSpeed = paddleSp;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Change the width of the paddle .
     *
     * @param paddleW . int the new width
     */
    public void setPaddleWidth(int paddleW) {
        this.paddleWidth = paddleW;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Change the list of blocks .
     *
     * @param blockList . List<Block> change the list of blocks
     */
    public void setBlocks(List<Block> blockList) {
        this.blocks = blockList;
    }

    /**
     * Add the block to the list .
     *
     * @param block . Block to add
     */
    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * Change the list of velocities .
     *
     * @param velocityList . List<Velocity> all the velocities
     */
    public void setVelocities(List<Velocity> velocityList) {
        this.velocities = velocityList;
    }

    /**
     * Add a velocity to the list .
     *
     * @param velocity . Velocity to add
     */
    public void addVelocitiy(Velocity velocity) {
        this.velocities.add(velocity);
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Change the level name .
     *
     * @param level . String the new level name
     */
    public void setLevelName(String level) {
        this.levelName = level;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blockToRemove;
    }

    /**
     * Change the number of blocks to remove from this level .
     *
     * @param blockRemove . int number of balls
     */
    public void setBlockToRemove(int blockRemove) {
        this.blockToRemove = blockRemove;
    }

    /**
     * return thus objucet if all details exist .
     *
     * @return levelinformation
     */
    public LevelInformation create() {
        //check all detailes exist and create
        if (!(this.background == null || this.numberOfBalls == 0 || this.paddleSpeed == 0
                || this.paddleWidth == 0 || this.blocks.isEmpty() || this.velocities.isEmpty()
                || this.levelName == null || this.blockToRemove == 0)) {
            return this;
        } else {
            throw new RuntimeException("NOT ENOGHT INFORMATION");
        }
    }


}
