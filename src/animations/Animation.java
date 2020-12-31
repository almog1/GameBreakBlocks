/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package animations;

import biuoop.DrawSurface;

/**
 * Animation interface - represent an animation .
 */
public interface Animation {
    /**
     * One frame on the d dreawsurface .
     *
     * @param d  . DrawSurface on it
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * True or Flase if the game should stop or keep running .
     *
     * @return true or false
     */
    boolean shouldStop();
}