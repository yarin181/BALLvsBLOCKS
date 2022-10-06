/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package game;

import biuoop.DrawSurface;
import forms.Sprite;

import java.util.ArrayList;

/**
 * this class is stand for a sprite collection.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * The sprite collection constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Adding a sprite to the collection.
     *
     * @param s A sprite Object.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removing a sprite to the collection.
     *
     * @param s A sprite to be removed.
     */
    public void dropSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> tempSpriteList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : tempSpriteList) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d The surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> tempSpriteList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : tempSpriteList) {
            sprite.drawOn(d);
        }
    }
}
