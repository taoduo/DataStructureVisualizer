package sample;

/**
 * An interface to implement if users want to have the function of randomize it
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public interface RandomizableDataStructure {
    /**
     * Randomize the data in the data structure
     * @param size the size of the data structure
     * @param range the range of random numbers put into the data structure
     */
    void randomize(int size, int range);
}
