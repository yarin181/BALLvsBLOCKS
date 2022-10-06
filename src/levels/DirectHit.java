/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package levels;

import biuoop.DrawSurface;
import forms.Ball;
import forms.Block;
import forms.Point;
import forms.Sprite;
import game.GameLevel;
import tools.Constants;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(0, Constants.SLOW_BALL_SPEED));
        return velocityList;
    }

    @Override
    public List<Ball> initialBalls() {
        Ball ball = new Ball((double) Constants.SCREEN_WIDTH / 2,
                (double) Constants.SCREEN_HEIGHT - 50, 3, Color.yellow);
        ball.setVelocity(this.initialBallVelocities().get(0));
        List<Ball> ballsList = new ArrayList<>();
        ballsList.add(ball);

        return ballsList;
    }

    @Override
    public int numberOfBallsToRemove() {
        return 1;
    }

    @Override
    public int paddleSpeed() {
        return Constants.PADDLE_MOVE_SLOW;
    }

    @Override
    public int paddleWidth() {
        return Constants.PADDLE_SIZE_SMALL;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {

        return new Sprite() {
            private int move = 0;
            private boolean up;

            @Override
            public void drawOn(DrawSurface d) {
                Color color = new Color(124, 255, 0);
                d.setColor(color);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.black);
                for (int i = 0; i < 100; i++) {
                    d.drawLine(0, i * 10 + move, i * 10 + 50, Constants.SCREEN_HEIGHT);
                    d.drawLine(Constants.SCREEN_WIDTH, i * 10 + move, i * 10 + 50, 0);
                }
                if (this.up) {
                    move++;
                } else {
                    move--;
                }
                if (move > 50) {
                    this.up = false;
                }
                d.setColor(Color.BLUE);
                d.drawCircle(Constants.SCREEN_WIDTH / 2 - 5, 225, 100);
                d.drawCircle(Constants.SCREEN_WIDTH / 2 - 5, 225, 80);
                d.drawCircle(Constants.SCREEN_WIDTH / 2 - 5, 225, 60);
                d.drawLine(280, 225, 360, 225);
                d.drawLine(430, 225, 510, 225);
                d.drawLine(Constants.SCREEN_WIDTH / 2 - 5, 100, Constants.SCREEN_WIDTH / 2 - 5, 180);
                d.drawLine(Constants.SCREEN_WIDTH / 2 - 5, 270, Constants.SCREEN_WIDTH / 2 - 5, 350);

            }

            @Override
            public void timePassed() {

            }

            @Override
            public void addToGame(GameLevel g) {
                g.addSprite(this);
            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        Point blockPoint = new Point((double) Constants.SCREEN_WIDTH / 2 - 30, 200);
        blocksList.add(new Block(blockPoint, 50, 50));
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
