/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package tools;

import forms.Point;

/**
 * class represent the velocity of the ball.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructs a velocity object based on the "dx" and "dy" parameters.
     *
     * @param dx Stand fo the expected change in the x coordinate.
     * @param dy Stand fo the expected change in the y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * apply the velocity parameter with angle and speed instead of
     * "dx" and "dy" parameters.
     *
     * @param angle the angle of the move.
     * @param speed the speed of the move.
     * @return Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double xAngleCalc = Math.sin(Math.toRadians(angle));
        double yAngleCalc = Math.cos(Math.toRadians(angle));
        double dx = speed * xAngleCalc;
        double dy = speed * yAngleCalc * -1;
        return new Velocity(dx, dy);
    }

    /**
     * get the combined dx and dy speed.
     * @return The object speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + (Math.pow(this.getDy(), 2)));
    }

    /**
     * This function will update the location of a point based
     * on the current velocity ,will return a new updated point object.
     *
     * @param p Current point object.
     * @return A point object stands for the new updated point position.
     */
    public Point  applyToPoint(Point p) {

        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * function that turn the direction of dx.
     */
    public void dxTurn() {
        this.dx *= -1;
    }

    /**
     * function that turn the direction of dy.
     */
    public void dyTurn() {
        this.dy *= -1;
    }

    /**
     * the function return the Dx value of the Velocity.
     *
     * @return the "dx" value.
     */
    public double getDx() {
        return dx;
    }

    /**
     * the function return the Dy value of the Velocity.
     *
     * @return the "dy" value.
     */
    public double getDy() {
        return dy;
    }
}
