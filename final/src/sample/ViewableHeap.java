package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Created by tagoec on 5/27/16.
 */
public class ViewableHeap implements ViewableDataStructure{
    private PriorityQueue<Integer> heap;

    /**
     * Empty heap constructor
     */
    public ViewableHeap() {
        this.heap = new PriorityQueue<Integer>();
    }

    /**
     * Initialize heap with a string
     * @param initString Data structure serialized
     */
    public ViewableHeap(String initString) {

    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return
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
        TextField textField = new TextField();
        Button button = new Button("Push");
        EventHandler<ActionEvent> handler = (event) -> {
            this.heap.add(Integer.parseInt(textField.getText()));
        };
        ViewableDataStructure.ControlWrapper wrapper = new ViewableDataStructure.ControlWrapper(textField, button, handler);
        list.add(wrapper);
        // Other operations go here...
        return list;
    }
}
