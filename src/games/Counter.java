/**
 * @author Almog Gueta
 * @version 3.0
 * @since 18.05.18
 */

package games;

/**
 * Counter is a simple class that is used for counting things .
 */
public class Counter {
    private int count;

    /**
     * Create a new counter with 0 counts .
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Create a new counter with the count .
     *
     * @param count . int number of count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Add the number to the current count .
     *
     * @param number . Int number to add to the current count
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Subtract number from current count .
     *
     * @param number . Int the number to substract from the current count
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Return the current count .
     *
     * @return . this count
     */
    public int getValue() {
        return this.count;
    }

}