/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package collisions;

import games.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * Collidable - a thing that something else can collidable at it .
 */
public interface Collidable {
    /**
     * Make the rectangle shape .
     *
     * @return the rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Get the point that the object collided there with the velocity and return new velocity .
     *
     * @param hitter          . Ball that hit in this Collidable
     * @param collisionPoint  . geometry.Point the object collided with
     * @param currentVelocity . games.Velocity the object have when collided
     * @return the new velocity expected after the hit (based on the force
     * the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}