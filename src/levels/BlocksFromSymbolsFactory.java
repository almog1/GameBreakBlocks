/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import sprites.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Create blocks from the definition .
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor of a new Blocks Factory .
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }

    /**
     * Add a definition to a block symbol .
     *
     * @param s . String symbol
     * @param b . BlockCreator that associated to this symbol
     */
    public void addBlockDef(String s, BlockCreator b) {
        this.blockCreators.put(s, b);
    }

    /**
     * Add symbol of spacer and the width .
     *
     * @param s     . String symbol
     * @param width . int the size
     */
    public void addSpacer(String s, int width) {
        this.spacerWidths.put(s, width);
    }

    /**
     * Get a string and return it's width .
     *
     * @param s . String
     * @return the width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Return block according to the definitions associated .
     *
     * @param s . String symbol
     * @param x . int position
     * @param y . int position
     * @return block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Check if the symbol spacer exist .
     *
     * @param s . String
     * @return true if exist - false if not
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Check if the symbol block exist .
     *
     * @param s . String
     * @return true if exist - false if not
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

}
