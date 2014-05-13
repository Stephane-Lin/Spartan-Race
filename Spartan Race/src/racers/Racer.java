
package racers;

/**
 * Abstract racers class (not to be instantiated on game) and basic functions
 * implemented, that will be inherited by the special Player classes.
 */
public abstract class Racer {

    protected String name;
    protected int position;
    protected int energy;

    // Constructor
    public Racer() {
    }

    /**
     * Constructor
     *
     * @param name of racers
     */
    public Racer(String name) {
        this.name = name;
        position = 0;
        energy = 100;
    }

    /**
     * Accessor method
     *
     * @return	name	racers's name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method
     *
     * @return	position of racers
     */
    public int getPosition() {
        return position;
    }

    /**
     * Accessor method
     *
     * @return	energy units of racers
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Mutator method
     *
     * @param n location to move racers to
     */
    public void setPosition(int n) {
        position = n;
    }

    /**
     * Mutator method
     *
     * @param e energy level of racers
     */
    public void setEnergy(int e) {
        this.energy = e;
    }

    /**
     * Move racers by specified number of positions
     *
     * @param n number of positions to move racers on the board (can be >0 or
     * <0)
     */
    public void advanceBy(int n) {
        position += n;
    }
}
