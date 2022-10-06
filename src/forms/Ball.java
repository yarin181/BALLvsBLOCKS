/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package forms;

import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import tools.Velocity;


/**
 * class represent a ball with his  x,y coordinates and his size (the radius of the ball) .
 */
public class Ball implements Sprite {
    private final int size;
    private final java.awt.Color color;
    private Point point;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructs a ball using a point.
     *
     * @param center Point to the center of the ball.
     * @param r      The size (radius) of the ball.
     * @param color  The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = null;
    }

    /**
     * constructs a ball using a x,y coordinate.
     *
     * @param x     The x coordinate.
     * @param y     The y coordinate.
     * @param r     The ball radius.
     * @param color The ball color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = null;
    }

    /**
     * THis function sets the ball Game environment.
     *
     * @param gameEnvironment A game environment object.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {

        this.gameEnvironment = gameEnvironment;
    }

    /**
     * The function return the x value of a Ball.
     *
     * @return The Ball x coordinate value.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * The function return the y value of a Ball.
     *
     * @return The Ball y coordinate value.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * The function return the size of a Ball.
     *
     * @return The Ball size (Integer).
     */
    public int getSize() {
        return this.size;
    }

    /**
     * The function return the color of a Ball.
     *
     * @return The Ball color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function enable the ball to draw himself in a given surface.
     *
     * @param surface A surface do draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * The function return the position of the ball.
     *
     * @return point.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Sets the ball position.
     *
     * @param point The point to set the ball on.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Set the velocity of the ball based on "dx" ,"dy" parameters.
     *
     * @param dx Stand fo the expected change in the x coordinate.
     * @param dy Stand fo the expected change in the y coordinate.
     */
    public void setVelocity(double dx, double dy) {

        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Tge function return the current velocity of the ball.
     *
     * @return The current velocity.
     */
    public Velocity getVelocity() {

        return this.velocity;
    }

    /**
     * Set the velocity of the ball based on Velocity object.
     *
     * @param v The velocity object
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * This method find the trajectory of a Ball.
     *
     * @return The ball trajectory.
     */
    public Line findTrajectory() {
        Point end = new Point(this.point.getX() + this.velocity.getDx(),
                this.point.getY() + this.velocity.getDy());
        return new Line(new Point(this.point.getX(), this.point.getY()), end);
    }

    /**
     * This function indicate if a ball is inside an object.
     *
     * @return Boolean value if the ball is in collidable.
     */
    public boolean inRange() {
        for (Collidable collidable : gameEnvironment.getCollidables()) {
            Point upperLeft = collidable.getCollisionRectangle().getUpperLeft();
            double upperLeftX = upperLeft.getX();
            double upperLeftY = upperLeft.getY();
            double ballX = this.getX();
            double ballY = this.getY();
            double width = collidable.getCollisionRectangle().getWidth();
            double height = collidable.getCollisionRectangle().getHeight();
            if (ballX > upperLeftX && ballX < upperLeftX + width && ballY > upperLeftY && ballY < upperLeftY + height) {
                return true;
            }
        }
        return false;
    }

    /**
     * The function move the ball one step base on his "dx" and "dy"
     * if the ball got collide with an object direction of the ball.
     */
    public void moveOneStep() {
        if (gameEnvironment != null) {
            Line trajectory = this.findTrajectory();
            CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionInfo != null) {
                this.point = new Point(collisionInfo.collisionPoint().getX() + this.velocity.getDx() / 2,
                        collisionInfo.collisionPoint().getY() + this.velocity.getDy() / 2);
                this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                        this.getVelocity()));
            }
        }
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * This function insert the ball to a given game object.
     *
     * @param g A game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removing the ball from the game.
     *
     * @param gameLevel A game object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}