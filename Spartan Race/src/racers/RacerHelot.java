/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package racers;

/**
 * Special racers type Helot
 */
public class RacerHelot extends Racer {

    //Constructor with Helot characteristics
    public RacerHelot(String Name) {
        energy = 60;
        position = 40;
        name = "h_" + Name;
    }

}
