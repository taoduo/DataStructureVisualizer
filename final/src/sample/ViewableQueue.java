package sample;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The model class of Queue in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class ViewableQueue implements ViewableDataStructure {
    private Queue<Integer> queue;
    /**
     * Empty queue constructor
     */
    public ViewableQueue() {
        this.queue = new ArrayDeque<>();
    }

    /**
     * Initialize queue with a string
     * @param initString Data structure serialized
     */
    public ViewableQueue(String initString) {

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
    public List<ControlWrapper> getControls() {
        List<ControlWrapper> list = new ArrayList<ControlWrapper>();
        TextField textField = new TextField();
        Button button = new Button("Enqueue");
        EventHandler<ActionEvent> handler = (event) -> {
            this.queue.add(Integer.parseInt(textField.getText()));
        };
        ControlWrapper wrapper = new ControlWrapper(textField, button, handler);
        list.add(wrapper);
        // Other operations go here...
        return list;
    }
}
