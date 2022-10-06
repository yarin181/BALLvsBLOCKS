/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package graphics;

import biuoop.DrawSurface;
import forms.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Level name, showing the name of the level in the top right corner of the screen.
 */
public class LevelName implements Sprite {
    /**
     * The Name.
     */
    private final String name;

    /**
     * Instantiates a new Level name.
     *
     * @param name the name
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(600, 20, "Level: " + this.name, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
