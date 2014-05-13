
package cells;

import racers.Racer;
import spartanRace.MyWriter;

/**
 * Defines the game action when racers lands on a special Wheel of Fortune Cell
 */
public class WheelOfFortuneCell extends RegularCell {

    /**
     * Simulates the spinning of the wheel of fortune. There are 8 possible
     * slots the wheel can stop on.
     *
     * @param location location racers at
     * @return	new location
     */
    public int spinTheWheel(int location, MyWriter writer) {
        writer.printBoth("Spinning the wheel of fortune. ", false);
        switch ((int) (Math.random() * 8 + 1)) {
            case 1:
                location += 1;
                writer.printBoth("Go forward 1. ", false);
                break;
            case 2:
                location += 2;
                writer.printBoth("Go forward 2. ", false);
                break;
            case 3:
                location = 0;
                writer.printBoth("Go back to the beginning. ", false);
                break;
            case 4:
                location -= 4;
                writer.printBoth("Go back 4. ", false);
                break;
            case 5:
                location -= 6;
                writer.printBoth("Go back 6. ", false);
                break;
            case 6:
                location -= 7;
                writer.printBoth("Go back 7. ", false);
                break;
            case 7:
                location -= 8;
                writer.printBoth("Go back 8. ", false);
                break;
            case 8:
                location -= 9;
                writer.printBoth("Go back 9. ", false);
                break;
        }// switch
        writer.printBoth("You are now at location " + location, true);
        return location;
    } // method spinTheWheel

    /**
     * Overrides the method updateEnergy on RegularCell according to special
     * position on board - Wheel of Fortune.
     *
     * @param	player current racers on board
     * @param	p special position
     */
    @Override
    public void updateEnergy(int p, Racer player) {

        super.updateEnergy(p, player);
        player.setEnergy(player.getEnergy() * 2);
    }

    /**
     * Display code of the special cell.
     */
    @Override
    public String toString() {
        return "w";
    }

}
