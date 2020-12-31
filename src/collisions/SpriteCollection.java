/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package collisions;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;


/**
 * Make a collection of Sprites .
 */
public class SpriteCollection {
    private List<Sprite> listOfSpirte;

    /**
     * constructor .
     * Make an Array list and then can add Sprites
     */
    public SpriteCollection() {
        this.listOfSpirte = new ArrayList<Sprite>();
    }

    /**
     * Add a sprites.Sprite to the collection .
     *
     * @param s . sprites.Sprite to add to this collection
     */
    public void addSprite(Sprite s) {
        this.listOfSpirte.add(s);
    }

    /**
     * Remove a sprites.Sprite from the collection .
     *
     * @param s . sprites.Sprite to remove from this collection
     */
    public void removeSprite(Sprite s) {
        this.listOfSpirte.remove(s);
    }


    // call timePassed() on all sprites.

    /**
     * Call to the timePassed() Method of all the sprites .
     *
     * @param dt . double specifies the amount of seconds passed since the last call
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> listOfSpriteCp = this.listOfSpirte; //copy of it so no Exception while iterating

        //run on all the sprites in the List
        for (int i = 0; i < listOfSpriteCp.size(); i++) {
            // call to the timePassed method of every sprites.Sprite
            listOfSpriteCp.get(i).timePassed(dt);
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * Draw all the Sprites on the DrawSurface .
     *
     * @param d . DrawSurface on it will draw the sprites.Sprite
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> listOfSpriteCp = this.listOfSpirte; //copy of it so no Exception while iterating
        // run on all the Sprites in the List
        for (int i = 0; i < listOfSpriteCp.size(); i++) {
            // call to the DrawOn method of every sprites.Sprite
            listOfSpriteCp.get(i).drawOn(d);
        }
    }
}