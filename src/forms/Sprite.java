/**
 * yarin sason
 * Assignment 6

 */

package forms;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * This interface stand for a Sprite object.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d A surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to the game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
