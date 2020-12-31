/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package sprites;

import biuoop.DrawSurface;
import collisions.CollisionInfo;
import games.GameLevel;
import games.GameEnvironment;
import games.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * * sprites.Ball class have size (radius), color,  a geometry.Point of center .
 */
public class Ball implements Sprite {
    // Distance - so the ball collided almost in the point
    private static final double DISTANCE = 0.5;

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private int frameWidthStart;
    private int frameWidthEnd;
    private int frameHightStart;
    private int frameHightEnd;
    private GameEnvironment gameEnv;

    /**
     * Constructor of the sprites.Ball .
     *
     * @param center . geometry.Point of the center
     * @param r      . int the size of the radius
     * @param color  . java.awt.Color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor of the sprites.Ball with size of the limit the ball can move in .
     * the ball doesn't have velocity so apply a basic of 0,0
     *
     * @param center          . geometry.Point of the center
     * @param r               . int the size of the radius
     * @param color           . java.awt.Color the color of the ball
     * @param frameWidthStart . int the start point of the width limit
     * @param frameWidthEnd   . int the end point of the width limit
     * @param frameHightStart . int the start point of the hight limit
     * @param frameHightEnd   . int the end point of the hight limit
     */
    public Ball(Point center, int r, java.awt.Color color, int frameWidthStart, int frameWidthEnd, int frameHightStart,
                int frameHightEnd) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.frameWidthStart = frameWidthStart;
        this.frameWidthEnd = frameWidthEnd;
        this.frameHightStart = frameHightStart;
        this.frameHightEnd = frameHightEnd;
        this.vel = (new Velocity(0, 0)); //for have a basic velocity
    }

    /**
     * The X value of  the center point .
     *
     * @return The X value of  the center point .
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The Y value of  the center point .
     *
     * @return The Y value of  the center point .
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the radius of the ball .
     *
     * @return the size - the size of the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * return the color of the ball .
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface

    /**
     * Draw the ball in the surface given .
     *
     * @param surface . DrawSurface on it it draw the ball by its values and color
     */
    public void drawOn(DrawSurface surface) {
        //draw around the ball
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * change the valocity of the ball .
     *
     * @param v . games.Velocity to change to it
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Change the velocity by create velocity by dx and dy values .
     *
     * @param dx . double the change in the x
     * @param dy . double the change in the y
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * return the valocity of the ball .
     *
     * @return the valocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * Change the center of the sprites.Ball .
     * It will check if there will be any collisions anf if yes
     * will change the games.Velocity after it get there
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void moveOneStep(double dt) {
        //check the game environment - if it return not null it the ball collision
        double newX = this.getVelocity().applyToPoint(this.center, dt).getX(); //the newX of the point by the velocity
        double newY = this.getVelocity().applyToPoint(this.center, dt).getY(); // newY of the new point by the velocity
        Point newCenter = new Point(newX, newY);
        //the line is start from the old center to this new center
        Line trajectory = new Line(this.center, newCenter);
        CollisionInfo thisCollision = this.gameEnv.getClosestCollision(trajectory);
        //check if it collision
        //if it not null - need to send the object to find to which velocity to change
        if (thisCollision != null) {
            //take the rectangle of the collision
            Rectangle thisCollisionRec = thisCollision.collisionObject().getCollisionRectangle();
            //take the point X and Y values
            double thisCollisionX = thisCollision.collisionPoint().getX();
            double thisCollisionY = thisCollision.collisionPoint().getY();
            //moving the ball to almost the point - add / minus distance
            //if in the horizental UP -  the x stay and y change to minus the radius because it from the up
            if ((thisCollisionRec.getHorizentalUp()).isOnTheLine(thisCollision.collisionPoint())) {
                this.center = new Point(thisCollisionX, thisCollisionY - this.r - DISTANCE);
            }
            //if in the horizental Down-  the x stay and y change to plus the radius because it from the down
            if (thisCollisionRec.getHorizentalDown().isOnTheLine(thisCollision.collisionPoint())) {
                this.center = new Point(thisCollisionX, thisCollisionY + this.r + DISTANCE);
            }
            //if in the vertical left - the y stay and x change to minus the radius because it from the left
            if (thisCollisionRec.getVerticalLeft().isOnTheLine(thisCollision.collisionPoint())) {
                this.center = new Point(thisCollisionX - this.r - DISTANCE, thisCollisionY);
            }
            //if in the vertical right - the y stay and x change to plus the radius because it from the right
            if (thisCollisionRec.getVerticalRight().isOnTheLine(thisCollision.collisionPoint())) {
                this.center = new Point(thisCollisionX + this.r + DISTANCE, thisCollisionY);
            }
            //change the velocity by the hit function
            this.setVelocity(thisCollision.collisionObject().hit(this, thisCollision.collisionPoint(), this.vel));
        } else {
            // no need to change the velocity so keep with it and just change the center so the ball move
            this.center = newCenter;
        }

    }

    /**
     * Make the method move one step .
     *
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Add the ball to the games.GameLevel by adding it to Sprites collection .
     *
     * @param gameLevel . games.GameLevel to add the ball to the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        this.gameEnv = gameLevel.getEnvironment();
    }

    /**
     * Remove the ball from the gameLevel by remove it from the Sprite Collection .
     *
     * @param gameLevel . GameLevel to remove the ball from it
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}