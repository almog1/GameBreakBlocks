/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package menus;

import animations.Animation;

/**
 * Animation of the menu with the strings and keys .
 *@param <T> . Type
 */
public interface Menu<T> extends Animation {
    /**
     * Add a selection of key,string and return val to this menu .
     *
     * @param key       . String the key to wait for press
     * @param message   . String the message printed
     * @param returnVal . Task the return value - depend on the menu type
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Status of the return val .
     *
     * @return the return value
     */
    T getStatus();

    /**
     * Reset the values of should stop and alreadyPassed .
     * so the menu animation can run again
     */
    void resetMenu();

    /**
     * Add a sub menu by key, message andthe menu .
     * @param key . String
     * @param message . String
     * @param subMenu . Menu<T>
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}