/**
 * yarin sason
 * Assignment 6

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
 * The type Sun shine.
 */
public class SunShine implements LevelInformation {
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
            velocityList.add(Velocity.fromAngleAndSpeed(angle, Constants.SLOW_BALL_SPEED));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return Constants.PADDLE_MOVE_SLOW;
    }

    @Override
    public int paddleWidth() {
        return Constants.PADDLE_SIZE_BIG;
    }

    @Override
    public String levelName() {
        return "Sun Shine";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                String color = "#" + 0x3000;
                int g = 5;
                int b = 3;
                for (int i = 0; i < d.getHeight(); i += 20) {
                    d.fillRectangle(0, i, d.getWidth(), d.getHeight());
                    d.setColor(new Color(255, (249 - g) % 255, (151 - b) % 255));
                    g += 5;
                    b += 4;

                }
                d.setColor(Color.decode("#FFCC33"));
                d.fillCircle(d.getWidth() / 2, Constants.SCREEN_HEIGHT - 150, 30);

                d.setColor(Color.decode("#FFE742"));
                d.fillCircle(d.getWidth() / 2, Constants.SCREEN_HEIGHT - 150, 20);

                d.setColor(Color.decode("#006995"));
                d.fillRectangle(0, Constants.SCREEN_HEIGHT - 150, d.getWidth(), d.getHeight());

                d.setColor(Color.decode("#1E3F66"));
                for (int i = Constants.SCREEN_HEIGHT - 130; i < Constants.SCREEN_HEIGHT; i += 20) {
                    for (int j = 0; j < Constants.SCREEN_WIDTH; j += 80) {
                        d.drawLine((j + (i % 3) * 20) % Constants.SCREEN_WIDTH, i,
                                (j + 30 + (i % 3) * 20) % Constants.SCREEN_WIDTH, i);
                    }
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
        int r = 255;
        int g = 255;
        int b = 155;
        for (int i = 0; i < 4; i++) { //6
            Color color = new Color(r, g, b);
            for (int j = 0; j < 19; j++) { //15
                forms.Point blockPoint = new Point(20 + (j * 40), 100 + i * 30);
                Block block = new Block(blockPoint, 40, 30);
                block.setColor(color);
                blockList.add(block);
            }
            g -= 35;
            b -= 25;
            if (g <= 0) {
                g = 5;
            }
            if (b <= 0) {
                b = 5;
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 4 * 19;
    }

    @Override
    public List<Ball> initialBalls() {
        List<Ball> ballList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int ballX = Constants.BALL_LEFT_BOUND + random.nextInt(Constants.BALL_RIGHT_BOUND);
            int ballY = Constants.BALL_UP_BOUND
                    + random.nextInt(Constants.BALL_BOTTOM_BOUND - Constants.BALL_UP_BOUND);
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
