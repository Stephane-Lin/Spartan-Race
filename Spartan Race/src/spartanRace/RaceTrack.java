/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package spartanRace;

import cells.DeckOfFortuneCell;
import cells.JokerCell;
import cells.RegularCell;
import cells.SkipCell;
import cells.WheelOfFortuneCell;

/**
 * Race track / game board object
 */
public class RaceTrack {

    public static final int BOARD_LIM = 90;
    public static final int COUNT_PLAYER = 91;
    public static final int MAX_COL_LINE = 30;
    private RegularCell[] track = new RegularCell[COUNT_PLAYER];
    private int[] howMany = new int[COUNT_PLAYER];

    //Constructor	
    public RaceTrack() {
        for (int i = 0; i < track.length; i++) {
            track[i] = new RegularCell();
        }
        
        //Creates a different object of special 
        //cells for each special position - all special
        //positions do not point to the same object.
        track[10] = new DeckOfFortuneCell();
        track[30] = new DeckOfFortuneCell();
        track[50] = new DeckOfFortuneCell();
        track[70] = new DeckOfFortuneCell();
        track[20] = new SkipCell();
        track[55] = new SkipCell();
        track[40] = new WheelOfFortuneCell();
        track[60] = new WheelOfFortuneCell();
        track[80] = new WheelOfFortuneCell();
        track[17] = new JokerCell();
        track[37] = new JokerCell();
        track[57] = new JokerCell();
    }

    /**
     * Increments the count of players on a specific location. If the location
     * is past the end of the board, the last location counter is incremented.
     */
    public void addPlayer(int loc) {
        if (loc >= BOARD_LIM) {
            howMany[BOARD_LIM]++;
        } else {
            howMany[loc]++;
        }
    }

    /**
     * Resets the count of players at each location to back to zero
     */
    public void resetTrack() {
        howMany = new int[COUNT_PLAYER];
    }

    /**
     * Determines if the given location is a special location, that is one where
     * the players spins the wheel, picks a card of fortune, moves directly to a
     * specific location
     *
     * @param	loc	location on board to evaluate
     * @return	track[loc] the character code for the location passed
     */
    public String getCode(int loc) {
        if (loc > BOARD_LIM) {
            return " ";
        }
        return track[loc].toString();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return board
     */
    public String toString() {
        String board = "\r\n";
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= MAX_COL_LINE; j++) {
                board += ((howMany[MAX_COL_LINE * i + j] == 0 ? "_" : howMany[MAX_COL_LINE * i + j]) + " ");
            }
            board += "\r\n";
            for (int j = 1; j <= MAX_COL_LINE; j++) {
                board += (track[MAX_COL_LINE * i + j] + " ");
            }
            board += "\r\n\r\n";
        }
        return board;
    }// toString()	

}
