/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is in charge of reading a block-definitions file .
 * and returning a BlocksFromSymbolsFactory object
 */
public class BlocksDefinitionReader {
    //block def specific
    private static final String BLOCK_DEF = "bdef";
    //space def
    private static final String SPACER_DEF = "sdef";

    //default definition
    private static final String DEFAULT = "default";

    //definitions
    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String SYMBOL = "symbol";
    private static final String HIT_POINTS = "hit_points";
    private static final String STROKE = "stroke";
    private static final String FILL = "fill";

    /**
     * This Method get a reader of a specific file and return BlocksFromSymbolsFactory .
     * according to the parsing of the instruction in the file
     *
     * @param reader . java.io.Reader file to read from it
     * @return BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        List<String> blocksStrings = parseBlocksStrings(reader);
        BlocksFromSymbolsFactory blocksSymbols = new BlocksFromSymbolsFactory();
        //check if there is default values
        String[] splitLineBySpaceFirst = blocksStrings.get(0).split(" "); //first line
        BlocksTempCreator blocksTemp = new BlocksTempCreator();
        //check if it default
        if (splitLineBySpaceFirst[0].equals(DEFAULT)) {
            for (int i = 1; i < splitLineBySpaceFirst.length; i++) {
                String definition = splitLineBySpaceFirst[i];
                //try to run on the definitions
                try {
                    blockDefaultChanger(blocksTemp, definition);
                } catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        //run to find definitions
        for (int i = 0; i < blocksStrings.size(); i++) {
            String symbolOfBlock = null;
            BlocksTempCreator blocksTempCreator = new BlocksTempCreator(blocksTemp);
            String line = blocksStrings.get(i);
            //split the line to the definitions
            String[] splitLineBySpace = line.split(" ");
            //the first is
            if (splitLineBySpace.length <= 1) {
                throw new RuntimeException("");
            } else {
                String firstDef = splitLineBySpace[0];
                //check if it space or block definition
                if (firstDef.equals(BLOCK_DEF)) {
                    //run on all the definitions
                    try {
                        symbolOfBlock = blcokDefinition(blocksTempCreator, splitLineBySpace);
                        blocksSymbols.addBlockDef(symbolOfBlock, blocksTempCreator);
                    } catch (Exception e) {
                        throw new RuntimeException(e.toString());
                    }
                } else if (firstDef.equals(SPACER_DEF)) {
                    //create block spacer
                    //run on all the definitions
                    String blockSpaceSymbol = "";
                    boolean blockSymbolChange = false;
                    boolean blockWidthChange = false;
                    int blockSpaceWidth = 0;
                    for (int j = 1; j < splitLineBySpace.length; j++) {
                        String definition = splitLineBySpace[j];
                        String[] definitionKeyAndValue = definition.split(":");
                        String key, value;
                        key = definitionKeyAndValue[0];
                        value = definitionKeyAndValue[1];
                        //if symbol
                        if (key.equals(SYMBOL)) {
                            //for throw exception if it isn't char
                            try {
                                //if more then one character - error
                                if (value.length() > 1) {
                                    throw new RuntimeException("Not Char");
                                } else {
                                    blockSpaceSymbol = value;
                                    blockSymbolChange = true;
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("ERROR - Symbol must be char!");
                            }
                        } else if (key.equals(WIDTH)) {
                            //make sure it's positive integer
                            try {
                                //bigger then 0
                                if (Integer.parseInt(value) > 0) {
                                    blockSpaceWidth = Integer.parseInt(value);
                                    blockWidthChange = true;
                                } else {
                                    throw new RuntimeException("Error - Negative number!");
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Couldn't parse to Int");
                            }

                        }
                    }
                    //check we have two values
                    if (blockSymbolChange && blockWidthChange) {
                        blocksSymbols.addSpacer(blockSpaceSymbol, blockSpaceWidth);
                    } else {
                        throw new RuntimeException("NO VALUES FOR SPACER");
                    }
                }
            }
        }
        return blocksSymbols;
    }

    /**
     * get a file and return List of blocks strings definition .
     *
     * @param reader . java.io.Reader
     * @return List of strings defintions
     */
    public static List<String> parseBlocksStrings(java.io.Reader reader) {
        List<String> blocksDef = new ArrayList<String>();

        //try to read lines from the file by reading lines
        try {
            //first line - if not keep reading until null or find null
            String line = ((BufferedReader) reader).readLine();
            //check if it is  not the start
            if (line != null) {
                //run until null
                while (line != null) {
                    //if it isn't start with "#" - keep it
                    if (!line.startsWith("#")) {
                        //check it isn't empty
                        if (!line.isEmpty()) {
                            blocksDef.add(line);
                        }
                    }
                    line = ((BufferedReader) reader).readLine();
                }

            }

        } catch (IOException e) {
            //     System.out.println(e.toString());
            //   System.out.println(" Something went wrong while reading !");
            System.out.println("");
        } finally {
            if (reader != null) { // Exception might have happened at constructor
                try {
                    reader.close();
                    return blocksDef;
                } catch (IOException e) {
                    throw new RuntimeException(" Failed closing the file !");
                }

            }
        }
        //if nothing sent here - error
        throw new RuntimeException("ERROR PARSING");
        // ...
    }

    /**
     * Get a value and return the real color of it after split .
     *
     * @param value . String of the value after "color"
     * @return the Color (if coudln't find - null)
     */
    public static Color valueOfColor(String value) {
        String[] colorName = value.split("\\(");
        String[] colorNameSecond = colorName[1].split("\\)");
        ///// print the RGB / COLOR NAME
        ColorsParser colorParse;
        Color colorOfBack = null;
        //if it rgb or name
        if (colorNameSecond[0].startsWith("RGB")) {
            String[] colorNumbers = colorName[2].split("\\)");
            String[] numbers = colorNumbers[0].split(",");
            //make sure 3 ints
            if (numbers.length == 3) {
                colorParse = new ColorsParser(Integer.parseInt(numbers[0]),
                        Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));
                colorOfBack = colorParse.getColor();
            }
        } else {
            colorParse = new ColorsParser(colorNameSecond[0]);
            colorOfBack = colorParse.getColor();
        }
        return colorOfBack;


    }

    /**
     * Get a default block temo and a string definition and change the block temp values .
     *
     * @param blocksTemp . BlocksTempCreator to change hit values
     * @param definition . String the dfinition
     */
    private static void blockDefaultChanger(BlocksTempCreator blocksTemp, String definition) {
        //throw run time exception if failed
        try {
            String[] definitionKeyAndValue = definition.split(":");
            String key, value;
            key = definitionKeyAndValue[0];
            value = definitionKeyAndValue[1];
            //if it height or width - must be positive Int
            if (key.equals(WIDTH) || key.equals(HEIGHT) || key.equals(HIT_POINTS)) {
                sizeIntValues(blocksTemp, key, value);
            } else if (key.equals(FILL)) {
                //if it is fill - need to check if fill with image or color
                fillValues(blocksTemp, value);
            } else if (key.startsWith("fill-")) {
                fillValuesMap(blocksTemp, key, value);
            } else if (key.equals(STROKE)) {
                strokeValue(blocksTemp, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * Get a block definition and change the values by the string array .
     *
     * @param blocksTempCreator . BlocksTempCreator to change its values
     * @param splitLineBySpace  . String[] the strings of the definition
     * @return string of the block symbol
     */
    private static String blcokDefinition(BlocksTempCreator blocksTempCreator, String[] splitLineBySpace) {
        //try - if faile throw run time exception
        try {
            String symbolOfBlock = null;
            //run on all the deinitions in the line
            for (int j = 1; j < splitLineBySpace.length; j++) {
                String definition = splitLineBySpace[j];
                String[] definitionKeyAndValue = definition.split(":");
                String key, value;
                key = definitionKeyAndValue[0];
                value = definitionKeyAndValue[1];
                //if it height or width - must be positive Int
                if (key.equals(WIDTH) || key.equals(HEIGHT) || key.equals(HIT_POINTS)) {
                    sizeIntValues(blocksTempCreator, key, value);
                } else if (key.equals(SYMBOL)) {
                    //for throw exception if it isn't char
                    try {
                        //if more then one character - error
                        if (value.length() > 1) {
                            throw new RuntimeException("Not Char");
                        } else {
                            symbolOfBlock = value;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("ERROR - Symbol must be char!");
                    }
                } else if (key.equals(FILL)) {
                    //if it is fill - need to check if fill with image or color
                    fillValues(blocksTempCreator, value);
                } else if (key.startsWith("fill-")) {
                    fillValuesMap(blocksTempCreator, key, value);
                } else if (key.equals(STROKE)) {
                    strokeValue(blocksTempCreator, value);
                }
            }
            return symbolOfBlock;
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }

    }

    /**
     * For the width,hegiht and hit points .
     * change the block temp by the value
     *
     * @param blockTemp . BlocksTempCreator to change its values
     * @param key       . String key
     * @param value     . String the real value
     */
    private static void sizeIntValues(BlocksTempCreator blockTemp, String key, String value) {
        //make sure it's positive integer
        try {
            if (Integer.parseInt(value) > 0) {
                //make sure if it's height or width or hit points
                if (key.equals(WIDTH)) {
                    blockTemp.setWidth(Integer.parseInt(value));
                } else if (key.equals(HEIGHT)) {
                    blockTemp.setHeight(Integer.parseInt(value));
                } else if (key.equals(HIT_POINTS)) {
                    blockTemp.setHitPoints(Integer.parseInt(value));
                }
            } else {
                throw new RuntimeException("Error - Negative number!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't parse to Int");
        }
    }

    /**
     * Change the blocktemo background by the value .
     *
     * @param blockTemp . BlocksTempCreator to change his values
     * @param value     . String of the value
     */
    private static void fillValues(BlocksTempCreator blockTemp, String value) {
        //check if color or image
        if (value.startsWith("color")) {
            Color colorOfBack = null;
            colorOfBack = valueOfColor(value);
            //if not null - save it to the block
            if (colorOfBack != null) {
                blockTemp.setBackground(colorOfBack);
            }
        } else if (value.startsWith("image")) {
            String[] colorName = value.split("\\(");
            String[] colorNameSecond = colorName[1].split("\\)");
            //take the file name
            String image = colorNameSecond[0];
            Image img = null;
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                img = ImageIO.read((is));
            } catch (IOException e) {
                System.out.println("Failed read image");
            }
            blockTemp.setBackground(img);
        }
    }

    /**
     * Change the block hitPointsMap by the values .
     *
     * @param blockTemp . BlocksTempCreator to change its values
     * @param key       . String the key
     * @param value     . String the value
     */
    private static void fillValuesMap(BlocksTempCreator blockTemp, String key, String value) {
        String[] fillNumber = key.split("-");
        int numberHitPoints = Integer.parseInt(fillNumber[1]);
        //if it is color
        if (value.startsWith("color")) {
            Color colorOfBack = null;
            colorOfBack = valueOfColor(value);
            //if not null
            if (colorOfBack != null) {
                blockTemp.addHitPointsMap(numberHitPoints, colorOfBack);
            }
        } else if (value.startsWith("image")) {
            String[] colorName = value.split("\\(");
            String[] colorNameSecond = colorName[1].split("\\)");
            //take the file name
            String image = colorNameSecond[0];
            Image img = null;
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                img = ImageIO.read((is));
            } catch (IOException e) {
                System.out.println("Failed read image");
            }
            blockTemp.addHitPointsMap(numberHitPoints, img);
        }
    }

    /**
     * Change the stroke value in the block temp .
     *
     * @param blockTemp . BlocksTempCreator to change its stroke value
     * @param value     . String the value
     */
    private static void strokeValue(BlocksTempCreator blockTemp, String value) {
        //make sure it's number
        if (value.startsWith("color")) {
            Color colorOfBack = null;
            colorOfBack = valueOfColor(value);
            //if not null
            if (colorOfBack != null) {
                blockTemp.setStroke(colorOfBack);
            }
        }
    }
}



