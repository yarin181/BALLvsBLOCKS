/**
 * yarin sason
 * Assignment 6

 */

package game;

import forms.Ball;
import forms.Block;
import forms.HitListener;
import tools.Constants;
import tools.Counter;


/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * construct a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(Constants.BLOCK_BREAK_BONUS);

    }
}