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
import java.util.Random;

/**
 * The type Big final level.
 */
public class BigFinal implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 6;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Random random = new Random();
        int angle;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle = -80 + random.nextInt(160);
            velocityList.add(Velocity.fromAngleAndSpeed(angle, Constants.FAST_BALL_SPEED));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return Constants.PADDLE_MOVE_FAST;
    }

    @Override
    public int paddleWidth() {
        return Constants.PADDLE_SIZE_SMALL;
    }

    @Override
    public String levelName() {
        return "Big Final";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private int flicker;

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#4682B4"));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(105, 360, 140, 300);
                d.setColor(Color.white);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 10; j++) {
                        d.fillRectangle(115 + i * 22, 370 + j * 40, 12, 30);
                    }
                }


                d.setColor(Color.decode("#696969"));
                d.fillRectangle(155, 280, 40, 80);
                d.setColor(Color.decode("#A9A9A9"));
                d.fillRectangle(170, 115, 10, 165);
                d.setColor(Color.decode("#F88379"));
                d.fillCircle(175, 100, 15);
                d.setColor(Color.decode("#D70040"));
                if (this.flicker % 100 < 80) {
                    d.fillCircle(175, 100, 10);
                    d.setColor(Color.decode("#fee5e3"));
                    d.fillCircle(175, 100, 4);
                }
                this.flicker++;

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
        List<Block> blockList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            Color color = new Color(r, g, b);
            for (int j = i; j < 14; j++) {
                forms.Point blockPoint = new Point(200 + (j * 40), 100 + i * 30);
                Block block = new Block(blockPoint, 40, 30);
                block.setColor(color);
                blockList.add(block);
            }
        }
        return blockList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 9 * 14;
    }

    @Override
    public List<Ball> initialBalls() {
        List<Ball> ballList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int ballX = Constants.BALL_LEFT_BOUND + random.nextInt(Constants.BALL_RIGHT_BOUND);
            int ballY = Constants.BALL_UP_BOUND + 100
                    + random.nextInt(Constants.BALL_BOTTOM_BOUND - Constants.BALL_UP_BOUND - 50);
            Ball ball = new Ball(ballX, ballY, 3, Color.BLACK);
            ball.setVelocity(this.initialBallVelocities().get(i));
            ballList.add(ball);
        }
        return ballList;
    }

    @Override
    public int numberOfBallsToRemove() {
        return this.numberOfBalls();
    }
}
