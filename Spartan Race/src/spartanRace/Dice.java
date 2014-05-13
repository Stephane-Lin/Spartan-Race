
package spartanRace;

import java.util.Random;

/**
 * Dice object
 */
public class Dice {

    // Instance Variables
    private int die1;
    private int die2;

    /**
     * Constructor
     */
    public Dice() {
        die1 = 0;
        die2 = 0;
    }
    /**
     * Mutator method
     * @param value first die value
     * @throws DieValueOutOfBoundsException 
     */
    public void setDie1(int value) throws DieValueOutOfBoundsException{
        if(value < 1 || value > 6)
            throw new DieValueOutOfBoundsException();
        die1 = value;
    }
    /**
     * Accessor method
     * @return  value of first die
     */
    public int getDie1(){
        return die1;
    }
    /**
     * Mutator method
     * @param value second die value
     * @throws DieValueOutOfBoundsException 
     */
    public void setDie2(int value) throws DieValueOutOfBoundsException{
        if(value < 1 || value > 6)
            throw new DieValueOutOfBoundsException();
        die2 = value;
    }
    /**
     * Accessor method
     * @return  value of second die
     */
    public int getDie2(){
        return die2;
    }
    
    /**
     * Simulates the rolling of 2 die by generating 2 random numbers between 1
     * and 6 inclusive.
     *
     * @return Sum of the 2 die
     */
    public int rollDice() {
        Random randomGenerator = new Random();
        die1 = randomGenerator.nextInt(6) + 1;
        die2 = randomGenerator.nextInt(6) + 1;
        return die1 + die2;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return "You rolled a " + die1 + " and a " + die2 + ".";
    }
}