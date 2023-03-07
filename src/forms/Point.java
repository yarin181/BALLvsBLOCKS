/**
 * yarin sason
 * Assignment 6

 */

package forms;
import tools.Constants;
/**
 * class represent a point in the space with his final x,y coordinates.
 */
public class Point {
    private final double y;
    private final double x;

    /**
     * The constructor get to coordinate and set them in the Object.
     *
     * @param x The x value.
     * @param y The y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function gets a Point object and base on his x and y coordinate
     * calculate the distance from another point object.
     *
     * @param other Another Point object.
     * @return the Distance between the two points.
     */
    public double distance(Point other) {
        double fromX = this.x;
        double fromY = this.y;
        double toX = other.getX();
        double toY = other.getY();
        return Math.sqrt((fromX - toX) * (fromX - toX) + (fromY - toY) * (fromY - toY));

    }

    /**
     * comparing the y coordinate between two point True if equals false otherwise.
     *
     * @param other Point for compression.
     * @return boolean vale if equals.
     */
    public boolean compareY(Point other) {
        return ((Math.abs(this.getY() - other.getY())) <= Constants.ALLOWED_ERROR);

    }

    /**
     * comparing the x coordinate between two point True if equals false otherwise.
     *
     * @param other Point for compression.
     * @return boolean vale if equals.
     */
    public boolean compareX(Point other) {
        return ((Math.abs(this.getX() - other.getX())) <= Constants.ALLOWED_ERROR);

    }

    /**
     * The function return true is the points are equal, false otherwise.
     *
     * @param other The compare to object.
     * @return The answer if they are equals in boolean.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.getX()) <= Constants.ALLOWED_ERROR)
                && (Math.abs(this.y - other.getY()) <= Constants.ALLOWED_ERROR);
    }

    /**
     * The function return the x value of a Point.
     *
     * @return The point x coordinate value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function return the y value of a Point.
     *
     * @return The point y coordinate value.
     */
    public double getY() {
        return this.y;
    }
}
