package sample;

/**
 * Converts the data into string and vice versa
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public interface SerializableDataStructure {
    /**
     * Returns the serialized data as string
     * @return the string that represents the data
     */
    String serialize();

    /**
     * Take in a string and parse it back to the data
     * return null if the input is misformatted
     * @param inputString the string to be parsed
     * @return The data structure
     */
    boolean deserialize(String inputString) throws Exception;
}
