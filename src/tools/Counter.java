/**
 * yarin sason
 * Assignment 6
 * 318229143
 */

package tools;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increase the counter value.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return the int
     */
    public int getValue() {
        return this.count;
    }
}