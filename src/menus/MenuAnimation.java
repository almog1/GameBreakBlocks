/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package menus;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu animation implemetaion .
 *
 * @param <T> . MenuAnimation
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<T> returnVal;
    private List<String> keys;
    private List<String> messages;
    private String menuTitle;
    private KeyboardSensor sensor;
    private BackgroundMenu backgroundMenu;
    private boolean needStop;
    private boolean isAlreadyPressed;
    private T status;
    private List<Menu<T>> subMenues;
    private List<String> keysSub;
    private List<String> messagesSub;


    /**
     * Constructor of a new MenuAnimation .
     *
     * @param menuTitle . String the title of the menu
     * @param sensor    . KeyboardSensor the sensor of this keyboard
     */
    public MenuAnimation(String menuTitle, KeyboardSensor sensor) {
        //initilize the lists
        this.menuTitle = menuTitle;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.returnVal = new ArrayList<T>();
        this.sensor = sensor;
        this.needStop = false;
        this.isAlreadyPressed = true;
        this.status = null;
        this.subMenues = new ArrayList<Menu<T>>();
        this.keysSub = new ArrayList<String>();
        this.messagesSub = new ArrayList<String>();

    }

    /**
     * Reset the values of the menu .
     */
    public void resetMenu() {
        this.needStop = false;
        this.isAlreadyPressed = true;
        this.status = null;
    }

    /**
     * Add the key,message and return value to this MenuAnimation .
     *
     * @param key         . String the key to wait for press
     * @param message     . String the message printed
     * @param returnValue . T the return value - depend on the menu type
     */
    @Override
    public void addSelection(String key, String message, T returnValue) {
        this.returnVal.add(returnValue);
        this.messages.add(message);
        this.keys.add(key);
    }

    /**
     * Check if the function need to stop .
     *
     * @return the should stop boolean
     */
    @Override
    public boolean shouldStop() {
        return this.needStop;
    }

    /**
     * Get the background of this menu .
     *
     * @return A sprite of background animation
     */
    public Sprite getBackground() {
        List<String> allKeys = new ArrayList<String>();
        allKeys.addAll(this.keysSub);
        allKeys.addAll(this.keys);
        List<String> allMessages = new ArrayList<String>();
        allMessages.addAll(this.messagesSub);
        allMessages.addAll(this.messages);
        return new BackgroundMenu(this.menuTitle,
                allKeys, allMessages);
        //return (new BackgroundMenu(this.menuTitle,this.keys,this.messages));
    }

    /**
     * This method accept a background sprite and display it .
     *
     * @param background . Sprite of the background
     * @param d          . DrawSurface to draw on it
     */
    public void printBackground(Sprite background, DrawSurface d) {
        background.drawOn(d); //draw the background
    }

    /**
     * One frame - create the animation background .
     *
     * @param d  . DrawSurface on it
     * @param dt . double specifies the amount of seconds passed since the last call double dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //draw the sprite of the background animation
        this.printBackground(this.getBackground(), d);
        //check if one of the return value pressed - if yes - keep it
        for (int i = 0; i < this.returnVal.size(); i++) {
            //if pressed a key
            if (this.sensor.isPressed(this.keys.get(i))) {
                //check if it is the first run
                if (!this.isAlreadyPressed) {
                    this.needStop = true;
                    this.status = this.returnVal.get(i);
                }
            } else {
                this.isAlreadyPressed = false;
            }
        }
        if (!this.keysSub.isEmpty()) {
            for (int i = 0; i < this.keysSub.size(); i++) {
                //if pressed a key
                if (this.sensor.isPressed(this.keysSub.get(i))) {
                    //check if it is the first run
                    if (!this.isAlreadyPressed) {
                        this.needStop = true;
                        //as long as the submenu isn't stopped
                        //  this.needStop = false;
                    }
                } else {
                    this.isAlreadyPressed = false;
                }

            }
        }
    }

    /**
     * Return the status - the pressed .
     *
     * @return the status
     */
    @Override
    public T getStatus() {
        //return this.returnVal;
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        //add the key and messages to the subMenu
        this.subMenues.add(subMenu);
        this.keysSub.add(key);
        this.messagesSub.add(message);
    }
}
