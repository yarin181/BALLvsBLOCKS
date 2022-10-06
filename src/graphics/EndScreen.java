/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package graphics;

import biuoop.DrawSurface;
import tools.Counter;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {

    private final boolean win;
    private final Counter score;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param win   the win
     */
    public EndScreen(Counter score, boolean win) {

        this.win = win;
        this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.setColor(Color.decode("#DFFF00"));
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.black);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() / 2 - 200, "you win !!!", 50);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() / 2 - 100, "your score is " + this.score.getValue(), 50);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() - 200, "press space to continue ", 20);
        } else {
            d.setColor(Color.decode("#b94e48"));
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.black);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() / 2 - 200, "you lose..", 50);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() / 2 - 100, "your score is " + this.score.getValue(), 50);
            d.drawText(d.getWidth() / 2 - 300, d.getHeight() - 200, "press space to continue ", 20);

        }

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
