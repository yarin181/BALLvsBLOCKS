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
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * The BlockRemover constructor.
     * @param gameLevel A game object.
     * @param removedBlocks The current game blocks status.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * in case of hit event of ball in block remove the block from the game.
     *
     * @param beingHit The block that got hit.
     * @param hitter The hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }
}