package games;

import geometry.Point;

/**
 * @author almog gueta
 * games.Velocity is for speed and direction for a sprites.Ball .
 * games.Velocity specifies the change in position on the `x` and the `y` axes .
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor by the change in x and the change in y values .
     *
     * @param dx . double the change in the X value of the ball
     * @param dy . double the change in the Y value of the ball
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The DX  - the change in the value of X .
     *
     * @return the dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The DY  - the change in the value of Y .
     *
     * @return the dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy) .
     *
     * @param p  . geometry.Point that is the current position
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     * @return The new geometry.Point of the position
     */
    public Point applyToPoint(Point p, double dt) {
        return (new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt));
    }

    // public Point moveByDt(double dt){
    //   new Point()
    //}

    /**
     * Get a new velocity by angle and speed and make a new velocity .
     *
     * @param angle . double the angle of the ball - the direction
     * @param speed . double the speed the ball have
     * @return The new velocity after change the dx and dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = (-1) * speed * Math.cos(Math.toRadians(angle));
        // make dx and dy values by the angle and the speed (the direction)
        return new Velocity(dx, dy);
    }

    /**
     * Calculate the speed by the dx and dy values .
     *
     * @return The current speed of the velocity
     */
    public double fromVelocityToSpeed() {
        double angle = Math.atan(this.getDx() / this.getDy()); //calculate it
        double speed = this.getDx() / Math.sin(angle);
        //check that speed wont be 0
        if (this.getDx() == 0) {
            speed = this.getDy();
        }

        return speed;
    }
}