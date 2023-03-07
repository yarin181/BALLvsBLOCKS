/**
 * yarin sason
 * Assignment 6

 */

package levels;

import forms.Ball;
import forms.Block;
import forms.Sprite;
import tools.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();


    /**
     * Initial balls list,based on the velocity list.
     *
     * @return the list
     */

    List<Ball> initialBalls();


    /**
     * Number of balls to remove int.
     *
     * @return the int
     */
    int numberOfBallsToRemove();
}
