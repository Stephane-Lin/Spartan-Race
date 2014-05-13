
package spartanRace;

/**
 * Special exception class regarding the dice values
 */
public class DieValueOutOfBoundsException extends Exception{
    public DieValueOutOfBoundsException(){}
    public DieValueOutOfBoundsException(String msg){super(msg); System.exit(0);}
}
