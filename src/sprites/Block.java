/**
 * @author Almog Gueta
 * @version 3.0
 * @since 23.05.18
 */
package sprites;

import biuoop.DrawSurface;
import collisions.Collidable;
import games.GameLevel;
import games.HitListener;
import games.HitNotifier;
import games.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Block class .
 * represents the blocks in the game that can be hitted and are
 * kind og Sprite and collidable
 */
public class Block implements Sprite, Collidable, HitNotifier {

    //the size of the string
    private static final int FONT_SIZE = 15;

    private Point upperLeft;
    private double width;
    private double height;
    private int hitPoints;
    private Color color;
    private List<HitListener> hitListeners;
    private Color bordersColor;
    private Map<Integer, Background> hitPointsMap;

    private Background background;

    /**
     * Create a block from x and y - just point upper .
     *
     * @param x . int
     * @param y . int
     */
    public Block(int x, int y) {
        this.upperLeft = (new Point(x, y));
    }

    /**
     * Constructor of a new sprites.Block .
     *
     * @param upperLeft    . geometry.Point of the start of the block the left side coordinate
     * @param width        . double the width size of the block
     * @param height       . double the height size of the block
     * @param hitPoints    . int number of hits left to hit the block
     * @param background   .Background the background of this block
     * @param bordersColor . Color of the borders of the block
     * @param hitPointsMap . Map<Integer, Background> map between hit points and background
     */
    public Block(geometry.Point upperLeft, int width, int height, int hitPoints, Background background,
                 Color bordersColor, Map<Integer, Background> hitPointsMap) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.background = background;

        this.hitListeners = new ArrayList<HitListener>();
        this.bordersColor = bordersColor;
        this.hitPointsMap = hitPointsMap;
    }

    /**
     * Constructor of a new sprites.Block .
     *
     * @param upperLeft    . geometry.Point of the start of the block the left side coordinate
     * @param width        . double the width size of the block
     * @param height       . double the height size of the block
     * @param hitPoints    . int number of hits left to hit the block
     * @param background   .Background the background of this block
     * @param bordersColor . Color of the borders of the block
     */
    public Block(geometry.Point upperLeft, double width, double height, int hitPoints, Background background,
                 Color bordersColor) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.background = background;

        this.hitListeners = new ArrayList<HitListener>();
        this.bordersColor = bordersColor;
        this.hitPointsMap = new HashMap<Integer, Background>();

    }

    /**
     * Constructor of a new sprites.Block .
     *
     * @param upperLeft    . geometry.Point of the start of the block the left side coordinate
     * @param width        . double the width size of the block
     * @param height       . double the height size of the block
     * @param hitPoints    . int number of hits left to hit the block
     * @param color        . java.awt.Color the color of this block
     * @param bordersColor . Color of the borders of the block
     */
    public Block(geometry.Point upperLeft, double width, double height, int hitPoints, Color color,
                 Color bordersColor) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        // this.color = color;
        this.background = new ColorBackground(color, (int) upperLeft.getX(),
                (int) (upperLeft.getY()), (int) width, (int) height);
        this.hitListeners = new ArrayList<HitListener>();
        this.bordersColor = bordersColor;
        this.hitPointsMap = new HashMap<Integer, Background>();

    }

    /**
     * Create a new rectangle by its values .
     *
     * @return the rectangle shape of the block
     */
    public Rectangle getCollisionRectangle() {
        return (new geometry.Rectangle(this.upperLeft, this.width, this.height));
    }

    /**
     * Get the point it hit there and the velocity and change .
     *
     * @param hitter          . Ball that hit this block
     * @param collisionPoint  . geometry.Point the object collided with
     * @param currentVelocity . games.Velocity the object have when collided
     * @return The new velocity after it hit
     */
    public Velocity hit(Ball hitter, geometry.Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        //find on which line is the collisionPoint
        // if it is intersect on the horizenteal - turn the DY
        if (((this.getCollisionRectangle().getHorizentalDown()).isOnTheLine(collisionPoint))
                || ((this.getCollisionRectangle().getHorizentalUp()).isOnTheLine(collisionPoint))) {
            //change the DY
            newVelocity = new Velocity(newVelocity.getDx(), newVelocity.getDy() * (-1));
            //
            if (this.hitPoints != 0) {
                this.hitPoints--;
            }
        }
        //if the it is intersect on the vertical - turn the DX
        if (((this.getCollisionRectangle().getVerticalLeft()).isOnTheLine(collisionPoint))
                || ((this.getCollisionRectangle().getVerticalRight()).isOnTheLine(collisionPoint))) {
            //change the DX
            newVelocity = new Velocity(newVelocity.getDx() * (-1), newVelocity.getDy());
            if (this.hitPoints != 0) {
                this.hitPoints--;
            }
        }
        this.notifyHit(hitter); //notify that hit
        return newVelocity;
    }

    /**
     * Draw the sprites.Block in the surface given .
     *
     * @param surface . DrawSurface on it draw the block by its values
     */
    public void drawOn(DrawSurface surface) {
        //check if hit point in the map
        if (!this.hitPointsMap.isEmpty()) {
            if (this.hitPointsMap.containsKey(this.hitPoints)) {
                // this.hitPointsMap.
                this.hitPointsMap.get(this.hitPoints).drawOn(surface);
            } else {
                this.background.drawOn(surface);
            }
        } else {
            // this.background.setBackgrounValues();
            this.background.drawOn(surface);
        }
        //draw borders if not null
        if (this.bordersColor != null) {
            this.drawOnBOrdersOfBlock(surface);
        }


        /*surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height); //point of start (x),(y), width, height
        //print the number of HITS
        surface.setColor(Color.white);
        this.drawOnBOrdersOfBlock(surface); //draw thw borders of the block
        */

    }

    /**
     * Draw the  borders of the sprites.Block in the surface given .
     *
     * @param surface . DrawSurface on it draw the borders block by its values
     */
    public void drawOnBOrdersOfBlock(DrawSurface surface) {
        surface.setColor(this.bordersColor); //color of borders
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * If the time passed - what will it do .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void timePassed(double dt) {

    }

    /**
     * Add it to the gameLevel .
     * add it as collidable and to collection of sprites.Sprite
     *
     * @param gameLevel . games.GameLevel to add the sprites.Block to it
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this); //add the block to the collisions.Collidable
        gameLevel.addSprite(this); //add the block to the Sprites collections
    }

    /**
     * Remove this block from a gameLevel .
     * remove it as collidable and from the collection of sprites.Sprite
     *
     * @param gameLevel . GameLevel from it we will remove this block
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this); //remove it from the environment
        gameLevel.removeSprite(this); //remove the block from the sprite collection
    }

    /**
     * The method called from the Hit method .
     * will send a notification for all the HitListeners by calling their HitEvent method
     *
     * @param hitter . Ball the ball that this block hit by
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This method add a listener to the list of listeners .
     *
     * @param hl . HitListener to add to the list
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method remove a listener from the list of listeners .
     *
     * @param hl . HitListener to remove from the list
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Methid that return the number of hits that left .
     *
     * @return the number if hits
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Return the width of this block .
     *
     * @return width
     */
    public double getWidth() {
        return this.width;
    }
}
