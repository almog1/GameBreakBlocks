/**
 * @author Almog Gueta
 * @version 3.0
 * @since 24.05.18
 */
package games;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import collisions.SpriteCollection;
import geometry.Point;
import levels.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.LevelNameIndicator;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;

//import sprites.;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Level animation of a level .
 */
public class GameLevel implements Animation {
    //GUI WIDTH
    private static final int WIDTH_GUI = 800;

    // GUI Height
    private static final int HEIGHT_GUI = 600;

    // number of lines of blocks
    private static final int LINES_OF_BLOCKS = 6;

    // number of blocks in the line
    private static final int NUMBER_OF_BLOCKS = 12;

    //borders - their width/height
    private static final int BORDER_SIZE = 25;

    //blocks height
    private static final int BLOCK_HEIGHT = 25;

    //blocks width
    private static final int BLOCK_WIDTH = 50;

    // for the number of hits
    //have 3 options to start - 2 , 1 , X
    private static final int TWO_HITS_LEFT = 2;

    private static final int ONE_HITS_LEFT = 1;

    private static final String ZERO_HITS_LEFT = "X";

    //paddle
    //paddle width
    private static final int PADDLE_WIDTH = 150;

    //paddle height
    private static final int PADDLE_HEIGHT = 15;

    //for score - end the blocks
    private static final int FINISH_BLOCKS = 100;

    //Score Text Printing
    private static final int X_VALUE_SCORE_PLACE = WIDTH_GUI / 2 - 40;
    private static final int Y_VALUE_SCORE_PLACE = BORDER_SIZE - 10;

    //for additional differnce from the border
    private static final int BORDER_ADDITIONAL_SPACE = 5;

    //Lives Text Printing
    private static final int X_VALUE_LIVES_PLACE = WIDTH_GUI / 2 - 350;
    private static final int Y_VALUE_LIVES_PLACE = Y_VALUE_SCORE_PLACE;

    //Level name Text Printing place
    private static final int X_VALUE_LEVEL_PLACE = WIDTH_GUI - 200;
    private static final int Y_VALUE_LEVEL_PLACE = Y_VALUE_SCORE_PLACE;

    //for number of lives
    private static final int NUMBER_OF_LIVES = 4;

    //for the size of radius
    private static final int RADIUS_BALL = 5;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    //  private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private Counter numberOfLives;
    private Paddle paddle;
    private Block deathRegion;

    private AnimationRunner runner;
    private boolean running;

    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;

    private boolean end;

    /**
     * Constructor of a new Game Level .
     * create a new game from the details
     *
     * @param animationRunner  . AnimationRunner the runner of this animation game level
     * @param keyboard         . KeyboardSensor the keyboard sensor of this game level
     * @param levelInformation . LevelInformation the informaion about this level
     * @param score            . Counter of the score until now
     * @param lives            . Counter of the live until now
     */
    public GameLevel(AnimationRunner animationRunner, KeyboardSensor keyboard,
                     LevelInformation levelInformation,
                     Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.ballsCounter = new Counter(); //start for 0
        this.score = score; //start from what entered
        this.numberOfLives = lives; //start from what entered
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        //initialize the blocks to remove from the blocks to remove of this level information
        this.blocksCounter = new Counter(this.levelInformation.numberOfBlocksToRemove());
    }

    /**
     * Return true or false by this.running value .
     *
     * @return return the this.running
     */
    public boolean shouldStop() {
        return this.running;
    }

    /**
     * Return the game environment .
     *
     * @return the environment of this game
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Add a collisions.Collidable to the games.GameLevel .
     *
     * @param c . collisions.Collidable add to the games.GameLevel Environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a sprites.Sprite to the sprites.Sprite Coleection .
     *
     * @param s . sprites.Sprite to add to the Collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Create the indicators for this game .
     * score,level name and live
     */
    public void indicatorsCreation() {
        LivesIndicator livesInd = new LivesIndicator(this.numberOfLives, X_VALUE_LIVES_PLACE, Y_VALUE_LIVES_PLACE);
        livesInd.addToGame(this); //add the lives to the game
        //for the score on the screen
        ScoreIndicator scoreInd = new ScoreIndicator(this.score, X_VALUE_SCORE_PLACE, Y_VALUE_SCORE_PLACE);
        LevelNameIndicator levelName = new LevelNameIndicator(this.levelInformation.levelName(),
                X_VALUE_LEVEL_PLACE, Y_VALUE_LEVEL_PLACE);
        levelName.addToGame(this);
        scoreInd.addToGame(this); //add the score to the game
    }

    /**
     * Initial a new games.GameLevel by make blocks and add them to the game .
     * and initialize their listeners
     */
    public void initialize() {
        //create the background and add it to the game
        this.levelInformation.getBackground().addToGame(this);
        //create a blockRemover will be the listener
        BlockRemover blockRemoverGame = new BlockRemover(this, this.blocksCounter);
        //listener for the score
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.score);
        this.indicatorsCreation();
        this.makeBorders();
        this.makeBlocksPattern(blockRemoverGame, scoreTrack);
    }

    /**
     * Create the special block which is the death region .
     * if the balls go to this area - the ball is out of the game
     */
    private void createDeathRegion() {
        this.deathRegion = new Block(new Point(BORDER_SIZE, HEIGHT_GUI), WIDTH_GUI
                - (2 * BORDER_SIZE),
                BORDER_SIZE, 0, Color.YELLOW.darker(), Color.BLACK);
        this.deathRegion.addToGame(this);
        BallRemover ballRemoverGame = new BallRemover(this, this.ballsCounter);
        this.deathRegion.addHitListener(ballRemoverGame); //add the listener to the block
    }

    /**
     * create the balls for the game .
     */
    private void createBallsOnPaddle() {
        //put it in the middle of this paddle
        Point middleOfPaddle = new Point((this.paddle.getUpperLeft().getX() + (this.paddle.getWidth()) / 2),
                this.paddle.getUpperLeft().getY());
        List<Velocity> ballsVel = this.levelInformation.initialBallVelocities();
        List<Ball> balls = new ArrayList<Ball>(); //should be in the count of count balls
        //run and add balls
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            balls.add(new Ball(middleOfPaddle, RADIUS_BALL, Color.WHITE));
            //set the velocity to the match in the velocity list
            balls.get(i).setVelocity(ballsVel.get(i));
            this.ballsCounter.increase(1);
            balls.get(i).addToGame(this); //add the ball to the game
        }
    }

    /**
     * make the blocks of the borders and add them to the game .
     */
    private void makeBorders() {
        //create Sprite Blocks for borders and add them to the game
        Sprite upBorder = new Block(new Point(
                0, Y_VALUE_SCORE_PLACE + BORDER_ADDITIONAL_SPACE), WIDTH_GUI,
                BORDER_SIZE + BORDER_ADDITIONAL_SPACE + BORDER_ADDITIONAL_SPACE, 0,
                Color.GRAY, Color.GRAY);
        upBorder.addToGame(this);
        Sprite leftBorder = new Block(new Point(0, BORDER_SIZE + Y_VALUE_SCORE_PLACE), BORDER_SIZE,
                HEIGHT_GUI - BORDER_SIZE,
                0, Color.GRAY, Color.GRAY);
        leftBorder.addToGame(this);
        Sprite rightBorder = new Block(new Point(WIDTH_GUI - BORDER_SIZE, BORDER_SIZE + Y_VALUE_SCORE_PLACE),
                BORDER_SIZE, HEIGHT_GUI - BORDER_SIZE, 0, Color.GRAY, Color.GRAY);
        rightBorder.addToGame(this);
    }

    /**
     * Create the paddle .
     * take the information from the levelInformation
     */
    private void paddleCreation() {
        //create the paddle in the middle
        this.paddle = new Paddle(new Point((WIDTH_GUI / 2) - (this.levelInformation.paddleWidth() / 2),
                HEIGHT_GUI - BORDER_SIZE - PADDLE_HEIGHT), this.levelInformation.paddleWidth(),
                PADDLE_HEIGHT, this.keyboard, BORDER_SIZE, WIDTH_GUI - BORDER_SIZE,
                new Velocity(this.levelInformation.paddleSpeed(), 0));
        paddle.addToGame(this);
    }

    /**
     * Make the pattern of the blocks .
     * take the blocks from the LevelInformation
     *
     * @param blockRemoverGame . BlockRemover add the blocks to the listener
     * @param scoreTrack       . ScoreTrackingListener for the scoring
     */
    private void makeBlocksPattern(BlockRemover blockRemoverGame, ScoreTrackingListener scoreTrack) {
        //for creating the line of blocks
        //create the blocks in t pattern
        List<Block> blocks = this.levelInformation.blocks(); //create a copy of list of blocks
        //run on all the blocks in the list
        for (Block initBlock : blocks) {
            initBlock.addToGame(this); //add the block to the game
            initBlock.addHitListener(blockRemoverGame); //add the block so it listen to it
            initBlock.addHitListener(scoreTrack); // for the scoring
        }

    }

    /**
     * The method remove a Collidable from the environment game .
     *
     * @param c . Collidable to remove from the game
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The method remove a Sprite from the collection .
     *
     * @param s . Sprite to remove it from the game
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method run a turn .
     */
    public void run() {
    }

    /**
     * This method play one Turn of the game .
     *
     * @param d  . DrawSurface to draw thw game on it
     * @param dt . double specifies the amount of seconds passed since the last call
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //draw all the sprites and notify time passed (so do what they need)
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        //check if the blocks over
        if (this.blocksCounter.getValue() == 0) {
            this.running = true;
        }
        //check if the balls over
        if (this.ballsCounter.getValue() == 0) {
            this.running = true;
            //take one life
            this.numberOfLives.decrease(1);
        }
        //if enter "p" for pause the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    /**
     * One turn of playing in this level .
     */
    public void playOneTurn() {
        //create the paddle
        this.paddleCreation();
        //initialize the number of balls to 0
        this.ballsCounter = new Counter();
        //create the balls onthe paddle
        this.createBallsOnPaddle();
        this.createDeathRegion();
        // countdown before turn starts
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        this.running = false; //change the boolean of runnning to false so it run the animation
        // play one turn of the game - the animation runner run
        this.runner.run(this);
        //check if it because the blocks over
        if (blocksCounter.getValue() == 0) {
            this.score.increase(FINISH_BLOCKS);
        }
        //check if no more balls - take one live
        this.paddle.removeFromGame(this);
        this.deathRegion.removeFromGame(this);
    }

    /**
     * Get the number of remaining blocks .
     *
     * @return how many blocks left
     */
    public int getBlocksCounter() {
        return this.blocksCounter.getValue();
    }

    /**
     * Get the number of lives .
     *
     * @return how many lives have left
     */
    public int getNumberOfLives() {
        return this.numberOfLives.getValue();
    }
}

