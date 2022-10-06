/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package forms;

import tools.Velocity;

/**
 * This interface stand for a collidable object.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return The collidable collosion shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  The point of the collision.
     * @param currentVelocity The object current velocity.
     * @param hitter          The hitter Object.
     * @return the new updated velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
