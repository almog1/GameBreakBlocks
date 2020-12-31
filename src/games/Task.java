/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package games;

/**
 * Interface Task<T> something that needs to happen .
 * or something that we can run() and return a value
 *
 * @param <T> Type of the Task
 */
public interface Task<T> {
    /**
     * Run the Task .
     *
     * @return value
     */
    T run();

}
