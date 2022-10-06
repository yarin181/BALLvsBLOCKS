/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package game;

import biuoop.DrawSurface;
import forms.Sprite;
import tools.Constants;
import tools.Counter;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score   the score
     * @param surface the surface
     */
    public ScoreIndicator(Counter score, DrawSurface surface) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, 25);
        d.setColor(Color.black);
        d.drawText(350, 20, "Score: " + score.getValue(), 20);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
