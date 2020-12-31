/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import geometry.Point;
import sprites.Block;
import sprites.Background;
import sprites.ColorBackground;
import sprites.ImageBackground;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Create template blocks .
 */
public class BlocksTempCreator implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, Image> hitPointsMapImage;
    private Map<Integer, Color> hitPointsMapColor;
    private Color stroke;
    private Color backgroundColor;
    private Image backgroundImage;

    /**
     * Initilize all the values to null/empty/-1 by the type .
     */
    public BlocksTempCreator() {
        this.width = -1;
        this.height = -1;
        this.hitPoints = -1;
        this.hitPointsMapImage = new HashMap<Integer, Image>();
        this.hitPointsMapColor = new HashMap<Integer, Color>();
        this.stroke = null;
        this.backgroundColor = null;
        this.backgroundImage = null;
    }

    /**
     * Create a temp blockCreator by the values of another default block .
     *
     * @param blockTemp . BlocksTempCreator to set values by its values
     */
    public BlocksTempCreator(BlocksTempCreator blockTemp) {
        this.width = blockTemp.width;
        this.height = blockTemp.height;
        this.hitPoints = blockTemp.hitPoints;
        this.hitPointsMapImage = blockTemp.hitPointsMapImage;
        this.hitPointsMapColor = blockTemp.hitPointsMapColor;
        this.stroke = blockTemp.stroke;
        this.backgroundColor = blockTemp.backgroundColor;
        this.backgroundImage = blockTemp.backgroundImage;
    }

    @Override
    public Block create(int xpos, int ypos) {
        //check all the the values exist
        if (this.height > 0
                && this.width > 0
                && this.hitPoints > 0
                && (this.backgroundColor != null || !this.hitPointsMapColor.isEmpty()
                || !this.hitPointsMapImage.isEmpty() || this.backgroundImage != null)) {
            Background background = null;
            // if there is background color - cretae background of Color .
            if (backgroundColor != null) {
                background = new ColorBackground(this.backgroundColor, xpos, ypos, this.width, this.height);
            }
            //if there is background image - create an image background of the image .
            if (backgroundImage != null) {
                background = new ImageBackground(this.backgroundImage, xpos, ypos);
            }
            HashMap<Integer, Background> hitPointsMap = new HashMap<Integer, Background>();
            //run on every key in the image map, create backgroun image and put in the new map .
            for (Integer key : this.hitPointsMapImage.keySet()) {
                Background back = new ImageBackground(this.hitPointsMapImage.get(key), xpos, ypos);
                hitPointsMap.put(key, back);
            }
            //run on every key in the color map, create background color and put in the new map .
            for (Integer key : this.hitPointsMapColor.keySet()) {
                Background back = new ColorBackground(this.hitPointsMapColor.get(key), xpos, ypos, width, height);
                hitPointsMap.put(key, back);
            }
            //create the new block and return it
            return new Block(new Point(xpos, ypos), this.width, this.height, this.hitPoints, background,
                    this.stroke, hitPointsMap);
        } else {
            throw new RuntimeException("Error - not all parameters exists!");
        }
    }

    /**
     * Change the heigh value .
     *
     * @param heightBlo . int
     */
    public void setHeight(int heightBlo) {
        this.height = heightBlo;
    }

    /**
     * Change the width value .
     *
     * @param widthBlock . int
     */
    public void setWidth(int widthBlock) {
        this.width = widthBlock;
    }

    /**
     * Change the hitpoints value .
     *
     * @param hitPoint . int
     */
    public void setHitPoints(int hitPoint) {
        this.hitPoints = hitPoint;
    }

    /**
     * add hit points and background to the map .
     *
     * @param hitPo     . int
     * @param imagePath . Image
     */
    public void addHitPointsMap(int hitPo, Image imagePath) {
        this.hitPointsMapImage.put(hitPo, imagePath);
    }

    /**
     * add hit points and background to the map .
     *
     * @param hitPo     . int
     * @param colorBack . Color
     */
    public void addHitPointsMap(int hitPo, Color colorBack) {
        this.hitPointsMapColor.put(hitPo, colorBack);
    }

    /**
     * Change the stroke color .
     *
     * @param colorStroke . Color of the strokes
     */
    public void setStroke(Color colorStroke) {
        this.stroke = colorStroke;
    }

    /**
     * Get background and change it .
     *
     * @param colorBack . Color
     */
    public void setBackground(Color colorBack) {
        this.backgroundColor = colorBack;
    }

    /**
     * Get background and change it .
     *
     * @param imageBack . Image of image file
     */
    public void setBackground(Image imageBack) {
        this.backgroundImage = imageBack;
    }
}
