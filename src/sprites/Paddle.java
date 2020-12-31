/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import games.GameLevel;
import games.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Paddle - the class that represent the Paddle of the game .
 * a kind of a Sprite and a Collidable
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Point upperLeft;
    private double width;
    private double height;
    private int limitStart;
    private int limitEnd;
    private Velocity velocity;
    private Color color;

    /**
     * Constructor .
     * create the new paddle by the values of upper left point, width and its height
     *
     * @param upperLeft  . geometry.Point of the left start side coordinate of the paddle
     * @param width      . double the width size of the paddle
     * @param height     . double the height size of the paddle
     * @param keyboard   . KeyboardSensor to know what the user  key
     * @param limitStart . int the width start of the limit
     * @param limitEnd   . int the width end of the limit
     * @param velocity   . games.Velocity of the ball
     */
    public Paddle(Point upperLeft, double width, double height, KeyboardSensor keyboard,
                  int limitStart, int limitEnd, Velocity velocity) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.keyboard = keyboard;
        this.limitStart = limitStart;
        this.limitEnd = limitEnd - (int) this.width; //calculate by the width of the paddle
        // need to make sure the dx positive and dy 0
        if (velocity.getDx() > 0) {
            this.velocity = new Velocity(velocity.getDx(), 0);
        } else {
            this.velocity = new Velocity(velocity.getDx() * (-1), 0);
        }
    }

    /**
     * Move the paddle one step to left .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void moveLeft(double dt) {
        //use for the left side - need to move in minus DX
        Velocity velLeft = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy());
        //make sure it didn't go next from the limit
        if (velLeft.applyToPoint(this.upperLeft, dt).getX() >= this.limitStart) {
            this.upperLeft = velLeft.applyToPoint(this.upperLeft, dt); //move the X value one left
        }

    }

    /**
     * Move the paddle one step to right .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void moveRight(double dt) {
        //make sure it didn't go next from the limit
        if (this.velocity.applyToPoint(this.upperLeft, dt).getX() <= this.limitEnd) {
            this.upperLeft = this.velocity.applyToPoint(this.upperLeft, dt);
        }

    }

    /**
     * Timed passed - move paddle .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }

    }

    /**
     * Draw thw sprites.Paddle on the surface .
     *
     * @param d . DrawSurface on this the padlle will be drown
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
        d.setColor(Color.BLACK);
        //draw borders
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);

        //  this.getCollisionRectangle().drawOn(d); // do it by the Recatangle drawOn method
    }

    // collisions.Collidable

    /**
     * Create a new geometry.Rectangle by the paddle's values .
     *
     * @return the geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return (new Rectangle(this.upperLeft, this.width, this.height));
    }

    /**
     * Check where is the CollisionPoint on the paddle .
     * By that change the games.Velocity to the velocity by the place it Collision in the sprites.Paddle and
     * the correct new angle
     *
     * @param hitter          . Ball that the paddle hitter by
     * @param collisionPoint  . geometry.Point the object collided with
     * @param currentVelocity . games.Velocity the object have when collided
     * @return The new games.Velocity after it hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //split the paddle for 5 different regions
        Line[] lines = new Line[6];
        Point pointStart = this.upperLeft;
        Point pointEnd;
        int speed = (int) ((currentVelocity.fromVelocityToSpeed()));
        Velocity newVelocity = currentVelocity;
        //find on which line is the collisionPoint
        // if it is intersect on the horizental - turn the DY
        if (((this.getCollisionRectangle().getHorizentalUp()).isOnTheLine(collisionPoint))) {
            //change the DY - by its place on the paddle - one of the 5
            for (int i = 1; i <= 5; i++) {
                pointEnd = new Point(pointStart.getX() + (this.width / 5), pointStart.getY());
                lines[i] = new Line(pointStart, pointEnd);
                pointStart = pointEnd;
            }
            //check on which place the collisionPoint is
            // 1 - most left - degree is 300
            if (lines[1].isOnTheLine(collisionPoint)) {
                newVelocity = newVelocity.fromAngleAndSpeed(300, speed);
            } else {
                if (lines[2].isOnTheLine(collisionPoint)) {
                    newVelocity = newVelocity.fromAngleAndSpeed(330, speed);
                } else {
                    //3 - back up
                    if (lines[3].isOnTheLine(collisionPoint)) {
                        newVelocity = new Velocity(newVelocity.getDx(), newVelocity.getDy() * (-1));
                    } else {
                        //4 - little right
                        if (lines[4].isOnTheLine(collisionPoint)) {
                            newVelocity = newVelocity.fromAngleAndSpeed(30, speed);
                        } else {
                            //5 - most right
                            if (lines[5].isOnTheLine(collisionPoint)) {
                                newVelocity = newVelocity.fromAngleAndSpeed(60, speed);
                            } else {
                                //if dy positive
                                if (newVelocity.getDy() > 0) {
                                    //make it positive - need to back from the paddle always up
                                    newVelocity = new Velocity(newVelocity.getDx(), newVelocity.getDy() * (-1));
                                }
                            }
                        }
                    }
                }
            }
            //newVelocity = newVelocity

        }
        //if the it is intersect on the vertical - turn the DX
        if (((this.getCollisionRectangle().getVerticalLeft()).isOnTheLine(collisionPoint))
                || ((this.getCollisionRectangle().getVerticalRight()).isOnTheLine(collisionPoint))) {
            //change the DX
            newVelocity = new Velocity(newVelocity.getDx() * (-1), newVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game .
     * add it as collidable and to collection of sprites
     *
     * @param g . games.GameLevel to add the paddle to it
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this); //add the paddle to the Collidables
        g.addSprite(this); //add the paddle to the Sprites collection
    }

    /**
     * Remove this paddle from the game .
     * remove it as collidable and to collection of sprites
     *
     * @param g . games.GameLevel to remove the paddle from it
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this); //remove the paddle from the Collidables
        g.removeSprite(this); //remove the paddle from the Sprites collection
    }

    /**
     * Return the upper left point of this  paddle .
     *
     * @return The point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * The width of this paddle .
     *
     * @return the Width of the paddle
     */
    public double getWidth() {
        return this.width;
    }
}