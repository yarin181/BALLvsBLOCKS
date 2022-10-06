/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

import biuoop.GUI;

import graphics.AnimationRunner;
import levels.BigFinal;
import levels.DirectHit;
import levels.LevelInformation;
import levels.Party;
import levels.SunShine;
import tools.Constants;
import tools.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an example for a part of the game .
 */
public class Ass6Game {
    /**
     * the main function create gui and starting the game.
     *
     * @param args Values that has passed in to commend line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("BALL vs. BLOCKS", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, Constants.FRAMES_PER_SECOND);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());


        List<LevelInformation> levelInformationList = new ArrayList<>();
        for (String num : args) {
            if (num.equals("1")) {
                levelInformationList.add(new DirectHit());
            }
            if (num.equals("2")) {
                levelInformationList.add(new Party());
            }
            if (num.equals("3")) {
                levelInformationList.add(new BigFinal());
            }
            if (num.equals("4")) {
                levelInformationList.add(new SunShine());
            }

        }
        if (levelInformationList.size() == 0) {
            levelInformationList.add(new DirectHit());
            levelInformationList.add(new Party());
            levelInformationList.add(new BigFinal());
            levelInformationList.add(new SunShine());
        }

        gameFlow.runLevels(levelInformationList);
    }
}
