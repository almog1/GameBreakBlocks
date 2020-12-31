/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package collisions;

import geometry.Point;

/**
 * Info of the Collision .
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * counstructor of the Coliision Info .
     *
     * @param collisionPoint  . geometry.Point The point of the collision
     * @param collisionObject . collisions.Collidable The object of that collision in
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Check in which point the collision occur .
     *
     * @return The point of the collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.

    /**
     * Check in which object the collision occur .
     *
     * @return The object of that collision in
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}