package sample;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A class that wraps up the three interfaces and forms a parent class for all viewable data structure subclasses
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public abstract class VisualizedDataStructure implements ViewableDataStructure, RandomizableDataStructure, SerializableDataStructure<VisualizedDataStructure> {
    public Label outputLabel;
    public VisualizedDataStructure(Label label) {
        this.outputLabel = label;
    }
    @Override
    abstract public void randomize(int size, int range);

    @Override
    abstract public String serialize();

    /**
     * Take in a string and parse it back to the data
     * return null if the input is misformatted
     * @param inputString the string to be parsed
     * @return The data structure
     */
    @Override
    abstract public VisualizedDataStructure deserialize(String inputString);

    @Override
    abstract public List<Node> getNodes();

    @Override
    abstract public List<ControlWrapper> getControls();
}
