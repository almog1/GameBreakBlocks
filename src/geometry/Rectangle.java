package geometry;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author almog
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor of a new rectangle .
     *
     * @param upperLeft . geometry.Point of the start of the rectangle the left side coordinate
     * @param width     . double the width size of the rectangle
     * @param height    . double the height size of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The method gets a geometry.Line and can find the intersection points of the rectangle and the line .
     *
     * @param line . geometry.Line to check the intersection with it
     * @return List of the intersection point
     */
    public List intersectionPoints(Line line) {
        //create lines of the boundaries
        Line vertLeft = this.getVerticalLeft();
        Line vertRight = this.getVerticalRight();
        Line horizUp = this.getHorizentalUp();
        Line horizDown = this.getHorizentalDown();
        List<Point> listInPoints = new ArrayList<Point>();
        //if intersect - add to the list
        if (vertLeft.isIntersecting(line)) {
            listInPoints.add(vertLeft.intersectionWith(line));
        }
        //if intersect - add to the list
        if (vertRight.isIntersecting(line)) {
            listInPoints.add(vertRight.intersectionWith(line));
        }
        //if intersect - add to the list
        if (horizDown.isIntersecting(line)) {
            listInPoints.add(horizDown.intersectionWith(line));
        }
        //if intersect - add to the list
        if (horizUp.isIntersecting(line)) {
            listInPoints.add(horizUp.intersectionWith(line));
        }
        //if the list empty - no inter points - return Null
        return listInPoints;
    }

    /**
     * The Width of the rectangle .
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The Height of the rectangle .
     *
     * @return the Height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The upper-left point of the rectangle .
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Make a geometry.Line of the vertical right side of the rectangle .
     *
     * @return the vertical right line
     */
    public Line getVerticalRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return (new Line(upperRight, downRight));
    }

    /**
     * Make a geometry.Line of the vertical left side of the rectangle .
     *
     * @return the vertical left line
     */
    public Line getVerticalLeft() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return (new Line(upperLeft, downLeft));
    }

    /**
     * Make a geometry.Line of the horizontal up line of the rectangle .
     *
     * @return the horizontal up line
     */
    public Line getHorizentalUp() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return (new Line(upperLeft, upperRight));
    }

    /**
     * Make a geometry.Line of the horizontal down line of the rectangle .
     *
     * @return the horizontal down line
     */
    public Line getHorizentalDown() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return (new Line(downLeft, downRight));
    }

    /**
     * Draw the rectangle on the surface given .
     *
     * @param surface . DrawSurface on it draw the rectangle
     */
    public void drawOn(DrawSurface surface) {
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
}