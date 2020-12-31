/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import sprites.Block;

/**
 * The interface is of a factory-object that is used for creating blocks .
 */
public interface BlockCreator {
    // Create a block at the specified location.

    /**
     * Create a Block from x and y positions .
     *
     * @param xpos . int the x position
     * @param ypos . int the y position
     * @return the block created
     */
    Block create(int xpos, int ypos);
}