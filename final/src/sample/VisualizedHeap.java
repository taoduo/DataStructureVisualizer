package sample;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * The model class of Heap in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedHeap extends VisualizedDataStructure {
    private PriorityQueue<Integer> heap;

    /**
     * Empty heap constructor
     */
    public VisualizedHeap(Label label) {
        super(label);
        this.heap = new PriorityQueue<Integer>();
    }

    /**
     * Reset the heap with random numbers
     * @param size The size of the heap
     * @param range The range of random numbers
     */
    @Override
    public void randomize(int size, int range) {

    }


    /**
     * Returns visual representation of nodes of the data structure
     * @return the viewable nodes of the data structure
     */
    @Override
    public List<Node> getNodes() {
        return null;
    }

    /**
     * Get and returns control components
     * @return control components
     */
    @Override
    public List<ViewableDataStructure.ControlWrapper> getControls() {
        List<ViewableDataStructure.ControlWrapper> list = new ArrayList<ControlWrapper>();
        /*TextField textField = new TextField();
        Button button = new Button("Push");
        EventHandler<ActionEvent> handler = (event) -> {
            this.heap.add(Integer.parseInt(textField.getText()));
        };
        ViewableDataStructure.ControlWrapper wrapper = new ViewableDataStructure.ControlWrapper(textField, button, handler);
        list.add(wrapper);*/
        // Other operations go here...
        return list;
    }

    public String serialize() {
        String result = "";
        return result;
    }

    @Override
    public VisualizedHeap deserialize(String stringRepresentation) {
        return null;
    }
}
