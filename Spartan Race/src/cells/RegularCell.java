/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package cells;

import racers.Racer;

/**
 * Defines the game action when racers lands on a regular Cell
 */
public class RegularCell {

    /**
     * Updates the racers energy according to current position on board.
     *
     * @param	player current racers on board
     * @param	p current position
     */
    public void updateEnergy(int p, Racer player) {

        player.setEnergy(player.getEnergy() - p);
    }

    /**
     * Display code of the special cell.
     */
    @Override
    public String toString() {
        return "_";

    }

}
