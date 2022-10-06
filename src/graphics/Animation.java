/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package graphics;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks of the Animation should stop in boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}
