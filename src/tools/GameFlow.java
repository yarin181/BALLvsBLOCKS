/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package tools;

import biuoop.KeyboardSensor;
import game.GameLevel;
import graphics.Animation;
import graphics.AnimationRunner;
import graphics.EndScreen;
import levels.LevelInformation;


import java.util.List;

/**
 * The type Game flow, get a list of levels and run them one by one.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }

            if (!(level.getRemainingBalls().getValue() > 0)) {
                Animation loseScreen = new EndScreen(this.score, false);

                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, loseScreen));
                this.animationRunner.getGui().close();
                break;
            }
            score.increase(Constants.CLEARING_SCREEN_BONUS);
        }
        Animation winScreen = new EndScreen(this.score, true);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, winScreen));
        this.animationRunner.getGui().close();

    }


}

