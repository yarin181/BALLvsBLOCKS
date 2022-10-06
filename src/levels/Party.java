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
 * The type Party.
 */
public class Party implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Random random = new Random();
        int angle;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle = -80 + random.nextInt(160);
            velocityList.add(Velocity.fromAngleAndSpeed(angle, Constants.SLOW_BALL_SPEED));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return Constants.PADDLE_MOVE_FAST;
    }

    @Override
    public int paddleWidth() {
        return Constants.PADDLE_SIZE_VERY_BIG;
    }

    @Override
    public String levelName() {
        return "Party";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private double move;
            private double factor = 0.001;

            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.white);
                d.drawText(43, 143, "BAR-ILAN", 60);
                d.drawText(53, 223, "CLUB", 60);
                d.setColor(Color.GREEN);
                d.drawText(40, 140, "BAR-ILAN", 60);
                d.drawText(50, 220, "CLUB", 60);
                d.setColor(Color.white);
                d.drawLine(600, 0, 600, 150);

                for (int i = 60; i > 10; i--) {
                    if (i % 2 == 0) {
                        d.setColor(Color.white);
                        d.fillCircle((int) (600 + this.move / 3), (int) (150 - this.move / 3), i);
                    } else {
                        d.setColor(Color.LIGHT_GRAY);
                        d.fillCircle(600 + (int) (this.move / 3), (int) (150 + this.move / 3), i);
                    }

                }
                for (int i = 0; i < 50; i++) {
                    d.setColor(Color.pink);
                    d.drawLine(Constants.SCREEN_WIDTH / 2 + 500, 0,
                            (int) (200 - move * i) / 2, Constants.SCREEN_HEIGHT);
                    d.setColor(Color.CYAN);
                    d.drawLine(Constants.SCREEN_WIDTH / 2 + 300, 0,
                            (int) (move * i) / 3, Constants.SCREEN_HEIGHT);
                    d.setColor(Color.yellow);
                    d.drawLine(Constants.SCREEN_WIDTH / 2 - 200, 0,
                            (int) (800 + (move * i - 200) / 2), Constants.SCREEN_HEIGHT);
                    move = move + factor;
                }
                if (move > 5 || move < 0) {
                    this.factor *= -1;
                }


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
        for (int j = 0; j < 15; j++) { //15
            int r = 50 + random.nextInt(200);
            int g = 50 + random.nextInt(200);
            int b = 50 + random.nextInt(200);
            Color color = new Color(r, g, b);
            forms.Point blockPoint = new Point(25 + (j * 50), 300);
            Block block = new Block(blockPoint, 50, 40);
            block.setColor(color);
            blockList.add(block);
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 6 * 14;
    }

    @Override
    public List<Ball> initialBalls() {
        List<Ball> ballList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int ballX = Constants.BALL_LEFT_BOUND + random.nextInt(Constants.BALL_RIGHT_BOUND);
            int ballY = Constants.BALL_UP_BOUND + 50
                    + random.nextInt(Constants.BALL_BOTTOM_BOUND - Constants.BALL_UP_BOUND - 50);
            Ball ball = new Ball(ballX, ballY, 3, Color.red);
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
