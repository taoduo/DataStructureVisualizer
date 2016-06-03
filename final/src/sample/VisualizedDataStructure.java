package sample;

import java.util.List;

import javafx.scene.Node;

/**
 * A class that wraps up the three interfaces and forms a parent class for all viewable data structure subclasses
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public abstract class VisualizedDataStructure implements ViewableDataStructure, RandomizableDataStructure, SerializableDataStructure {
    protected Controller controller;
    protected final double BOARD_WIDTH;
    protected final double BOARD_HEIGHT;
    public VisualizedDataStructure(Controller controller) {
        this.controller = controller;
        this.BOARD_HEIGHT = controller.displayBoard.getHeight();
        this.BOARD_WIDTH = controller.displayBoard.getWidth();
    }

    protected boolean isInt(String s) {
        return s.matches("[-+]?\\d+");
    }

    /**
     * Reset the data in the data structure to random numbers
     * @param size the size of the data structure
     * @param min the min of randomization
     * @param max the max of randomization
     */
    @Override
    abstract public void randomize(int size, int min, int max);

    @Override
    abstract public String serialize();

    /**
     * Take in a string and parse it back to the data
     * return null if the input is misformatted
     * @param inputString the string to be parsed
     * @return The data structure
     */
    @Override
    abstract public boolean deserialize(String inputString);

    /**
     * Return the nodes to show to the user
     * @return a list of nodes in the display board
     */
    @Override
    abstract public List<Node> getNodes();

    /**
     * Return the control components
     * @return a list of ControlWrappers, which are packs of a button, an input and a handler
     */
    @Override
    abstract public List<ControlWrapper> getControls();

    /**
     * Tells if this data structure is empty
     * @return true if it is, false if not
     */
    abstract public boolean isEmpty();
}
