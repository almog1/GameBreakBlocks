package geometry;

import java.util.List;

/**
 * The class of geometry.Line .
 * A line connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * Can tell if equal to another line segment.
 *
 * @author almog gueta
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * construct the Points in the start and the end of the line .
     *
     * @param start . geometry.Point the start of the line
     * @param end   . geometry.Point the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * initialize the x and y of the line coordinate .
     *
     * @param x1 . double the X value of the start point
     * @param y1 . double the Y value of the start point
     * @param x2 . double the X value of the end point
     * @param y2 . double the Y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The function calculate and return the length of this line .
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * The function return the middle geometry.Point of the line .
     *
     * @return The middle geometry.Point
     */
    public Point middle() {
        // calculate the point in the middle by divide the x and the y
        return (new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2));
    }

    /**
     * The function return the Start geometry.Point .
     *
     * @return the start geometry.Point
     */
    public Point start() {
        return this.start;
    }

    /**
     * The function return the End point .
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function checks if the lines intersect .
     *
     * @param other . The line we want to check if intersect with
     * @return true if intersect false if not
     */
    public boolean isIntersecting(Line other) {
        double slopeThisLine, slopeOther; //for the slope of every line
        slopeThisLine = this.slope();
        slopeOther = other.slope();
        // if the lines are equal - return false
        if (this.equals(other)) {
            return false;
        }
        //if the slope equal - not intersecting - return false
        if (slopeOther == slopeThisLine) {
            return false;
        } else {
            //need to check if the intersect point is in the line
            Point intersectPoint = interPoint(other); //for the intersection point
            //return if the x and y values of the intersection point are on the lines
            return this.isBetween(other, intersectPoint);
        }

    }

    /**
     * The function check if the line intersect with the other line .
     *
     * @param other . geometry.Line to check if it intersect with
     * @return The intersection geometry.Point if intersect . Null if not intersect
     */
    public Point intersectionWith(Line other) {
        // if they have intersection point - return it
        if (isIntersecting(other)) {
            return interPoint(other);
        } else {
            return null;
        }

    }

    /**
     * The function return true if the lines have same Points .
     *
     * @param other . geometry.Line to check if equal to
     * @return True if equal or false if not
     */
    public boolean equals(Line other) {
        //if start and end of the points equals return true or false if not
        return ((other.start.equals(this.start) && other.end.equals(this.end))
                || (other.start.equals(this.end) && other.end.equals(this.start)));
    }

    /**
     * The function calculate the slope of line by his 2 points .
     *
     * @return the slope
     */
    public double slope() {
        double xDiffer = this.start.getX() - this.end.getX();
        double yDiffer = this.start.getY() - this.end.getY();
        return (yDiffer / xDiffer);
    }

    /**
     * Calculate the line equalization and check the intersection point of the lines .
     *
     * @param other . geometry.Line to check the intersection with
     * @return the intersection point
     */
    public Point interPoint(Line other) {
        double slopeThisLine, slopeOther;
        double xInter, yInter; // for the intersecting point
        //if the line is like X=K and worth
        slopeThisLine = this.slope();
        slopeOther = other.slope();
        // if one of them is vertical to the Y
        if (slopeOther == Double.POSITIVE_INFINITY || slopeOther == Double.NEGATIVE_INFINITY) {
            xInter = other.start.getX();
            yInter = slopeThisLine * xInter - slopeThisLine * this.start.getX() + this.start.getY();
            // if this is vertical
        } else {
            if (slopeThisLine == Double.POSITIVE_INFINITY || slopeThisLine == Double.NEGATIVE_INFINITY) {
                //check if the y is between
                xInter = this.start.getX();
                yInter = slopeOther * xInter - slopeOther * other.start.getX() + other.start.getY();
            } else {
                xInter = ((slopeThisLine * this.start.getX()) - this.start.getY()
                        - (slopeOther * other.start.getX()) + other.start.getY())
                        / (slopeThisLine - slopeOther);
                yInter = slopeThisLine * xInter - slopeThisLine * this.start.getX() + this.start.getY();
            }
        }
        //return the intersection point
        return (new Point(xInter, yInter));
    }

    /**
     * The function gets point and line and check if the point is on one of the lines .
     *
     * @param other . geometry.Line The other line to check if the point is on it
     * @param inter . geometry.Point The point we want to check if it on the geometry.Line
     * @return True if the point is on the line and false if not
     */
    private boolean isBetween(Line other, Point inter) {
        double minXThis, minXOther, maxXThis, maxXOther, minYThis, minYOther, maxYThis, maxYOther;
        double epsilon = 0.0001;
        //add / less to all of them epsilon

        //take the minimum and maximum between the values of X of this line
        minXThis = Math.min(this.start.getX(), this.end.getX()) - epsilon;
        maxXThis = Math.max(this.start.getX(), this.end.getX()) + epsilon;
        //take the minimum and maximum between the values of Y of this line
        minYThis = Math.min(this.start.getY(), this.end.getY()) - epsilon;
        maxYThis = Math.max(this.start.getY(), this.end.getY()) + epsilon;
        //take the minimum and maximum between the values of X of the other line
        minXOther = Math.min(other.start.getX(), other.end.getX()) - epsilon;
        maxXOther = Math.max(other.start.getX(), other.end.getX()) + epsilon;
        //take the minimum and maximum between the values of Y of the other line
        minYOther = Math.min(other.start.getY(), other.end.getY()) - epsilon;
        maxYOther = Math.max(other.start.getY(), other.end.getY()) + epsilon;
        //check it is between the min and max and return true or false

        return ((inter.getX() >= minXThis) && (inter.getX() <= maxXThis)
                && (inter.getX() >= minXOther) && (inter.getX() <= maxXOther)
                && (inter.getY() >= minYThis) && (inter.getY() <= maxYThis)
                && (inter.getY() >= minYOther) && (inter.getY() <= maxYOther));
    }

    /**
     * The method check if the rectangle intersect with the line .
     *
     * @param rect . geometry.Rectangle to check bounderies
     * @return null if not intersect and if intersect - the closet intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        List intersectPoints = rect.intersectionPoints(this);
        double minDistance = 0; //for the smallest distance between points
        //check if the list is empty - no intersection points - return null
        if (intersectPoints.isEmpty()) {
            return null;
        }
        //need to put the first so we will have minimum
        minDistance = ((Point) intersectPoints.get(0)).distance(this.start);
        closestPoint = (Point) intersectPoints.get(0);
        //check the distance from the point to the start of the line
        for (int i = 1; i < intersectPoints.size(); i++) {
            //if the distance between the points is less - change the min
            if (((Point) intersectPoints.get(i)).distance(this.start) < minDistance) {
                closestPoint = (Point) intersectPoints.get(i);
                minDistance = ((Point) intersectPoints.get(i)).distance(this.start);
            }
        }
        return closestPoint;
    }

    /**
     * Check if a point is on the line .
     *
     * @param point . geometry.Point the point to check if it on this line
     * @return true if it on the line and false if not
     */
    public boolean isOnTheLine(Point point) {
        //need to check if between the start and the end
        double minXThis, maxXThis, minYThis, maxYThis;
        double epsilon = 0.0001;
        // if this is vertical
        if (this.slope() == Double.POSITIVE_INFINITY || this.slope() == Double.NEGATIVE_INFINITY) {
            //check it have the same X value - include epsilon
            if ((this.start.getX() >= point.getX() - epsilon) && (this.start().getX() <= point.getX() + epsilon)) {
                //check it in the limit of the Y values of the line
                minYThis = Math.min(this.start.getY(), this.end.getY()) - epsilon;
                maxYThis = Math.max(this.start.getY(), this.end.getY()) + epsilon;
                return ((point.getY() >= minYThis) && (point.getY() <= maxYThis));
            } else {
                return false;
            }
        } else {
            //check if it the equaztion
            double getY = (((this.slope() * point.getX())
                    - (this.slope() * this.start.getX()) + this.start.getY()));
            // check if it have same Y value + - epsilon
            if ((getY >= point.getY() - epsilon) && (getY <= point.getY() + epsilon)) {
                //take the minimum and maximum between the values of X of this line
                minXThis = Math.min(this.start.getX(), this.end.getX()) - epsilon;
                maxXThis = Math.max(this.start.getX(), this.end.getX()) + epsilon;
                //take the minimum and maximum between the values of Y of this line
                minYThis = Math.min(this.start.getY(), this.end.getY()) - epsilon;
                maxYThis = Math.max(this.start.getY(), this.end.getY()) + epsilon;
                //check it is between the min and max and return true or false
                return ((point.getX() >= minXThis) && (point.getX() <= maxXThis)
                        && (point.getY() >= minYThis) && (point.getY() <= maxYThis));
            } else {
                return false;
            }
        }

    }
}
