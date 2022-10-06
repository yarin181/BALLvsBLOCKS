/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package game;

import forms.Collidable;
import forms.Line;
import forms.Point;

import java.util.ArrayList;

/**
 * This class represents the game environment .
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * This function adds a collidable object to the collidable list.
     *
     * @param c Collidable Object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * This function remove a collidable object to the collidable list.
     *
     * @param c Collidable Object.
     */
    public void dropCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Returning the list of the collidables.
     *
     * @return A collidables list.
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory Line stands for the move pattern.
     * @return The collision information or null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closetObject = null;
        Point collisionPoint = null;
        for (Collidable object : collidables) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (collisionPoint != null) {
                if (closestPoint != null) {
                    if (collisionPoint.distance(trajectory.start()) < closestPoint.distance(trajectory.start())) {
                        closestPoint = collisionPoint;
                        closetObject = object;
                    }
                } else {
                    closestPoint = collisionPoint;
                    closetObject = object;
                }
            }
        }
        if (closetObject != null) {
            return new CollisionInfo(closestPoint, closetObject);
        }
        return null;
    }

}
