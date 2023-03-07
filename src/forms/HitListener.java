/**
 * yarin sason
 * Assignment 6

 */

package forms;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit The being hit block.
     * @param hitter   The hitter ball.
     */

    void hitEvent(Block beingHit, Ball hitter);
}