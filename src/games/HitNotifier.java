/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */
package games;

/**
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit .
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * Add a listener to this notifier .
     *
     * @param hl . HitListener we want to add
     */
    void addHitListener(HitListener hl);

    // Remove hl from the list of listeners to hit events.

    /**
     * Remove a listener to this notifier .
     *
     * @param hl . HitListener we want to remove
     */
    void removeHitListener(HitListener hl);
}