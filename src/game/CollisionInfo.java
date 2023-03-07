/**
 * yarin sason
 * Assignment 6

 */
package game;

import forms.Point;
import forms.Collidable;

/**
 * This class stands for a Collision info object,
 * in case of collision its hold the collision point and the object
 * that has collided.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * The Collision info constructor base on collision point and a object.
     *
     * @param collisionPoint The point of the collision.
     * @param collisionObject The object that ha collided.
     */
    CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * The collidable object involved in the collision.
     *
     * @return The object that has collided.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
