/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package cells;

import java.util.Scanner;

import racers.Racer;
import spartanRace.MyWriter;

/**
 * Defines the game action when racers lands on a special Joker Cell
 */

public class JokerCell extends RegularCell {

    String choice = "";
    public static final WheelOfFortuneCell wheel = new WheelOfFortuneCell();
    public static final DeckOfFortuneCell oneCard = new DeckOfFortuneCell();

    /**
     * Asks the user if, in a joker cell, user rather pick a card or spin the
     * wheel.
     *
     * @param location
     * @return new location calculated by spinning the wheel or picking a card.
     */
    public int cardOrWheel(int location, MyWriter writer, boolean testMode, String jChoice) {
        Scanner keyIn = new Scanner(System.in);
        
        do {
            writer.printBoth("Enter 'w' to spin the wheel or 'c' to pick a card: ", false);
            
            if(testMode){
                choice = jChoice;
                writer.printBoth(choice, true);
            }else{
                choice = keyIn.next();
                writer.println(choice);
            }
        } while (!(choice.equals("w") || choice.equals("c")));

        if (choice.equals("w")){
            return wheel.spinTheWheel(location, writer);
        }
        return oneCard.pickCard(location, writer);

    }// method cardOrWheel

    /**
     * Overrides the method updateEnergy on RegularCell according to special
     * position on board - Joker.
     *
     * @param	player current racers on board
     * @param	p special position
     */
    @Override
    public void updateEnergy(int p, Racer player) {

        if (choice.equals("w")) {
            wheel.updateEnergy(p, player);
        } else if (choice.equals("c")) {
            oneCard.updateEnergy(p, player);
        }
    }

    /**
     * Display code of the special cell.
     */
    @Override
    public String toString() {
        return "?";
    }

}
