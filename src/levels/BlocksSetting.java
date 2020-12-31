/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

/**
 * Setting of blocks .
 */
public class BlocksSetting {
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;

    /**
     * Constructor of a new block setting .
     *
     * @param blocksX      . int
     * @param blocksStartY . int
     * @param rowHeight    . int
     */
    public BlocksSetting(int blocksX, int blocksStartY, int rowHeight) {
        this.blocksStartX = blocksX;
        this.blocksStartY = blocksStartY;
        this.rowHeight = rowHeight;
    }

    /**
     * Return the Height of the row .
     *
     * @return highet of the row
     */
    public int getRowHeight() {
        return this.rowHeight;
    }

    /**
     * Block start x setting .
     *
     * @return the start x value
     */
    public int getBlocksStartX() {
        return this.blocksStartX;
    }

    /**
     * Blcok start y int setting .
     *
     * @return the StartY value
     */
    public int getBlocksStartY() {
        return this.blocksStartY;
    }
}
