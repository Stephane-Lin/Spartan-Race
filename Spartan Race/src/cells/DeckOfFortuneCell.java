/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package cells;

import java.util.Random;

import racers.Racer;
import spartanRace.MyWriter;

/**
 * Defines the game action when racers lands on a special Deck of Fortune Cell
 */

public class DeckOfFortuneCell extends RegularCell {

    public static int nextCard;
    public static int BONUS_ENERGY = 30;

    /**
     * Default constructor Sets the top card to a number between 0 and 9
     */
    public DeckOfFortuneCell() {
        Random randomGenerator = new Random();
        nextCard = randomGenerator.nextInt(10);
    }

    /**
     * Accessor method
     *
     * @return	nextCard the number of the next card
     */
    public int getNextCard() {
        return nextCard + 1;
    }
    /**
     * Controls the cards in the deck of fortune to be picked
     * @param location player's location
     * @param writer MyWriter object
     * @return player's new location
     */
    public int pickCard(int location, MyWriter writer) {
        writer.printBoth("Picking a card of fortune. ", false);
        writer.printBoth("Current card: " + nextCard, true);
        switch (nextCard + 1) {
            case 1:
                location -= 9;
                writer.printBoth("Go back 9. ", false);
                break;
            case 2:
                location = 0;
                writer.printBoth("Go back to the beginning. ", false);
                break;
            case 3:
                location -= 3;
                writer.printBoth("Go back 3. ", false);
                break;
            case 4:
                location -= 8;
                writer.printBoth("Go back 8. ", false);
                break;
            case 5:
                location += 2;
                writer.printBoth("Go forward 2. ", false);
                break;
            case 6:
                location += 1;
                writer.printBoth("Go forward 1. ", false);
                break;
            case 7:
                location += 3;
                writer.printBoth("Go forward 3. ", false);
                break;
            case 8:
                location = 0;
                writer.printBoth("Go back to the beginning. ", false);
                break;
            case 9:
                location -= 4;
                writer.printBoth("Go back 4. ", false);
                break;
            case 10:
                location += 6;
                writer.printBoth("Go forward 3. ", false);
                break;
        } // switch
        writer.printBoth("You are at location " + location, true);

        // Prepare for next turn
        nextCard = (++nextCard % 10);

        return location;
    }// method pickCard

    /**
     * Overrides the method updateEnergy on RegularCell according to special
     * position on board - Deck of Fortune.
     *
     * @param	player current racers on board
     * @param	p special position
     */
    @Override
    public void updateEnergy(int p, Racer player) {

        super.updateEnergy(p, player);
        player.setEnergy(player.getEnergy() + BONUS_ENERGY);
    }

    /**
     * Display code of the special cell.
     */
    @Override
    public String toString() {
        return "d";
    }

}
