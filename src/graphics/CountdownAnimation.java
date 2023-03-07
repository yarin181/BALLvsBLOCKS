/**
 * yarin sason
 * Assignment 6

 */

package graphics;

import biuoop.DrawSurface;
import game.SpriteCollection;
import tools.Constants;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private int countFrom;
    private boolean running;
    private int frames;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;
        this.running = true;
        this.frames = ((int) numOfSeconds + 1) * Constants.FRAMES_PER_SECOND;


    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.fillRectangle(d.getWidth() / 2 - 100, d.getHeight() / 2 - 40, 240, 45);
        d.setColor(Color.BLUE);
        if (this.frames < Constants.FRAMES_PER_SECOND) {
            d.drawText(d.getWidth() / 2 - 20, d.getHeight() / 2, "Go !!!", 50);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 50);
        }
        this.frames--;
        if (this.frames % Constants.FRAMES_PER_SECOND == 0) {
            this.countFrom--;
        }

        if (this.frames <= 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
