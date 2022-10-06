/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package forms;

import java.util.ArrayList;

/**
 * class represent a Rectangle including his upper left point width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft The upper left point.
     * @param width     The rectangle width.
     * @param height    the rectangle height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points, with the specified line.
     *
     * @param line A line to find intersection with.
     * @return A list of the intersection points, null if there isn't.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interactions = new ArrayList<Point>();
        ArrayList<Line> lines = new ArrayList<Line>();
        Point upperLeft = this.getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight());

        lines.add(new Line(upperLeft, upperRight));
        lines.add(new Line(bottomRight, bottomLeft));
        lines.add(new Line(upperRight, bottomRight));
        lines.add(new Line(upperLeft, bottomLeft));

        Point currentPoint;

        for (Line l : lines) {
            currentPoint = l.intersectionWith(line);
            if (currentPoint != null) {
                interactions.add(currentPoint);
            }
        }
        return interactions;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return The rectangle width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return The rectangle height.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * sets the upper left point of the rectangle.
     * @param upperLeft The upper left point.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}

