package geometry;

/**
 * A point has an x and a y value, and can measure the distance
 * to other points, and if its is equal to another point .
 *
 * @author almog gueta
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor - Construct the point with x and y values.
     *
     * @param x . double the x value
     * @param y . double the y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function gets a geometry.Point calculate their distance and return it .
     *
     * @param other . geometry.Point to calculate the diatnce to
     * @return the distance between the points
     */
    public double distance(Point other) {
        double distance;
        //take the X and Y from the other point and calculate the distance
        distance = Math.sqrt(Math.pow(this.x - other.getX(), 2)
                + Math.pow(this.y - other.getY(), 2));
        return distance;
    }

    /**
     * The function get a geometry.Point and check if it equal to other geometry.Point .
     *
     * @param other . geometry.Point to check if it equal to it
     * @return true if equal, or false if not
     */
    public boolean equals(Point other) {
        //if the x and the y values of the point are same - return true
        //if not it return false
        return ((other.getX() == this.x) && (other.getY() == this.y));
    }

    /**
     * The function return the X value of the geometry.Point .
     *
     * @return the value of X
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function return the Y value of the geometry.Point .
     *
     * @return the value of y
     */
    public double getY() {
        return this.y;
    }
}

