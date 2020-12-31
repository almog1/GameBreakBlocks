/**
 * @author Almog Gueta
 * @version 3.0
 * @since 22.05.18
 */
package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it .
 */
public class AnimationRunner {
    //GUI WIDTH
    private static final int WIDTH_GUI = 800;

    // GUI Height
    private static final int HEIGHT_GUI = 600;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor of new anmiation runner .
     *
     * @param gui . Gui got to use it and run the animation on it
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * Return the gui of this animation runner .
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run this specific animation .
     *
     * @param animation . Animation that it need to run
     */
    public void run(Animation animation) {
        long milles = System.currentTimeMillis();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        double dt;

        //as long as the counter isn't 0
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            dt = 1.0 / this.framesPerSecond;
            animation.doOneFrame(d, dt);
            this.gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        return;
    }
}