/**
 * @author Almog Gueta
 * @version 4.0
 * @since 11.06.18
 */
package levels;

import games.Velocity;
import sprites.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for getting a file and create Level Informations .
 */
public class LevelSpecificationReader {
    //for the begining and end of levels
    public static final String START_LEVEL = "START_LEVEL";
    public static final String END_LEVEL = "END_LEVEL";

    //borders - their width/height
    private static final int BORDER_SIZE = 30;

    //for the begining and end of blocks definition
    private static final String START_BLOCKS = "START_BLOCKS";
    private static final String END_BLOCKS = "END_BLOCKS";

    //for all the level information
    private static final String LEVEL_NAME = "level_name";
    private static final String BALL_VELOCITIES = "ball_velocities";
    private static final String BACKGROUND = "background";
    private static final String PADDLE_SPEED = "paddle_speed";
    private static final String PADDLE_WIDTH = "paddle_width";
    private static final String BLOCK_DEFINITIONS = "block_definitions";
    private static final String BLOCKS_START_X = "blocks_start_x";
    private static final String BLOCKS_START_Y = "blocks_start_y";
    private static final String ROW_HEIGHT = "row_height";
    private static final String NUMBER_OF_BLOCKS = "num_blocks";

    /**
     * Empty constructor .
     */
    public LevelSpecificationReader() {

    }

    /**
     * Get a file name and return List of Level Information's .
     *
     * @param reader . java.io.Reader file to read from
     * @return List of Level Information
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<List<String>> levels = new ArrayList<List<String>>();
        List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
        //try to reader
        try {
            // reader = new BufferedReader(
            //       new InputStreamReader(new FileInputStream("FILENAME")));
            levels = this.levelsSplit(reader);
            //run on all the levels
            for (int i = 0; i < levels.size(); i++) {
                levelInformations.add(this.parseStringsToInfo(levels.get(i)));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed");
        } finally {
            if (reader != null) { // Exception might have happened at constructor
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(" Failed closing the file !");
                }

            }
        }
        return levelInformations;
    }

    /**
     * This method Splitting the file into levels .
     * reading a single level specification block from file
     * (from START_LEVEL to END_LEVEL)
     * get a java.io.Reader
     * and return a list of strings.
     *
     * @param reader . Java.io.Reader to read the file
     * @return list of strings - list of all the lines in the level
     * @throws Exception if problem while parsing
     */
    public List<List<String>> levelsSplit(java.io.Reader reader) throws Exception {
        List<List<String>> levels = new ArrayList<List<String>>();
        boolean succedRead = true;

        //try to read lines from the file by reading lines
        try {
            //first line - if not keep reading until null or find a "START_LEVEL"
            String line = ((BufferedReader) reader).readLine();
            //check if it is  not the start
            while (line != null) {
                List<String> level = new ArrayList<String>();
                //take the line
                //if start level
                if (line.equals(START_LEVEL)) {
                    succedRead = false;
                    line = ((BufferedReader) reader).readLine();
                    //if not null - means find the START - put the Strings into the List
                    //while not null and not END - add to the string list
                    while (line != null && !line.equals(END_LEVEL)) {
                        //check it isn't empty or comment
                        if (!line.startsWith("#")) {
                            //check it isn't empty
                            if (!line.isEmpty()) {
                                level.add(line);
                            }
                        }
                        line = ((BufferedReader) reader).readLine();
                    }
                    //if this line is not null - means succeeded to read level - change boolean to true
                    //else need to throw error of parsing
                    if (line.equals(END_LEVEL)) {
                        succedRead = true;
                    }
                    levels.add(level);
                }
                line = ((BufferedReader) reader).readLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        } finally {
            if (reader != null) { // Exception might have happened at constructor
                try {
                    reader.close();
                    //if succed boolean is true return level
                    if (succedRead) {
                        return levels;
                    } else {
                        throw new RuntimeException("Failed Parsing");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(" Failed closing the file !");
                }

            }
        }

        //if didn't return - failed
        throw new RuntimeException("Failed prsing");
    }

    /**
     * This Method get a list of strings, parse them .
     *
     * @param level . List<String> list of the strings of this level
     * @return object of level information
     */
    public LevelInformation parseStringsToInfo(List<String> level) {
        LevelInformationFactory levelInformation = new LevelInformationFactory();
        //for all the component in level information
        String splitReg = ":";
        int k = 0;
        int blocksStartX = -1;
        int blocksStartY = -1;
        int blocksHeight = -1;
        boolean startBlock = false;
        BlocksFromSymbolsFactory blocksSymbols = null;
        //run on the string and parse the information
        for (int i = 0; i < level.size(); i++) {
            String line = level.get(i);
            //make sure it isn't comment line
            if (!line.startsWith("#")) {
                //if it is the blocks send to blocks
                if (line.equals(START_BLOCKS)) {
                    //sent it to block definitioor reader
                    startBlock = true;
                    k = i;
                    break;
                } else {
                    String[] afterSplit = line.split(splitReg);
                    String key = null;
                    String value = null;
                    //make sure String at least 2 size
                    if (afterSplit.length == 2) {
                        key = afterSplit[0];
                        value = afterSplit[1];
                        //check what is the key
                        if (key.equals(LEVEL_NAME)) {
                            levelInformation.setLevelName(value);
                        } else if (key.equals(BALL_VELOCITIES)) {
                            Velocity vel = new Velocity(0, 0);
                            //velocities split by " "
                            String[] velocities = value.split(" ");
                            //split each velocity to angle and speed
                            //in addition the velocities length is the number of blocks
                            for (int j = 0; j < velocities.length; j++) {
                                String[] angleAndSpeed = velocities[j].split(",");
                                vel = vel.fromAngleAndSpeed(Double.parseDouble(angleAndSpeed[0]),
                                        Double.parseDouble(angleAndSpeed[1]));
                                levelInformation.addVelocitiy(vel);
                            }
                            levelInformation.setNumberOfBalls(velocities.length);
                        } else if (key.equals(BACKGROUND)) {
                            //if it is fill - need to check if fill with image or color
                            if (value.startsWith("color")) {
                                ColorsParser colorParse;
                                Color colorOfBack = null;
                                colorOfBack = BlocksDefinitionReader.valueOfColor(value);
                                //if not null - save it to the block
                                if (colorOfBack != null) {
                                    levelInformation.setBackground(colorOfBack);
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
                                    throw new RuntimeException(e.toString());
                                }
                                levelInformation.setBackground(img);
                            }
                        } else if (key.equals(PADDLE_SPEED)) {
                            levelInformation.setPaddleSpeed(Integer.parseInt(value));
                        } else if (key.equals(PADDLE_WIDTH)) {
                            levelInformation.setPaddleWidth(Integer.parseInt(value));
                        } else if (key.equals(BLOCK_DEFINITIONS)) {
                            java.io.Reader readerBlocks = null;
                            try {
                                readerBlocks = new BufferedReader(new InputStreamReader(
                                        ClassLoader.getSystemClassLoader().getResourceAsStream(value)));
                            } catch (Exception e) {
                                throw new RuntimeException("failed");
                            } finally {
                                if (readerBlocks != null) {
                                    try {
                                        blocksSymbols = BlocksDefinitionReader.fromReader(readerBlocks);
                                        readerBlocks.close();
                                    } catch (Exception e) {
                                        throw new RuntimeException("failed close");
                                    }
                                }
                            }
                        } else if (key.equals(BLOCKS_START_X)) {
                            blocksStartX = Integer.parseInt(value);
                        } else if (key.equals(BLOCKS_START_Y)) {
                            blocksStartY = Integer.parseInt(value);
                        } else if (key.equals(ROW_HEIGHT)) {
                            blocksHeight = Integer.parseInt(value);
                        } else if (key.equals(NUMBER_OF_BLOCKS)) {
                            levelInformation.setBlockToRemove(Integer.parseInt(value));
                        }
                    }
                }
            }
        }
        List<String> blocksStrings = new ArrayList<String>();
        //start block - wait to end
        if (startBlock) {
            k++;
            //until the end
            while (k < level.size()) {
                String line = level.get(k);
                //if the end of the block - break
                if (!line.equals(END_BLOCKS)) {
                    blocksStrings.add(line);
                } else {
                    break;
                }
                k++;
            }
        }

        levelInformation.setBlocks(this.createListBlocks(blocksSymbols, new BlocksSetting((blocksStartX),
                blocksStartY, blocksHeight), blocksStrings));

        return levelInformation.create();

    }

    /**
     * Get the factory, x,y,height and the lines strins and create blocks .
     *
     * @param blocksFactory . BlocksFromSymbolsFactory
     * @param blockSet      .BlockSetting with the blocks set
     * @param blcoksLines   . List<String> blcoks lines
     * @return list of the blocks
     */
    public List<Block> createListBlocks(BlocksFromSymbolsFactory blocksFactory,
                                        BlocksSetting blockSet, List<String> blcoksLines) {
        List<Block> blockList = new ArrayList<Block>();
        int xTemp = blockSet.getBlocksStartX();
        int yTemp = blockSet.getBlocksStartY();
        //run on the lines
        for (int i = 0; i < blcoksLines.size(); i++) {
            //run on the chars
            for (int j = 0; j < blcoksLines.get(i).length(); j++) {
                String symbol = String.valueOf(blcoksLines.get(i).charAt(j));
                //if the symbol exist as block def
                if (blocksFactory.isBlockSymbol(symbol)) {
                    Block block = blocksFactory.getBlock(symbol, xTemp, yTemp);
                    blockList.add(block);
                    xTemp = xTemp + (int) block.getWidth();
                } else if (blocksFactory.isSpaceSymbol(symbol)) {
                    //take the x one step of this symbol width
                    xTemp = xTemp + blocksFactory.getSpaceWidth(symbol);
                } else {
                    //if symbol not exist
                    throw new RuntimeException("Symbol not exist!");
                }
            }
            //in the end - the y + height
            yTemp = yTemp + blockSet.getRowHeight();
            xTemp = blockSet.getBlocksStartX();
        }
        return blockList;
    }
}
