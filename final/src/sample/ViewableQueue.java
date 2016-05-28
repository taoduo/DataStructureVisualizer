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
     * @return a list of Nodes with one Node for each entry
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
        Button button = new Button("enqueue");
        EventHandler<ActionEvent> handler = (event) -> {
            this.queue.offer(Integer.parseInt(textField.getText()));
        };
        ControlWrapper wrapper = new ControlWrapper(textField, button, handler);

        TextField textField1 = new TextField();
        Button button1 = new Button("dequeue");
        EventHandler<ActionEvent> handler1 = (event) -> {
            textField1.setText(Integer.toString(this.queue.poll()));
        };
        ControlWrapper wrapper1 = new ControlWrapper(textField1, button1, handler1);
        list.add(wrapper1);

        TextField textField2 = new TextField();
        Button button2 = new Button("getFront");
        EventHandler<ActionEvent> handler2 = (event) -> {
            this.queue.peek();
        };
        ControlWrapper wrapper2 = new ControlWrapper(textField2, button2, handler2);
        list.add(wrapper2);
        // Other operations go here...
        return list;
    }
}
