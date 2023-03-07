/**
 * yarin sason
 * Assignment 6

 */

package forms;

import biuoop.DrawSurface;
import game.GameLevel;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * This Class stands for A block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle collisionRectangle;
    private List<HitListener> hitListeners;
    private Color color;

    /**
     * Block constructor based on Point and with and height.
     *
     * @param upperLeft The block upperLeft pint
     * @param width     The block width.
     * @param height    The block height.
     */
    public Block(Point upperLeft, double width, double height) {
        this.collisionRectangle = new Rectangle(upperLeft, width, height);
        this.color = Color.LIGHT_GRAY;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Block constructor based on collision rectangle.
     *
     * @param collisionRectangle Collision rectangle stands for the sizes of the block.
     */
    Block(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
        this.color = Color.LIGHT_GRAY;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return The collidable collision shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {

        return this.collisionRectangle;
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
        Point upperLeft = collisionRectangle.getUpperLeft();
        double width = collisionRectangle.getWidth();
        double height = collisionRectangle.getHeight();
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        if ((collisionPoint.compareY(upperLeft) && (newVelocity.getDy() > 0))
                || (collisionPoint.compareY(bottomRight))) {
            newVelocity.dyTurn();
        }
        if ((collisionPoint.compareX(upperLeft) && (newVelocity.getDx() > 0))
                || (collisionPoint.compareX(bottomRight))) {
            newVelocity.dxTurn();
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color The color to be set.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int blockTopX = (int) this.collisionRectangle.getUpperLeft().getX();
        int blockTopY = (int) this.collisionRectangle.getUpperLeft().getY();
        int width = (int) this.collisionRectangle.getWidth();
        int height = (int) this.collisionRectangle.getHeight();
        surface.fillRectangle(blockTopX, blockTopY, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(blockTopX, blockTopY, width, height);

    }

    @Override
    public void timePassed() {
        //do nothing for now.
    }

    /**
     * Adding the block to the game.
     *
     * @param g A game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removing the block from the game.
     *
     * @param gameLevel A game object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * called whenever a hit occurs, and will notify all of the registered
     * HitListener objects by calling their hitEvent method.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
