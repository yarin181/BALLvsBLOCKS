/**
 * yarin sason
 * Assignment 6

 */

package tools;

import forms.Ball;
import forms.Block;
import forms.HitListener;
import game.GameLevel;

/**
 * The class Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * The BallRemover constructor.
     *
     * @param gameLevel         A game object.
     * @param removedBalls The current game blocks status.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * in case of hit event of ball in block death remove the ball from the game.
     *
     * @param beingHit The block that got hit.
     * @param hitter   The hitter Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
