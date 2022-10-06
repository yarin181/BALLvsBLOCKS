/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package forms;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import tools.Constants;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;


/**
 * This class stand for a paddle object that implements the sprites and the collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private final Rectangle collisionRectangle;
    private final Color color;
    private final ArrayList<Ball> gameBalls;
    private final int speed;
    private final biuoop.GUI gui;

    /**
     * The paddle constructor based on gui and a keyboard sensor.
     *
     * @param gui      A gui.
     * @param keyboard A keyboard sensor.
     * @param width    the width
     * @param speed    the speed
     */
    public Paddle(biuoop.GUI gui, KeyboardSensor keyboard, int width, int speed) {
        this.collisionRectangle = new Rectangle(new Point((double) (Constants.SCREEN_WIDTH / 2 - width / 2),
                (double) Constants.SCREEN_HEIGHT - 30), width, 20);
        this.color = Color.red;
        this.gui = gui;
        this.keyboard = keyboard;
        this.gameBalls = new ArrayList<>();
        this.speed = speed;

    }

    /**
     * Add a ball to the balls game balls list.
     *
     * @param ball A ball object.
     */
    public void addBall(Ball ball) {
        this.gameBalls.add(ball);
    }

    /**
     * Moving the paddle left.
     */
    public void moveLeft() {
        double upperLeftX = this.collisionRectangle.getUpperLeft().getX();
        double upperLeftY = this.collisionRectangle.getUpperLeft().getY();
        this.collisionRectangle.setUpperLeft(new Point(upperLeftX - this.speed, upperLeftY));
    }

    /**
     * Moving the paddle right.
     */
    public void moveRight() {
        double upperLeftX = this.collisionRectangle.getUpperLeft().getX();
        double upperLeftY = this.collisionRectangle.getUpperLeft().getY();
        this.collisionRectangle.setUpperLeft(new Point(upperLeftX + this.speed, upperLeftY));
    }

    /**
     * This check and fix the case that one of the game balls has entered to the paddle.
     */
    private void ballEnter() {
        double paddleX = this.collisionRectangle.getUpperLeft().getX();
        double paddleWidth = this.collisionRectangle.getWidth();
        for (Ball ball : gameBalls) {
            Point upperLeft = this.getCollisionRectangle().getUpperLeft();
            double upperLeftX = upperLeft.getX();
            double upperLeftY = upperLeft.getY();
            double ballX = ball.getX();
            double ballY = ball.getY();
            double width = this.getCollisionRectangle().getWidth();
            double height = this.getCollisionRectangle().getHeight();
            if (((ballX > upperLeftX) && (ballX < upperLeftX + width))
                    && ((ballY > upperLeftY) && (ballY < upperLeftY + height))) {
                if (ball.getPoint().getX() < paddleX + paddleWidth / 2) {
                    ball.setPoint(new Point(paddleX - 10, ball.getPoint().getY()));
                } else {
                    ball.setPoint(new Point(paddleX + paddleWidth + 10, ball.getPoint().getY()));
                }
            }

        }
    }
    // Sprite

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        double paddleX = this.collisionRectangle.getUpperLeft().getX();
        double paddleWidth = this.collisionRectangle.getWidth();
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY) && paddleX > 10) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && (paddleX < Constants.SCREEN_WIDTH - paddleWidth - 10)) {
            this.moveRight();
        }
        ballEnter();


    }

    /**
     * draw the sprite to the screen.
     *
     * @param d A surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int blockTopX = (int) this.collisionRectangle.getUpperLeft().getX();
        int blockTopY = (int) this.collisionRectangle.getUpperLeft().getY();
        int width = (int) this.collisionRectangle.getWidth();
        int height = (int) this.collisionRectangle.getHeight();
        d.fillRectangle(blockTopX, blockTopY, width, height);

    }

    // Collidable

    /**
     * Return the "collision shape" of the object.
     *
     * @return The collidable collosion shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(),
                collisionRectangle.getWidth(), collisionRectangle.getHeight());
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  The point of the collision.
     * @param currentVelocity The object current velocity.
     * @return the new updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //System.out.println("paddle hit is active");
        Point upperLeft = collisionRectangle.getUpperLeft();
        double width = collisionRectangle.getWidth();
        double height = collisionRectangle.getHeight();
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        double speed = currentVelocity.getSpeed();

        double area1 = upperLeft.getX() + ((width * 1) / 5);
        double area2 = upperLeft.getX() + ((width * 2) / 5);
        double area3 = upperLeft.getX() + ((width * 3) / 5);
        double area4 = upperLeft.getX() + ((width * 4) / 5);
        double area5 = upperLeft.getX() + (width);

        if (collisionPoint.compareY(bottomRight)) {
            newVelocity.dyTurn();
        }
        if (collisionPoint.compareY(upperLeft)) {
            if (collisionPoint.getX() <= area1) {
                return Velocity.fromAngleAndSpeed(300, speed);
            }
            if (collisionPoint.getX() <= area2) {
                return Velocity.fromAngleAndSpeed(330, speed);
            }
            if (collisionPoint.getX() <= area3) {
                newVelocity.dyTurn();
            }
            if (collisionPoint.getX() <= area4) {
                return Velocity.fromAngleAndSpeed(30, speed);
            }
            if (collisionPoint.getX() <= area5) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        if ((collisionPoint.compareX(upperLeft) || collisionPoint.compareX(bottomRight))) {
            newVelocity.dxTurn();
        }

        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g A game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
