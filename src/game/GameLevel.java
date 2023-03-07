/**
 * yarin sason
 * Assignment 6

 */
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import forms.Ball;
import forms.Collidable;
import forms.Paddle;
import forms.Sprite;
import forms.Block;
import forms.Point;
import graphics.Animation;
import graphics.AnimationRunner;
import graphics.CountdownAnimation;
import graphics.LevelName;
import graphics.PauseScreen;
import levels.LevelInformation;
import tools.BallRemover;
import tools.BlockRemover;
import tools.Constants;
import tools.Counter;
import tools.KeyPressStoppableAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stand for a game object.
 */
public class  GameLevel implements Animation {
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;
    private final ScoreTrackingListener scoreTrackingListener;
    private final ScoreIndicator scoreIndicator;
    private final BallRemover ballRemover;
    private final BlockRemover blockRemover;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private GUI gui;
    private boolean running;
    private int framesPerSecond;
    private LevelName levelName;


    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param keyboard         the keyboard
     * @param animationRunner  the animation runner
     * @param score            the score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner animationRunner,
                     Counter score) {
        this.levelInformation = levelInformation;
        this.setEnvironment(new GameEnvironment());
        this.setSprites(new SpriteCollection());

        this.setGui(animationRunner.getGui());
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);

        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);

        this.levelInformation.getBackground().addToGame(this);
        this.setGui(gui);
        this.keyboard = this.gui.getKeyboardSensor();
        this.setPaddle(new Paddle(this.gui, this.keyboard, levelInformation.paddleWidth(),
                this.levelInformation.paddleSpeed()));

        this.scoreIndicator = new ScoreIndicator(this.score, gui.getDrawSurface());
        this.paddle.addToGame(this);
        this.scoreIndicator.addToGame(this);

        this.levelName = new LevelName(this.levelInformation.levelName());
        this.levelName.addToGame(this);

        this.running = true;
        this.framesPerSecond = Constants.FRAMES_PER_SECOND;
        this.runner = new AnimationRunner(this.gui, this.framesPerSecond);

    }

    /**
     * sets the game environment.
     *
     * @param environment A game environment object.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Sets the game sprites.
     *
     * @param sprites A sprites object.
     */
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    /**
     * set the game paddle.
     *
     * @param paddle A paddle object.
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * This function sets the gui of the game.
     *
     * @param gui A gui object.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * This function adding a collidable object to the game.
     *
     * @param c A collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Responsible for remove a collidable object from the game.
     *
     * @param c A collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.dropCollidable(c);
    }

    /**
     * This function is adding a sprites to the game.
     *
     * @param s A sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This function is removing a sprites to the game.
     *
     * @param s A sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.dropSprite(s);
    }

    /**
     * Games bound blocks creator.
     */
    public void gamesBoundBlocksCreator() {
        Point upBlockPoint = new Point(10, 25);
        Point deathBlockPoint = new Point(-50, Constants.SCREEN_HEIGHT + 5);
        Point rightBlockPoint = new Point(Constants.SCREEN_WIDTH - 10, 0);
        Point leftBlockPoint = new Point(0, 0);

        Block upBlock = new Block(upBlockPoint, Constants.SCREEN_WIDTH, 10);
        Block deathBlock = new Block(deathBlockPoint, Constants.SCREEN_WIDTH + 100, 10);
        Block rightBlock = new Block(rightBlockPoint, 10, Constants.SCREEN_HEIGHT);
        Block leftBlock = new Block(leftBlockPoint, 10, Constants.SCREEN_HEIGHT);

        upBlock.addToGame(this);
        deathBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);

        deathBlock.addHitListener(ballRemover);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        for (Ball ball : this.levelInformation.initialBalls()) {
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.paddle.addBall(ball);
            this.remainingBalls.increase(1);
        }

        List<Block> blockList = new ArrayList<>(this.levelInformation.blocks());
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            this.remainingBlocks.increase(1);
        }

        gamesBoundBlocksCreator();


    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        this.runner.run(new CountdownAnimation(Constants.NUM_OF_SECOND, Constants.COUNT_FROM, this.sprites));
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pause));
            this.runner.run(new CountdownAnimation(Constants.NUM_OF_SECOND, Constants.COUNT_FROM, this.sprites));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBalls.getValue() <= 0 || this.remainingBlocks.getValue() <= 0) {
            this.running = false;
        }
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
}


