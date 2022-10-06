/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package tools;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.Animation;

/**
 * The type Key press stoppable animation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed) {
            if (this.sensor.isPressed(this.key)) {
                this.stop = true;
            }
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
