/**
 * @author Almog Gueta
 * @version 4.0
 * @since 12.06.18
 */
package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This Class wrap an existing animation and add a "waiting-for-key" behavior to it .
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean needStop;
    private boolean isAlreadyPressed;

    /**
     * Constructor of a ned KeyPressStoppableAnimation .
     *
     * @param sensor    . KeyboardSensor the sensor
     * @param key       . String the string that says it need to stop
     * @param animation . Animation the animation that can stop by the key
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.needStop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Draw thw specific animation .
     *
     * @param d  . DrawSurface on it
     * @param dt . double specifies the amount of seconds passed since the last call
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //keep do one frame until the key pressed
        this.animation.doOneFrame(d, dt);
        //if pressed
        if (this.sensor.isPressed(this.key)) {
            //check if it is the first run
            if (!this.isAlreadyPressed) {
                this.needStop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * The needStop boolean variable .
     * if the animation need to stop return true,
     * if can keep running - return false
     *
     * @return true or false
     */
    @Override
    public boolean shouldStop() {
        return this.needStop;
    }
}