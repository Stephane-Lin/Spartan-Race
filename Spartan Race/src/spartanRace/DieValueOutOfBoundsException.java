/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package spartanRace;

/**
 * Special exception class regarding the dice values
 */
public class DieValueOutOfBoundsException extends Exception{
    public DieValueOutOfBoundsException(){}
    public DieValueOutOfBoundsException(String msg){super(msg); System.exit(0);}
}
