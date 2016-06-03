package sample;

/**
 * An interface to implement if users want to have the function of randomize it
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public interface RandomizableDataStructure {

    /**
     * Randomize the data in the data structure
     * @param size the size of the data structure
     * @param min the min of randomization
     * @param max the max of randomization
     */
    void randomize(int size, int min, int max);
}
