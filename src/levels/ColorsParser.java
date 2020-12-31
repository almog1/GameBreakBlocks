/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import java.awt.Color;

/**
 * Class for parsing a color name to Color .
 */
public class ColorsParser {
    private java.awt.Color color;

    //colors names
    private static final String BLACK = "black";
    private static final String BLUE = "blue";
    private static final String CYAN = "cyan";
    private static final String GRAY = "gray";
    private static final String LIGHT_GRAY = "lightGray";
    private static final String GREEN = "green";
    private static final String ORANGE = "orange";
    private static final String PINK = "pink";
    private static final String RED = "red";
    private static final String WHITE = "white";
    private static final String YELLOW = "yellow";

    // parse color definition and return the specified color.

    /**
     * Creatine color parser .
     *
     * @param s . String
     */
    public ColorsParser(String s) {
        this.color = this.colorFromString(s);
    }

    /**
     * Create color from 3 number .
     *
     * @param x . int
     * @param y . int
     * @param z . int
     */
    public ColorsParser(int x, int y, int z) {
        this.color = this.colorFromNumbers(x, y, z);

    }

    /**
     * Return color .
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Get a string of Color name and return the matching Color object .
     *
     * @param s . String name of the color
     * @return object of java.awt.Color of this Color
     */
    public java.awt.Color colorFromString(String s) {
        //compare name
        if (s.equals(BLACK)) {
            return Color.black;
        } else if (s.equals(BLUE)) {
            return Color.blue;
        } else if (s.equals(CYAN)) {
            return Color.cyan;
        } else if (s.equals(GRAY)) {
            return Color.gray;
        } else if (s.equals(LIGHT_GRAY)) {
            return Color.lightGray;
        } else if (s.equals(GREEN)) {
            return Color.green;
        } else if (s.equals(ORANGE)) {
            return Color.orange;
        } else if (s.equals(PINK)) {
            return Color.pink;
        } else if (s.equals(RED)) {
            return Color.red;
        } else if (s.equals(WHITE)) {
            return Color.white;
        } else if (s.equals(YELLOW)) {
            return Color.yellow;
        }
        //if no one throw an exception
        throw new RuntimeException("ERROR - NOT COLOR FROM THE LIST");
        /*
         *
         * black, blue, cyan, gray, lightGray, green, orange, pink, red, white, yellow
         * */
    }

    /**
     * Create a color from 3 numbers .
     *
     * @param x . int
     * @param y . int
     * @param z . int
     * @return the color
     */
    public java.awt.Color colorFromNumbers(int x, int y, int z) {
        return new Color(x, y, z);

    }
}
