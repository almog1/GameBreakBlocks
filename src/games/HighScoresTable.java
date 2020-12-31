/**
 * @author Almog Gueta
 * @version 4.0
 * @since 08.06.18
 */
package games;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class HighScoreTable manage a table of size high-scores .
 * it will manage a table to store the historic high scores
 * and present players with the scores-history of previous games by
 */
public class HighScoresTable implements Serializable {
    //the file of the table
    private int maxSize;
    private List<ScoreInfo> scoreList;
    //for the current size
    //   private int currentSize;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.

    /**
     * Create a new empty high-scores table .
     * initilaize to the specified size
     *
     * @param size . int the maximum size of the table - max numbers
     */
    public HighScoresTable(int size) {
        this.maxSize = size;
        //create a new array list of size
        this.scoreList = new ArrayList<ScoreInfo>(size);
    }

    // Add a high-score.

    /**
     * Add the high score to the table .
     *
     * @param score . ScoreInfo to add to the table
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        //check there is enough space
        //check if the rank is <= to size - need to delete the last one and add it
        if (rank <= this.maxSize) {
            //if the maxsize is equal to the current size - delete the last one
            if (this.scoreList.size() == maxSize) {
                //the list is sorted so the delted will be last
                this.scoreList.remove(this.maxSize - 1);
            }
            //add the score to the list
            this.scoreList.add(score);
        }
        //nothing if else because no need to add
    }

    /**
     * The size of the table .
     *
     * @return the size
     */
    public int size() {
        return this.maxSize;
        //return this.scoreList.size();
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.

    /**
     * Sort the list of scores by value .
     *
     * @return the list sorted by score value
     */
    public List<ScoreInfo> getHighScores() {
        //sort the list
        Collections.sort(this.scoreList);
        return this.scoreList;
    }

    /**
     * Return the rank of the current score .
     * where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score . int of score to check what will be its rank
     * @return the Rank
     */
    public int getRank(int score) {
        //first sort the current list (if not already sorted)
        Collections.sort(this.scoreList);
        //run on the list and check if this score >= to the current score - this is the place of it
        for (int i = 0; i < this.scoreList.size(); i++) {
            //check if bigger
            if (score >= this.scoreList.get(i).getScore()) {
                return (i + 1);
            }
        }
        //if got here without return anything - return > size
        return (this.scoreList.size() + 1);
    }

    // Clears the table

    /**
     * Clear the table - create a new empty one .
     */
    public void clear() {
        this.scoreList = new ArrayList<ScoreInfo>(this.maxSize);
    }

    // Load table data from file.
    // Current table data is cleared.

    /**
     * Load the table of the scores from a file .
     *
     * @param filename . File to load the data from it
     * @throws IOException Escreption if any problem with the file
     */
    public void load(File filename) throws IOException {
        HighScoresTable highScoresTable = null;
        // = new HighScoresTable(7) ;
        ObjectInputStream objectInputStream = null;
        //try to reade the object from the file
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            //read the object of highScoreTable
            highScoresTable = (HighScoresTable) objectInputStream.readObject();
            //initialize this highScoresTable
            this.scoreList = highScoresTable.scoreList;
            this.scoreList = highScoresTable.scoreList;
            this.maxSize = highScoresTable.maxSize;
        } catch (FileNotFoundException e) {
            // Couldn't find the file to open
            System.err.println("Unable to find file: " + filename.getName());
            return;
        } catch (ClassNotFoundException e) {
            // The class in the stream is unknown to the JVM
            throw new RuntimeException("Unable to find class for object in file: " + filename.getName());

        } catch (IOException e) {
            // other problem with file
            throw new RuntimeException("Failed reading object");
            //e.printStackTrace(System.err);
        } finally {
            //close the InputStream
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed closing file: " + filename.getName());
            }
        }
    }

    // Save table data to the specified file.

    /**
     * Save the table data to the file .
     *
     * @param filename . File to save the data to it
     * @throws IOException exception while saving the data
     */
    public void save(File filename) throws IOException {
        HighScoresTable highScoresTable = this;
        //create outputstream to write the data to the file
        ObjectOutputStream objectOutputStream = null;
        //try to open output stream to the file
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(highScoresTable); //write this object to the file
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            //try to close the output stream from the file
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    // Read a table from file and return it.
    //
    // reading it, an empty table is returned.

    /**
     * Read a table from file and return it .
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned
     *
     * @param filename . File to load from it
     * @return the HighScoresTable
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(5);
        //try to load it - if failed - return empty table
        try {
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (Exception e) {
            return highScoresTable;
        }
    }

    /**
     * Return the string of the HighScoresTable - name and score .
     *
     * @return the String
     */
    @Override
    public String toString() {
        //print all the strings of the scores
        String highTable = "";
        this.scoreList = this.getHighScores(); //return it sorted
        for (int i = 0; i < this.scoreList.size(); i++) {
            highTable = highTable + this.scoreList.get(i);
            highTable = highTable + "\n"; //down line
        }
        return highTable;
    }
}
