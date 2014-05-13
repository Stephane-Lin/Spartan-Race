
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
