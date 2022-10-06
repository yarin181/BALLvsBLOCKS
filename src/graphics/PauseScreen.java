/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package graphics;

import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
